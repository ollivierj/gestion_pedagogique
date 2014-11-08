package net.eni.gestion.pedagogie.service;

import net.eni.gestion.pedagogie.commun.composant.erreur.ApplicationException;
import net.eni.gestion.pedagogie.commun.composant.instancePlanning.InstancePlanning;
import net.eni.gestion.pedagogie.commun.modele.InstanceSessionValidation;
import net.eni.gestion.pedagogie.commun.modele.SessionValidation;
import net.eni.gestion.pedagogie.commun.modele.SessionValidationStagiaire;

/**
 * @author jollivier
 * Interface métier pour le module de gestion des sessions de validation
 */
public interface SessionValidationService extends AService<SessionValidation, Integer> {

	/**
	 * Enregistre toutes les données liés aux instances (Réservation salles, nouvelles instances, stagiaires)
	 * @param instances
	 * @return
	 * @throws ApplicationException
	 */
	public SessionValidation saveInstanceData(InstancePlanning<InstanceSessionValidation, SessionValidationStagiaire> instances) throws ApplicationException;

}
