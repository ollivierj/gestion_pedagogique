package net.eni.gestion.pedagogie.service;

import java.util.List;

import net.eni.gestion.pedagogie.commun.composant.erreur.ApplicationException;
import net.eni.gestion.pedagogie.commun.modele.InstanceSessionValidation;
import net.eni.gestion.pedagogie.commun.modele.SessionValidation;

/**
 * @author jollivier
 * Interface métier pour le module de gestion des évaluations
 */
public interface InstanceSessionValidationService extends AService<InstanceSessionValidation,Integer> {

	/**
	 * Récupère les instances d'une session de validation
	 * @param sessionValidation Session de validation souhaitée
	 * @return Une liste d'instances de session de validation
	 * @throws ApplicationException
	 */
	public List<InstanceSessionValidation> getInstancesBySession(SessionValidation sessionValidation) throws ApplicationException;

}
