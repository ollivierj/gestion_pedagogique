package net.eni.gestion.pedagogie.DAO;

import java.util.List;

import net.eni.gestion.pedagogie.commun.composant.erreur.ApplicationException;
import net.eni.gestion.pedagogie.commun.modele.Evaluation;
import net.eni.gestion.pedagogie.commun.modele.InstanceEvaluation;

/**
 * @author jollivier
 * Description des requêtes d'accès (écriture, lecture) en base de données 
 * pour la gestion des instances d'évaluation (ECF, Epreuves finales)
 */
public interface InstanceEvaluationDao extends ADao<InstanceEvaluation, Integer> {

	/**
	 * Récupère les instances d'une évaluation
	 * @param evaluation Evaluation souhaitée
	 * @return Une liste d'instances d'évaluation
	 * @throws ApplicationException
	 */
	public List<InstanceEvaluation> getInstancesByEvaluation(Evaluation evaluation) throws ApplicationException;

}
