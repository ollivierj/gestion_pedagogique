package net.eni.gestion.pedagogie.ressource.implementation;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import net.eni.gestion.pedagogie.commun.composant.GenericException;
import net.eni.gestion.pedagogie.modele.SessionValidation;
import net.eni.gestion.pedagogie.ressource.SessionValidationRessource;
import net.eni.gestion.pedagogie.service.SessionValidationService;
import com.google.inject.Inject;

/**
 * @author jollivier
 * Classe d'implémentation pour le module de gestion des sessions de validation
 */
@Path("/stagiaires")
public class SessionValidationRessourceImpl implements SessionValidationRessource {

    /**
     * Unité métier session validation
     */
    private final SessionValidationService stagiaireService;

    /**
     * Constructeur
     * @param SessionValidationService
     */
    @Inject
    public SessionValidationRessourceImpl(SessionValidationService SessionValidationService) {
        this.stagiaireService = SessionValidationService;
    }

    /* (non-Javadoc)
     * @see net.eni.gestion.pedagogie.service.contrat.generique.CRUDService#charger()
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<SessionValidation> charger() throws GenericException {
        return stagiaireService.charger(new SessionValidation());
    }
        
	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.service.generique.CRUDService#chargerDetail(java.lang.Integer)
	 */
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
	public SessionValidation chargerDetail(@PathParam("id") Integer id) throws GenericException {
		return stagiaireService.chargerDetail(new SessionValidation(id));

	}

    /* (non-Javadoc)
     * @see net.eni.gestion.pedagogie.service.contrat.generique.CRUDService#ajouter(net.eni.gestion.pedagogie.modele.AModele)
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public SessionValidation ajouter(SessionValidation pModel) throws GenericException {
		return this.stagiaireService.ajouter(pModel);
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.service.contrat.generique.CRUDService#mettreAJour(net.eni.gestion.pedagogie.modele.AModele)
	 */
	@PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public SessionValidation mettreAJour(SessionValidation pModel) throws GenericException {
		return this.stagiaireService.mettreAJour(pModel);
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.service.contrat.generique.CRUDService#supprimer(net.eni.gestion.pedagogie.modele.AModele)
	 */
	@DELETE
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public SessionValidation supprimer(@PathParam("id") Integer id) throws GenericException {
		return this.stagiaireService.supprimer(new SessionValidation(id));
	}


}
