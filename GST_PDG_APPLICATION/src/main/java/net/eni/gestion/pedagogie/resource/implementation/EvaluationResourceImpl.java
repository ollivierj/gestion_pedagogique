package net.eni.gestion.pedagogie.resource.implementation;

import javax.ws.rs.Path;

import net.eni.gestion.pedagogie.modele.Evaluation;
import net.eni.gestion.pedagogie.resource.EvaluationResource;
import net.eni.gestion.pedagogie.service.EvaluationService;

import com.google.inject.Inject;

/**
 * @author jollivier
 * Classe d'implémentation pour le module de gestion des evaluations
 */
@Path("/sujetEvaluations")
public class EvaluationResourceImpl extends AResourceImpl<Evaluation, Integer, EvaluationService> implements EvaluationResource {

    /**
     * Constructeur
     * @param evaluationService
     */
    @Inject
    public EvaluationResourceImpl(EvaluationService evaluationService) {
    	super(evaluationService, Evaluation.class);
    }

}
