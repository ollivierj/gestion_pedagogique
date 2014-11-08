package net.eni.gestion.pedagogie.resource;

import java.util.List;

import net.eni.gestion.pedagogie.commun.composant.erreur.ApplicationException;
import net.eni.gestion.pedagogie.commun.modele.Evaluation;
import net.eni.gestion.pedagogie.commun.modele.InstanceEvaluation;

/**
 * @author jollivier
 * Interface service pour le module de suivi des evaluations
 */
public interface InstanceEvaluationResource extends AResource<InstanceEvaluation, Integer> {

	public List<InstanceEvaluation> getInstances(Evaluation evaluation) throws ApplicationException;
	
}
