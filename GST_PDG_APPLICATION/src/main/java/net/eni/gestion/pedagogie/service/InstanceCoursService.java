package net.eni.gestion.pedagogie.service;

import java.util.List;

import net.eni.gestion.pedagogie.commun.composant.erreur.ApplicationException;
import net.eni.gestion.pedagogie.commun.modele.Cours;
import net.eni.gestion.pedagogie.commun.modele.InstanceCours;

/**
 * @author jollivier
 * Interface métier pour le module de gestion des évaluations
 */
public interface InstanceCoursService extends AService<InstanceCours,Integer> {

	/**
	 * Récupère toutes les instances de cours lié à un cours.
	 * @param cours Cours choisi
	 * @return
	 * @throws ApplicationException
	 */
	public List<InstanceCours> getInstancesByCours(Cours cours) throws ApplicationException;

}
