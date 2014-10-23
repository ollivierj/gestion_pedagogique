package net.eni.gestion.pedagogie.resource.implementation;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import net.eni.gestion.pedagogie.commun.composant.GenericException;
import net.eni.gestion.pedagogie.modele.Utilisateur;
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
    @AuthenticationNotRequired
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
	public Utilisateur getAuthentification(Utilisateur utilisateur)
			throws GenericException {
    	return service.authentifier(utilisateur);
	}
}
