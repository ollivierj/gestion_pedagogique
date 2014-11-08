package net.eni.gestion.pedagogie.resource.implementation;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import net.eni.gestion.pedagogie.commun.composant.authentification.annotation.CheckSession;
import net.eni.gestion.pedagogie.commun.composant.erreur.ApplicationException;
import net.eni.gestion.pedagogie.commun.modele.InstanceSessionValidation;
import net.eni.gestion.pedagogie.commun.modele.SessionValidation;
import net.eni.gestion.pedagogie.resource.InstanceSessionValidationResource;
import net.eni.gestion.pedagogie.service.InstanceSessionValidationService;

import com.google.inject.Inject;

/**
 * @author jollivier
 * Classe d'implémentation pour le module de gestion des evaluations
 */
@Path("/instanceSessionValidation")
public class InstanceSessionValidationResourceImpl extends AResourceImpl<InstanceSessionValidation, Integer, InstanceSessionValidationService> implements InstanceSessionValidationResource {

    /**
     * Constructeur
     * @param evaluationService
     */
    @Inject
    public InstanceSessionValidationResourceImpl(InstanceSessionValidationService pInstanceSessionValidationService) {
    	super(pInstanceSessionValidationService, InstanceSessionValidation.class);
    }

	@Override
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@CheckSession
	public List<InstanceSessionValidation> getInstances(
			SessionValidation sessionValidation) throws ApplicationException {
		return service.getInstancesBySession(sessionValidation);
	}

}
