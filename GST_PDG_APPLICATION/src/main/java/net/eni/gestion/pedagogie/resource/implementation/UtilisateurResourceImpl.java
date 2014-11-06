package net.eni.gestion.pedagogie.resource.implementation;

import java.util.Date;

import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import net.eni.gestion.pedagogie.commun.composant.erreur.ApplicationException;
import net.eni.gestion.pedagogie.commun.modele.Utilisateur;
import net.eni.gestion.pedagogie.resource.UtilisateurResource;
import net.eni.gestion.pedagogie.service.UtilisateurService;

import com.google.inject.Inject;


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
	public Utilisateur getAuthentification(Utilisateur utilisateur)
			throws ApplicationException {
    	Utilisateur lUtilisateur = service.authentifier(utilisateur.getLogin(), utilisateur.getMotPasse());
    	if (null != lUtilisateur){
    		HttpSession session = this.request.getSession(true);
    		session.setAttribute("token", lUtilisateur.getToken());
    		session.setMaxInactiveInterval(30*60);
            return lUtilisateur;
    	}
    	throw new ApplicationException("Connexion refusée");
	}
    
    
    @POST
    @Path("/loginwithtoken")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Utilisateur loginwithtoken(Utilisateur utilisateur) throws ApplicationException{
    	String token = utilisateur.getToken();
    	if (null == token){
    		throw new ApplicationException("Connexion refusée");
    	}else {
    		Utilisateur lUtilisateur = service.authentifierAvecToken(token);
    		if (null != lUtilisateur && null != lUtilisateur.getToken()){
        		long expirationPeriod = (lUtilisateur.getDateExpiration().getTime()-new Date().getTime())/1000;
        		HttpSession session = this.request.getSession(true);
        		session.setAttribute("token", lUtilisateur.getToken());
        		session.setMaxInactiveInterval((int)(expirationPeriod/60));
        		return lUtilisateur;
    			
    		}else{
        		throw new ApplicationException("Connexion refusée");

    		}
    	}
    }
}
