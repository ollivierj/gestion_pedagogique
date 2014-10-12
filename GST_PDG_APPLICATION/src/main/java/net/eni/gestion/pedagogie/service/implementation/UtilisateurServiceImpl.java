package net.eni.gestion.pedagogie.service.implementation;

import java.sql.SQLException;

import javax.naming.ldap.LdapContext;

import net.eni.gestion.pedagogie.DAO.ProfilDao;
import net.eni.gestion.pedagogie.DAO.UtilisateurDao;
import net.eni.gestion.pedagogie.authentification.ActiveDirectory;
import net.eni.gestion.pedagogie.authentification.ActiveDirectory.User;
import net.eni.gestion.pedagogie.commun.composant.GenericException;
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

	
	protected final ProfilDao profilDao;
       /**
     * Constructeur
     * @param DAO utilisateur
     * @throws SQLException
     */
    @Inject
    public UtilisateurServiceImpl(UtilisateurDao pUtilisateurDao, ProfilDao profilDao) throws SQLException {
        super(pUtilisateurDao);
        this.profilDao = profilDao;
        
    }

	@Override
	public Utilisateur checkLogin(Utilisateur utilisateur) throws GenericException {
		
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
			String sIDutilBDD = dao.checkConnection(utilisateur, LDAPauth);
			if(sIDutilBDD != null){
				//authentification BDD OK
				BDDauth = true;
				Integer IDutilBDD  = Integer.parseInt(sIDutilBDD);
				utilBDD = dao.chargerDetail(IDutilBDD);
				//mise a jour du mot de passe en BDD
				utilBDD.setMotPasse(utilisateur.getMotPasse());
				dao.mettreAJour(utilBDD);
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
				dao.ajouter(newUtil);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return utilBDD;
	}

}
