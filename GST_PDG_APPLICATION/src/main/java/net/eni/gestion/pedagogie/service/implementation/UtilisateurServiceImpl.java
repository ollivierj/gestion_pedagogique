package net.eni.gestion.pedagogie.service.implementation;

import java.sql.SQLException;

import net.eni.gestion.pedagogie.DAO.UtilisateurDao;
import net.eni.gestion.pedagogie.modele.Utilisateur;
import net.eni.gestion.pedagogie.service.UtilisateurService;
import net.eni.gestion.pedagogie.commun.composant.GenericException;
import net.eni.gestion.pedagogie.configuration.LDAPConfiguration;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import javax.naming.ldap.LdapContext;

import net.eni.gestion.pedagogie.authentification.ActiveDirectory;
import net.eni.gestion.pedagogie.authentification.ActiveDirectory.User;

/**
 * @author jollivier
 * Classe d'implémentation pour le module de suivi des utilisateurs
 */
@Singleton
public class UtilisateurServiceImpl extends AServiceImpl<Utilisateur, Integer, UtilisateurDao> implements UtilisateurService {

       /**
     * Constructeur
     * @param DAO utilisateur
     * @throws SQLException
     */
    @Inject
    public UtilisateurServiceImpl(UtilisateurDao pUtilisateurDao) throws SQLException {
        super(pUtilisateurDao);
    }

	@Override
	public Utilisateur checkLogin(Utilisateur utilisateur) throws GenericException {
		
		try{
		    LdapContext ctx = ActiveDirectory.getConnection(utilisateur.getLogin(), utilisateur.getMotPasse(), LDAPConfiguration.LDAP_DOMAINE, LDAPConfiguration.getAdresseLDAP());
		    User[] users = ActiveDirectory.getUsers(ctx);
		    for (User user : users) {
				System.out.println(user.getCommonName()+ " "+user.getUserPrincipal());
			}
		    ctx.close();
		}
		catch(Exception e){
		    //Failed to authenticate user!
			throw new GenericException(
					"Echec lors de la connection au serveur LDAP.");
		}
		return utilisateur;
	}

}
