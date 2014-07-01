package net.eni.gestion.pedagogie.ressource.implementation;

import javax.ws.rs.Path;

import net.eni.gestion.pedagogie.modele.Evaluation;
import net.eni.gestion.pedagogie.ressource.EvaluationRessource;
import net.eni.gestion.pedagogie.service.EvaluationService;

import com.google.inject.Inject;

/**
 * @author jollivier
 * Classe d'implémentation pour le module de gestion des evaluations
 */
@Path("/evaluations")
public class EvaluationRessourceImpl extends ARessourceImpl<Evaluation, Integer, EvaluationService> implements EvaluationRessource {

    /**
     * Constructeur
     * @param evaluationService
     */
    @Inject
    public EvaluationRessourceImpl(EvaluationService evaluationService) {
    	super(evaluationService);
    }

}
