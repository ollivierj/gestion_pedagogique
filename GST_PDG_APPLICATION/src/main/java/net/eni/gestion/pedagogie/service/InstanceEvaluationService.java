package net.eni.gestion.pedagogie.service;

import java.util.List;

import net.eni.gestion.pedagogie.commun.composant.erreur.ApplicationException;
import net.eni.gestion.pedagogie.commun.modele.Evaluation;
import net.eni.gestion.pedagogie.commun.modele.InstanceEvaluation;

/**
 * @author jollivier
 * Interface métier pour le module de gestion des évaluations
 */
public interface InstanceEvaluationService extends AService<InstanceEvaluation,Integer> {

	/**
	 * Récupère les instances d'une évaluation
	 * @param evaluation Evaluation souhaitée
	 * @return Une liste d'instances d'évaluation
	 * @throws ApplicationException
	 */
	public List<InstanceEvaluation> getInstancesByEvaluation(Evaluation evaluation) throws ApplicationException;

}
