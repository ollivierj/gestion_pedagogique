package net.eni.gestion.pedagogie.resource.implementation;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import net.eni.gestion.pedagogie.commun.composant.authentification.annotation.CheckSession;
import net.eni.gestion.pedagogie.commun.composant.erreur.ApplicationException;
import net.eni.gestion.pedagogie.commun.modele.Evaluation;
import net.eni.gestion.pedagogie.commun.modele.InstanceEvaluation;
import net.eni.gestion.pedagogie.resource.InstanceEvaluationResource;
import net.eni.gestion.pedagogie.service.InstanceEvaluationService;

import com.google.inject.Inject;

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
    public InstanceEvaluationResourceImpl(InstanceEvaluationService pInstanceSessionValidationService) {
    	super(pInstanceSessionValidationService, InstanceEvaluation.class);
    }

	@Override
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@CheckSession
	public List<InstanceEvaluation> getInstances(Evaluation evaluation) throws ApplicationException {
		return service.getInstancesByEvaluation(evaluation);
	}

}
