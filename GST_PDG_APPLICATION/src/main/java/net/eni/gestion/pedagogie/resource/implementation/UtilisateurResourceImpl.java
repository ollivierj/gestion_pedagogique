package net.eni.gestion.pedagogie.resource.implementation;


import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;

import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import net.eni.gestion.pedagogie.commun.composant.authentification.annotation.CheckSession;
import net.eni.gestion.pedagogie.commun.composant.connexion.Connexion;
import net.eni.gestion.pedagogie.commun.composant.erreur.ApplicationException;
import net.eni.gestion.pedagogie.commun.modele.Utilisateur;
import net.eni.gestion.pedagogie.resource.UtilisateurResource;
import net.eni.gestion.pedagogie.service.UtilisateurService;

import com.google.inject.Inject;
import com.j256.ormlite.misc.TransactionManager;


/**
 * @author jollivier
 * Classe d'implémentation pour le module de gestion des utilisateurs
 */
@Path("/utilisateurs")
public class UtilisateurResourceImpl extends AResourceImpl<Utilisateur, Integer, UtilisateurService> implements UtilisateurResource {

    /**
     * Constructeur
     * @param utilisateurService
     */
    @Inject
    public UtilisateurResourceImpl(UtilisateurService utilisateurService) {
    	super(utilisateurService, Utilisateur.class);
    }

    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
	public Utilisateur getAuthentification(final Utilisateur utilisateur)
			throws ApplicationException {
    	try {
			return TransactionManager.callInTransaction(
					Connexion.getInstance().getConnexion(),
					new Callable<Utilisateur>() {
						public Utilisateur call()
								throws ApplicationException {
							Utilisateur lUtilisateur = service.authentifier(utilisateur.getLogin(), utilisateur.getMotPasse());
					    	if (null != lUtilisateur){
					    		HttpSession session = request.getSession(true);
					    		session.setAttribute("token", lUtilisateur.getToken());
					    		session.setMaxInactiveInterval(30*60);
					            return lUtilisateur;
					    	}
					    	throw new ApplicationException("Connexion refusée");
						}
					});
		} catch (SQLException e) {
			throw new ApplicationException(
					"Erreur lors de la lecture en base de données");
		}
	}

    @GET
    @Path("/formateurs/{search}")
    @Produces(MediaType.APPLICATION_JSON)
	@Override
    @CheckSession
	public List<Utilisateur> getFormateurs(final String pSearchText) throws ApplicationException {
    	try {
			return TransactionManager.callInTransaction(
					Connexion.getInstance().getConnexion(),
					new Callable<List<Utilisateur>>() {
						public List<Utilisateur> call()
								throws ApplicationException {
							return service.getFormateurs(pSearchText);
						}
					});
		} catch (SQLException e) {
			throw new ApplicationException(
					"Erreur lors de la lecture en base de données");
		}
	}
    
    
    @POST
    @Path("/loginwithtoken")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Utilisateur loginwithtoken(final Utilisateur utilisateur) throws ApplicationException{
    	try {
			return TransactionManager.callInTransaction(
					Connexion.getInstance().getConnexion(),
					new Callable<Utilisateur>() {
						public Utilisateur call()
								throws ApplicationException {
							String token = utilisateur.getToken();
					    	if (null == token){
					    		throw new ApplicationException("Connexion refusée");
					    	}else {
					    		Utilisateur lUtilisateur = service.authentifierAvecToken(token);
					    		if (null != lUtilisateur && null != lUtilisateur.getToken()){
					        		long expirationPeriod = (lUtilisateur.getDateExpiration().getTime()-new Date().getTime())/1000;
					        		HttpSession session = request.getSession(true);
					        		session.setAttribute("token", lUtilisateur.getToken());
					        		session.setMaxInactiveInterval((int)(expirationPeriod/60));
					        		return lUtilisateur;
					    			
					    		}else{
					        		throw new ApplicationException("Connexion refusée");

					    		}
					    	}
						}
					});
		} catch (SQLException e) {
			throw new ApplicationException(
					"Erreur lors de la lecture en base de données");
		}
    }
}
