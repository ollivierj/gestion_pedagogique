package net.eni.gestion.pedagogie.resource;

import net.eni.gestion.pedagogie.commun.composant.erreur.ApplicationException;
import net.eni.gestion.pedagogie.commun.composant.instancePlanning.InstancePlanning;
import net.eni.gestion.pedagogie.commun.modele.Evaluation;
import net.eni.gestion.pedagogie.commun.modele.EvaluationStagiaire;
import net.eni.gestion.pedagogie.commun.modele.InstanceEvaluation;

/**
 * @author jollivier
 * Interface service pour le module de suivi des evaluations
 */
public interface EvaluationResource extends AResource<Evaluation, Integer> {

	
	public void saveInstance(InstancePlanning<InstanceEvaluation, EvaluationStagiaire> instances) throws ApplicationException;
}
