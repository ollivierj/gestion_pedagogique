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
import net.eni.gestion.pedagogie.commun.modele.Evaluation;
import net.eni.gestion.pedagogie.commun.modele.InstanceEvaluation;
import net.eni.gestion.pedagogie.resource.InstanceEvaluationResource;
import net.eni.gestion.pedagogie.service.InstanceEvaluationService;

import com.google.inject.Inject;
import net.eni.gestion.pedagogie.commun.composant.connexion.TransactionManager;

/**
 * @author jollivier
 * Classe d'implémentation pour le module de gestion des evaluations
 */
@Path("/instanceEvaluation")
public class InstanceEvaluationResourceImpl extends AResourceImpl<InstanceEvaluation, Integer, InstanceEvaluationService> implements InstanceEvaluationResource {

    /**
     * Constructeur
     * @param evaluationService
     */
    @Inject
    public InstanceEvaluationResourceImpl(InstanceEvaluationService pInstanceSessionValidationService, Connexion pConnexion) {
    	super(pInstanceSessionValidationService, InstanceEvaluation.class, pConnexion);
    }

	@Override
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@CheckSession
	public List<InstanceEvaluation> getInstances(final Evaluation evaluation) throws ApplicationException {
		try {
			return TransactionManager.callInTransaction(
					connexion.getConnection(),
					new Callable<List<InstanceEvaluation>>() {
						public List<InstanceEvaluation> call()
								throws ApplicationException {
							return service.getInstancesByEvaluation(evaluation);
						}
					});
		} catch (SQLException e) {
			throw new ApplicationException(
					"Erreur lors de la lecture en base de données");
		}
	}

}
