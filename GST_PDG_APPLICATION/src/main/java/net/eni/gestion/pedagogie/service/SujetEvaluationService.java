package net.eni.gestion.pedagogie.service;

import java.util.HashMap;

import net.eni.gestion.pedagogie.errorhandling.ApplicationException;
import net.eni.gestion.pedagogie.modele.SujetEvaluation;

/**
 * @author jollivier
 * Interface métier pour le module de gestion des évaluations
 */
public interface SujetEvaluationService extends AService<SujetEvaluation,Integer> {

	HashMap<String, String> getTitleMap() throws ApplicationException;
	
}
