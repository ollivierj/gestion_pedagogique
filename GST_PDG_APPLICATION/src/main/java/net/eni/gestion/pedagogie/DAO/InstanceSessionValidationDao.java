package net.eni.gestion.pedagogie.DAO;

import net.eni.gestion.pedagogie.commun.modele.InstanceSessionValidation;

/**
 * @author jollivier
 * Description des requêtes d'accès (écriture, lecture) en base de données 
 * pour la gestion des instances de session de validation (une session dure une certaine période, une instance est une journé dans la période)
 */
public interface InstanceSessionValidationDao extends ADao<InstanceSessionValidation, Integer> {

	

}
