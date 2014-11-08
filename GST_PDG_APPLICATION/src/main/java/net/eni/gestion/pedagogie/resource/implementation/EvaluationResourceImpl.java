package net.eni.gestion.pedagogie.resource.implementation;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import net.eni.gestion.pedagogie.commun.composant.authentification.annotation.CheckSession;
import net.eni.gestion.pedagogie.commun.composant.erreur.ApplicationException;
import net.eni.gestion.pedagogie.commun.composant.instancePlanning.InstancePlanning;
import net.eni.gestion.pedagogie.commun.modele.Evaluation;
import net.eni.gestion.pedagogie.commun.modele.EvaluationStagiaire;
import net.eni.gestion.pedagogie.commun.modele.InstanceEvaluation;
import net.eni.gestion.pedagogie.resource.EvaluationResource;
import net.eni.gestion.pedagogie.service.EvaluationService;

import com.google.inject.Inject;

/**
 * @author jollivier
 * Classe d'implémentation pour le module de gestion des evaluations
 */
@Path("/evaluations")
public class EvaluationResourceImpl extends AResourceImpl<Evaluation, Integer, EvaluationService> implements EvaluationResource {

    /**
     * Constructeur
     * @param evaluationService
     */
    @Inject
    public EvaluationResourceImpl(EvaluationService evaluationService) {
    	super(evaluationService, Evaluation.class);
    }

	@Override
	@POST
	@Path("/instance")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@CheckSession
	public void saveInstance(InstancePlanning<InstanceEvaluation, EvaluationStagiaire> instances) throws ApplicationException {
		service.saveInstanceData(instances);
	}
	
}
