package net.eni.gestion.pedagogie.resource;

import java.util.UUID;

import javax.ws.rs.PathParam;

import net.eni.gestion.pedagogie.commun.composant.erreur.ApplicationException;
import net.eni.gestion.pedagogie.commun.composant.instancePlanning.InstancePlanning;
import net.eni.gestion.pedagogie.commun.modele.Cours;
import net.eni.gestion.pedagogie.commun.modele.CoursStagiaire;
import net.eni.gestion.pedagogie.commun.modele.InstanceCours;

/**
 * @author jollivier
 * Interface service pour le module de suivi des absences
 */
public interface CoursResource extends AResource<Cours, UUID> {
	
	/**
	 * Enregistre les données liées aux instances de cours (Réservation salles, stagiaires inscrits ou non)
	 * @param instances
	 * @throws ApplicationException
	 */
	public void saveInstance(InstancePlanning<InstanceCours, CoursStagiaire> instances) throws ApplicationException;
	
	/**
	 * Récupère les informations liées aux cours
	 * @param pId
	 * @return
	 * @throws ApplicationException
	 */
	public Cours getData(@PathParam("id") String pId) throws ApplicationException;
}
