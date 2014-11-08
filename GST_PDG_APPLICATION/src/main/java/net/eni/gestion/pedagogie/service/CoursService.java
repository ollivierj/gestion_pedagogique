package net.eni.gestion.pedagogie.service;

import java.util.UUID;

import net.eni.gestion.pedagogie.commun.composant.erreur.ApplicationException;
import net.eni.gestion.pedagogie.commun.composant.instancePlanning.InstancePlanning;
import net.eni.gestion.pedagogie.commun.modele.Cours;
import net.eni.gestion.pedagogie.commun.modele.CoursStagiaire;
import net.eni.gestion.pedagogie.commun.modele.InstanceCours;

/**
 * @author jollivier
 * Interface métier pour le module de suivi des absences
 */
public interface CoursService extends AService<Cours,UUID> {

	/**
	 * Enregistre les données liées aux instances de cours (Réservation salles, stagiaires inscrits ou non)
	 * @param instances
	 * @return
	 * @throws ApplicationException
	 */
	public Cours saveInstanceData(InstancePlanning<InstanceCours, CoursStagiaire> instances) throws ApplicationException;
	
	/**
	 * Récupère les informations du cours.
	 * @param pId
	 * @return
	 * @throws ApplicationException
	 */
	public Cours getData(String pId) throws ApplicationException;

}
