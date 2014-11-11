package net.eni.gestion.pedagogie.resource.implementation;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Callable;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import net.eni.gestion.pedagogie.commun.composant.authentification.annotation.CheckSession;
import net.eni.gestion.pedagogie.commun.composant.connexion.Connexion;
import net.eni.gestion.pedagogie.commun.composant.erreur.ApplicationException;
import net.eni.gestion.pedagogie.commun.modele.InstanceSessionValidation;
import net.eni.gestion.pedagogie.commun.modele.SessionValidation;
import net.eni.gestion.pedagogie.resource.InstanceSessionValidationResource;
import net.eni.gestion.pedagogie.service.InstanceSessionValidationService;

import com.google.inject.Inject;
import com.j256.ormlite.misc.TransactionManager;

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
			final SessionValidation sessionValidation) throws ApplicationException {
		try {
			return TransactionManager.callInTransaction(
					Connexion.getInstance().getConnexion(),
					new Callable<List<InstanceSessionValidation>>() {
						public List<InstanceSessionValidation> call()
								throws ApplicationException {
							return service.getInstancesBySession(sessionValidation);
						}
					});
		} catch (SQLException e) {
			throw new ApplicationException(
					"Erreur lors de la lecture en base de données");
		}
	}

}
