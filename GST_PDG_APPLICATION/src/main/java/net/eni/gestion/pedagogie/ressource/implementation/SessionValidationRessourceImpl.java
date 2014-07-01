package net.eni.gestion.pedagogie.ressource.implementation;

import javax.ws.rs.Path;

import net.eni.gestion.pedagogie.modele.SessionValidation;
import net.eni.gestion.pedagogie.ressource.SessionValidationRessource;
import net.eni.gestion.pedagogie.service.SessionValidationService;

import com.google.inject.Inject;

/**
 * @author jollivier
 * Classe d'implémentation pour le module de gestion des sessionValidations
 */
@Path("/sessionValidations")
public class SessionValidationRessourceImpl extends ARessourceImpl<SessionValidation, Integer, SessionValidationService> implements SessionValidationRessource {

    /**
     * Constructeur
     * @param sessionValidationService
     */
    @Inject
    public SessionValidationRessourceImpl(SessionValidationService sessionValidationService) {
    	super(sessionValidationService);
    }

}
