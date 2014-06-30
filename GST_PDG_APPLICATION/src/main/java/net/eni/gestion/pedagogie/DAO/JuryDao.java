package net.eni.gestion.pedagogie.DAO;

import net.eni.gestion.pedagogie.DAO.generique.CRUDDao;
import net.eni.gestion.pedagogie.modele.Jury;

/**
 * @author jollivier
 * Description des requêtes d'accès (écriture, lecture) en base de données 
 * pour la gestion des membres d'un jury pour une instance de session de validation
 */
public interface JuryDao extends CRUDDao<Jury, Integer> {

	

}
