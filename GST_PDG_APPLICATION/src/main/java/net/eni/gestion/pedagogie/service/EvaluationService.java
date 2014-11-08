package net.eni.gestion.pedagogie.service;

import net.eni.gestion.pedagogie.commun.composant.erreur.ApplicationException;
import net.eni.gestion.pedagogie.commun.composant.instancePlanning.InstancePlanning;
import net.eni.gestion.pedagogie.commun.modele.Evaluation;
import net.eni.gestion.pedagogie.commun.modele.EvaluationStagiaire;
import net.eni.gestion.pedagogie.commun.modele.InstanceEvaluation;

/**
 * @author jollivier
 * Interface métier pour le module de gestion des évaluations
 */
public interface EvaluationService extends AService<Evaluation,Integer> {

	public Evaluation saveInstanceData(InstancePlanning<InstanceEvaluation, EvaluationStagiaire> instances) throws ApplicationException;

}
