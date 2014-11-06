package net.eni.gestion.pedagogie.DAO;

import java.util.List;

import net.eni.gestion.pedagogie.commun.modele.InstanceSessionValidation;
import net.eni.gestion.pedagogie.commun.modele.SessionValidation;

/**
 * @author jollivier
 * Description des requêtes d'accès (écriture, lecture) en base de données 
 * pour la gestion des instances de session de validation (une session dure une certaine période, une instance est une journé dans la période)
 */
public interface InstanceSessionValidationDao extends ADao<InstanceSessionValidation, Integer> {

	/**
	 * Récupère les instances d'une session de validation
	 * @param sessionValidation Session de validation souhaitée
	 * @return Une liste d'instances de session de validation
	 * @throws Exception
	 */
	public List<InstanceSessionValidation> getInstancesBySession(SessionValidation sessionValidation) throws Exception;

}
