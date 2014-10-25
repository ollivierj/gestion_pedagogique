package net.eni.gestion.pedagogie.service.implementation;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.ldap.LdapContext;

import net.eni.gestion.pedagogie.DAO.DroitProfilDao;
import net.eni.gestion.pedagogie.DAO.ProfilDao;
import net.eni.gestion.pedagogie.DAO.UtilisateurDao;
import net.eni.gestion.pedagogie.authentification.ActiveDirectory;
import net.eni.gestion.pedagogie.authentification.ActiveDirectory.User;
import net.eni.gestion.pedagogie.commun.composant.GenericException;
import net.eni.gestion.pedagogie.commun.composant.PropertyFileLoader;
import net.eni.gestion.pedagogie.configuration.ApplicationConfiguration;
import net.eni.gestion.pedagogie.configuration.LDAPConfiguration;
import net.eni.gestion.pedagogie.modele.Profil;
import net.eni.gestion.pedagogie.modele.Utilisateur;
import net.eni.gestion.pedagogie.service.UtilisateurService;

import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * @author jollivier
 * Classe d'implémentation pour le module de suivi des utilisateurs
 */
@Singleton
public class UtilisateurServiceImpl extends AServiceImpl<Utilisateur, Integer, UtilisateurDao> implements UtilisateurService {

	private PropertyFileLoader propertyFileLoader = PropertyFileLoader
			.getInstance("configuration");

	protected final ProfilDao profilDao;
	protected final DroitProfilDao droitProfilDao;
	
       /**
     * Constructeur
     * @param DAO utilisateur
     * @throws SQLException
     */
    @Inject
    public UtilisateurServiceImpl(UtilisateurDao pUtilisateurDao, ProfilDao profilDao, DroitProfilDao droitProfilDao) throws SQLException {
        super(pUtilisateurDao);
        this.profilDao = profilDao;
        this.droitProfilDao = droitProfilDao;
    }

    public Utilisateur authentifier(Utilisateur utilisateur) throws GenericException {
    	if (ApplicationConfiguration.DEV_MODE.equals(propertyFileLoader
				.getValue("application.mode"))) {
			try {
				Utilisateur lUtilisateur = this.dao.chargerDetail(1);
				lUtilisateur.getProfil().setDroits(droitProfilDao.getListeDroits(lUtilisateur.getProfil().getId()));
				return lUtilisateur;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	 
		boolean LDAPauth = false;
		boolean BDDauth = false;
		Utilisateur utilBDD = null;
		User userLDAP = null;
		
		// --------------- AUTHENTIFICATION LDAP --------------- //
		try{
			//authentification LDAP Ok
		    LdapContext ctx = ActiveDirectory.getConnection(utilisateur.getLogin(), utilisateur.getMotPasse(), LDAPConfiguration.LDAP_DOMAINE, LDAPConfiguration.getAdresseLDAP());
		    userLDAP = ActiveDirectory.getUser(utilisateur.getLogin(), ctx);
		    LDAPauth = true;
		    ctx.close();
		}
		catch(Exception e){
			//LDAP identifiant MDP incorrect
			System.out.println("identifiant MDP incorrect LDAP");
		}
		
		// --------------- AUTHENTIFICATION BDD --------------- //
		try {
			//check avec login only si identification LDAP ok
			String sIDutilBDD = this.dao.checkConnection(utilisateur, LDAPauth);
			if(sIDutilBDD != null){
				//authentification BDD OK
				BDDauth = true;
				utilBDD = chargeLoginInfo(sIDutilBDD);
				
				//mise a jour du mot de passe en BDD
				utilBDD.setMotPasse(utilisateur.getMotPasse());
				this.dao.mettreAJour(utilBDD);
			} else {
				//identifiant MDP incorrect
				System.out.println("identifiant MDP incorrect");
			}
		} catch (Exception e1) {
			throw new GenericException(
					"Echec lors de la connection a la base de donnée.");
		}
		
		//si l'authentification LDAP est ok et pas l'authentification BDD alors créer l'utilisateur dans la BDD
		if(LDAPauth && !BDDauth){
			String[] userInfo = userLDAP.getCommonName().split(" ");
			Profil profilDefault = null;
			try {
				profilDefault = this.profilDao.chargerDetail(LDAPConfiguration.LDAP_CREATE_USER_DEFAULT_PROFIL);
			} catch (Exception e) {
			}
			Utilisateur newUtil = new Utilisateur();
			newUtil.setCivilite("M");
			newUtil.setNom(userInfo[1]);
			newUtil.setPrenom(userInfo[0]);
			newUtil.setEmail(userLDAP.getUserPrincipal());
			newUtil.setMotPasse(utilisateur.getMotPasse());
			newUtil.setProfil(profilDefault);
			newUtil.setLogin(utilisateur.getLogin());
			try {
				this.dao.ajouter(newUtil);
				String sIDutilBDD = this.dao.checkConnection(newUtil, true);
				utilBDD = chargeLoginInfo(sIDutilBDD);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return utilBDD;
	}
    
    
	
	/**
	 * 
	 * @param sIDutilBDD
	 * @return
	 */
	private Utilisateur chargeLoginInfo(String sIDutilBDD) {
		Integer IDutilBDD  = Integer.parseInt(sIDutilBDD);
		Utilisateur utilBDD = null;
		try {
			utilBDD = this.dao.chargerDetail(IDutilBDD);
			Profil p = this.profilDao.chargerDetail(utilBDD.getProfil().getId());
			ArrayList<String> listeDroit = this.droitProfilDao.getListeDroits(utilBDD.getProfil().getId());
			p.setDroits(listeDroit);
			utilBDD.setProfil(p);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return utilBDD;
	}

	@Override
	public boolean checkConnection(Utilisateur utilisateur, boolean loginOnly)
			throws GenericException {
		try {
			return (null != this.dao.checkConnection(utilisateur, true));
		} catch (Exception e) {
			throw new GenericException("Utilisateur invalide");
		}
	}
}
