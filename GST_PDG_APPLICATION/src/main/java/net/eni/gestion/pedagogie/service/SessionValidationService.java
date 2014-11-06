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
	 * Récupère les données nécessaire aux planifications des sessions de validation 
	 * @param id
	 * @return Retourne les données sous forme de map
	 * @throws GenericException
	 */
	public SessionValidation getInstanceData(Integer id) throws ApplicationException;
	
	public SessionValidation saveInstanceData(InstancePlanning<InstanceSessionValidation, SessionValidationStagiaire> instances) throws ApplicationException;

}
