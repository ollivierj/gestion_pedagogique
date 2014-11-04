package net.eni.gestion.pedagogie.resource.implementation;

import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import net.eni.gestion.pedagogie.commun.composant.erreur.ApplicationException;
import net.eni.gestion.pedagogie.commun.modele.InstanceSessionValidation;
import net.eni.gestion.pedagogie.commun.modele.SessionValidationStagiaire;
import net.eni.gestion.pedagogie.commun.modele.StagiairePromotion;
import net.eni.gestion.pedagogie.commun.modele.SessionValidation;
import net.eni.gestion.pedagogie.resource.SessionValidationResource;
import net.eni.gestion.pedagogie.service.SessionValidationService;

import com.google.inject.Inject;

/**
 * @author jollivier
 * Classe d'implémentation pour le module de gestion des sessionValidations
 */
@Path("/sessionValidations")
public class SessionValidationResourceImpl extends AResourceImpl<SessionValidation, Integer, SessionValidationService> implements SessionValidationResource {

    /**
     * Constructeur
     * @param sessionValidationService
     */
    @Inject
    public SessionValidationResourceImpl(SessionValidationService sessionValidationService) {
    	super(sessionValidationService, SessionValidation.class);
    }
    
    @Override
    @GET
    @Path("/instance/{id}")
    @Produces(MediaType.APPLICATION_JSON)
	public SessionValidation getInstance(@PathParam("id") Integer id) throws ApplicationException {
    	return service.getInstanceData(id);
	}

	@Override
	@POST
	@Path("/instance")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void saveInstance(
			Map<InstanceSessionValidation, List<StagiairePromotion>> instancesSessions,
			List<SessionValidationStagiaire> stagiaireLibres) throws ApplicationException {
		System.out.println(instancesSessions);
		System.out.println(stagiaireLibres);
	}
}
