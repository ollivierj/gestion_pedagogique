package net.eni.gestion.pedagogie.resource;

import net.eni.gestion.pedagogie.commun.composant.erreur.ApplicationException;
import net.eni.gestion.pedagogie.commun.composant.instancePlanning.InstancePlanning;
import net.eni.gestion.pedagogie.commun.modele.InstanceSessionValidation;
import net.eni.gestion.pedagogie.commun.modele.SessionValidation;
import net.eni.gestion.pedagogie.commun.modele.SessionValidationStagiaire;

/**
 * @author jollivier
 * Interface service pour le module de gestion des sessions de validation
 */
public interface SessionValidationResource extends AResource<SessionValidation, Integer> {
	
	/**
	 * Enregistrement des instances des session de validation
	 * @param instances
	 * @throws ApplicationException
	 */
	public void saveInstance(
			InstancePlanning<InstanceSessionValidation, SessionValidationStagiaire> instances
			) throws ApplicationException;

}
