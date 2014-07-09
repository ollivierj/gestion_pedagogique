package net.eni.gestion.pedagogie.resource.implementation;

import javax.ws.rs.Path;

import net.eni.gestion.pedagogie.modele.SessionValidation;
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

}
