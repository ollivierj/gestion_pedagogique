package net.eni.gestion.pedagogie.service.implementation;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.naming.ldap.LdapContext;

import net.eni.gestion.pedagogie.DAO.DroitProfilDao;
import net.eni.gestion.pedagogie.DAO.ProfilDao;
import net.eni.gestion.pedagogie.DAO.UtilisateurDao;
import net.eni.gestion.pedagogie.commun.composant.authentification.AD.ActiveDirectory;
import net.eni.gestion.pedagogie.commun.composant.authentification.AD.ActiveDirectory.User;
import net.eni.gestion.pedagogie.commun.composant.erreur.ApplicationException;
import net.eni.gestion.pedagogie.commun.configuration.ApplicationConfiguration;
import net.eni.gestion.pedagogie.commun.configuration.AuthentificationConfiguration;
import net.eni.gestion.pedagogie.commun.configuration.LDAPConfiguration;
import net.eni.gestion.pedagogie.commun.modele.Profil;
import net.eni.gestion.pedagogie.commun.modele.Utilisateur;
import net.eni.gestion.pedagogie.service.UtilisateurService;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.sun.jersey.core.util.Base64;

/**
 * @author jollivier
 * Classe d'implémentation pour le module de suivi des utilisateurs
 */
@Singleton
public class UtilisateurServiceImpl extends AServiceImpl<Utilisateur, Integer, UtilisateurDao> implements UtilisateurService {

	protected final ProfilDao profilDao;
	protected final DroitProfilDao droitProfilDao;

    /**
     * Constructeur
     * @param DAO utilisateur
     * @throws SQLException
     */
    @Inject
    public UtilisateurServiceImpl(UtilisateurDao pUtilisateurDao, ProfilDao profilDao, DroitProfilDao droitProfilDao) throws ApplicationException {
        super(pUtilisateurDao);
        this.profilDao = profilDao;
        this.droitProfilDao = droitProfilDao;
    }

    public Utilisateur authentifier(String pLogin, String pMotDePasse) throws ApplicationException {
    	Utilisateur lUtilisateur = null;
    	User userLDAP = null;
    	Date lCurrentDate = DateUtils.addHours(new Date(), AuthentificationConfiguration.getExpirationTime());
    	if (ApplicationConfiguration.DEV_MODE.equals(AuthentificationConfiguration.getApplicationMode())) {
			try {
				lUtilisateur = this.dao.chargerDetail(AuthentificationConfiguration.getDefaultUser());
				lUtilisateur.setToken(createToken(lUtilisateur, lCurrentDate));
				lUtilisateur.setDateExpiration(lCurrentDate);
				lUtilisateur=this.dao.mettreAJour(lUtilisateur);
				lUtilisateur.getProfil().setDroits(droitProfilDao.getListeDroits(lUtilisateur.getProfil().getId()));
				return lUtilisateur;
			} catch (Exception e) {
				throw new ApplicationException(
						"Echec lors le l'authentification");
			}
    	}
		// --------------- AUTHENTIFICATION LDAP --------------- //
		try{
			//authentification LDAP Ok
		    LdapContext ctx = ActiveDirectory.getConnection(pLogin, pMotDePasse, LDAPConfiguration.getLdapDomaine(), LDAPConfiguration.getAdresseLDAP());
		    userLDAP = ActiveDirectory.getUser(pLogin, ctx);
		    ctx.close();
		
		    // --------------- AUTHENTIFICATION BDD --------------- //
			//check avec login only si identification LDAP ok
			Integer lUtilisateurId = this.dao.checkConnection(pLogin, pMotDePasse, (null!=userLDAP));
			if(null!=lUtilisateurId){
				//authentification BDD OK
				lUtilisateur = this.dao.chargerDetail(lUtilisateurId);
				lUtilisateur.setMotPasse(pMotDePasse);
				lUtilisateur.setToken(createToken(lUtilisateur, lCurrentDate));
				lUtilisateur.setDateExpiration(lCurrentDate);
				lUtilisateur=this.dao.mettreAJour(lUtilisateur);
				lUtilisateur.getProfil().setDroits(droitProfilDao.getListeDroits(lUtilisateur.getProfil().getId()));
			} else if (null != userLDAP){
				//si l'authentification LDAP est ok et pas l'authentification BDD alors créer l'utilisateur dans la BDD
				String[] userInfo = userLDAP.getCommonName().split(" ");
				Profil profilDefault = this.profilDao.chargerDetail(LDAPConfiguration.getDefaultUserProfil());
				lUtilisateur = new Utilisateur();
				lUtilisateur.setCivilite("M");
				lUtilisateur.setNom(userInfo[1]);
				lUtilisateur.setPrenom(userInfo[0]);
				lUtilisateur.setEmail(userLDAP.getUserPrincipal());
				lUtilisateur.setMotPasse(pMotDePasse);
				lUtilisateur.setProfil(profilDefault);
				lUtilisateur.setLogin(pLogin);
				lUtilisateur.setMotPasse(pMotDePasse);
				lUtilisateur.setToken(createToken(lUtilisateur, lCurrentDate));
				lUtilisateur.setDateExpiration(lCurrentDate);
				lUtilisateur=this.dao.ajouter(lUtilisateur);
				lUtilisateur.getProfil().setDroits(droitProfilDao.getListeDroits(lUtilisateur.getProfil().getId()));
				lUtilisateur.setIsFormateur(false);
			}
		} catch (Exception e1) {
			throw new ApplicationException(
					"Echec lors le l'authentification");
		}
		return lUtilisateur;
	}
    
	@Override
	public boolean checkConnection(String pLogin, String pMotDePasse, boolean loginOnly)
			throws ApplicationException {
		try {
			return (null != this.dao.checkConnection(pLogin, pMotDePasse, true));
		} catch (Exception e) {
			throw new ApplicationException("Utilisateur invalide");
		}
	}
	
	protected String createToken(Utilisateur pUtilisateur, Date pDate){
		StringBuilder lStrBuilder = new StringBuilder();
		lStrBuilder.append(pUtilisateur.getLogin());
		lStrBuilder.append(":");
		lStrBuilder.append(DateFormatUtils.format(pDate, "yyyy-MM-dd HH:mm:ss"));
		return Base64.encode(lStrBuilder.toString().getBytes()).toString();
	}
	
	@Override
	public boolean checkToken(String pToken) throws ApplicationException{
		try {
			return this.dao.checkToken(pToken);
		} catch (Exception e) {
			throw new ApplicationException("Utilisateur invalide");
		}
	}

	@Override
	public List<Utilisateur> getFormateurs(String pSearchText) throws ApplicationException {
		try {
			return dao.getFormateurs(pSearchText);
		} catch (Exception e) {
			throw new ApplicationException("Impossible de récupérer les formateurs");
		}
	}

	public Utilisateur authentifierAvecToken(String token)
			throws ApplicationException {
		try {
			Utilisateur lUtilisateur = this.dao.loginwithtoken(token);
			lUtilisateur.getProfil().setDroits(droitProfilDao.getListeDroits(lUtilisateur.getProfil().getId()));
			return lUtilisateur;
		} catch (Exception e) {
			throw new ApplicationException("Utilisateur invalide");
		}
	}
}
