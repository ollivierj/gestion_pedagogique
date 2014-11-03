package net.eni.gestion.pedagogie.service;

import java.util.List;

import net.eni.gestion.pedagogie.commun.composant.GenericException;
import net.eni.gestion.pedagogie.modele.ReservationSalle;
import net.eni.gestion.pedagogie.modele.Salle;

/**
 * @author jollivier
 * Interface métier pour le module de réservation des salles
 */
public interface ReservationSalleService extends AService<ReservationSalle, Integer> {

	/**
	 * Récupération des salles
	 * @return salles
	 * @throws GenericException
	 */
	public List<Salle> getSalles() throws GenericException;
}
