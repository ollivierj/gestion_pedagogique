package net.eni.gestion.pedagogie.DAO;

import net.eni.gestion.pedagogie.commun.composant.erreur.ApplicationException;
import net.eni.gestion.pedagogie.commun.modele.SessionValidation;

/**
 * @author jollivier
 * Description des requêtes d'accès (écriture, lecture) en base de données 
 * pour la gestion des sessions de validation
 */
public interface SessionValidationDao extends ADao<SessionValidation, Integer> {
	
	public SessionValidation getInstance(Integer id) throws ApplicationException;

}
