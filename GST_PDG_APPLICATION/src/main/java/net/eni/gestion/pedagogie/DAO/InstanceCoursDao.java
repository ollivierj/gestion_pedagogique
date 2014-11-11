package net.eni.gestion.pedagogie.DAO;

import java.util.List;

import net.eni.gestion.pedagogie.commun.composant.erreur.ApplicationException;
import net.eni.gestion.pedagogie.commun.modele.Cours;
import net.eni.gestion.pedagogie.commun.modele.InstanceCours;

/**
 * @author jollivier
 * Description des requêtes d'accès (écriture, lecture) en base de données 
 * pour la gestion des instances de cours
 */
public interface InstanceCoursDao extends ADao<InstanceCours, Integer> {

	/**
	 * Récupère les instances d'un cours
	 * @param cours Cours souhaité
	 * @return Une liste d'instances de cours
	 * @throws ApplicationException
	 */
	public List<InstanceCours> getInstancesByCours(Cours cours) throws ApplicationException;

}
