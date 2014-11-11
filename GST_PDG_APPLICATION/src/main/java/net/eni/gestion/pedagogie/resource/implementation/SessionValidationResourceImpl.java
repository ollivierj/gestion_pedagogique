package net.eni.gestion.pedagogie.resource.implementation;

import java.sql.SQLException;
import java.util.concurrent.Callable;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import net.eni.gestion.pedagogie.commun.composant.authentification.annotation.CheckSession;
import net.eni.gestion.pedagogie.commun.composant.connexion.Connexion;
import net.eni.gestion.pedagogie.commun.composant.erreur.ApplicationException;
import net.eni.gestion.pedagogie.commun.composant.instancePlanning.InstancePlanning;
import net.eni.gestion.pedagogie.commun.modele.InstanceSessionValidation;
import net.eni.gestion.pedagogie.commun.modele.SessionValidation;
import net.eni.gestion.pedagogie.commun.modele.SessionValidationStagiaire;
import net.eni.gestion.pedagogie.resource.SessionValidationResource;
import net.eni.gestion.pedagogie.service.SessionValidationService;

import com.google.inject.Inject;
import com.j256.ormlite.misc.TransactionManager;

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
	@POST
	@Path("/instance")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@CheckSession
	public void saveInstance(
			final InstancePlanning<InstanceSessionValidation, SessionValidationStagiaire> instances
			) throws ApplicationException {
		try {
			TransactionManager.callInTransaction(
					Connexion.getInstance().getConnexion(),
					new Callable<Void>() {
						public Void call()
								throws ApplicationException {
							service.saveInstanceData(instances);
							return null;
						}
					});
		} catch (SQLException e) {
			throw new ApplicationException(
					"Erreur lors de la lecture en base de données");
		}
	}
}
