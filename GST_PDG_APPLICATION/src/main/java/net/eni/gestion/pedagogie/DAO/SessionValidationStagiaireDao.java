package net.eni.gestion.pedagogie.DAO;

import java.util.ArrayList;

import net.eni.gestion.pedagogie.commun.modele.SessionValidation;
import net.eni.gestion.pedagogie.commun.modele.SessionValidationStagiaire;

/**
 * @author jollivier
 * Description des requêtes d'accès (écriture, lecture) en base de données 
 * pour la gestion des instances de sessions de validation et des stagiaires associés
 */
public interface SessionValidationStagiaireDao extends ADao<SessionValidationStagiaire, Integer> {

	public ArrayList<SessionValidationStagiaire> mettreAJourCollectionStagiaireForSessionValidation(
			SessionValidation pSessionValidation,
			ArrayList<SessionValidationStagiaire> pSessionValidationStagiaires) throws Exception;
	

}
