package net.eni.gestion.pedagogie.ressource.implementation;

import javax.ws.rs.Path;

import net.eni.gestion.pedagogie.modele.ReservationSalle;
import net.eni.gestion.pedagogie.ressource.ReservationSalleRessource;
import net.eni.gestion.pedagogie.service.ReservationSalleService;

import com.google.inject.Inject;

/**
 * @author jollivier
 * Classe d'implémentation pour le module de gestion des reservationSalles
 */
@Path("/reservationSalles")
public class ReservationSalleRessourceImpl extends ARessourceImpl<ReservationSalle, Integer, ReservationSalleService> implements ReservationSalleRessource {

    /**
     * Constructeur
     * @param reservationSalleService
     */
    @Inject
    public ReservationSalleRessourceImpl(ReservationSalleService reservationSalleService) {
    	super(reservationSalleService);
    }

}
