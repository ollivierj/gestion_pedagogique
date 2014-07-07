package net.eni.gestion.pedagogie.resource.implementation;

import javax.ws.rs.Path;

import net.eni.gestion.pedagogie.modele.ReservationSalle;
import net.eni.gestion.pedagogie.resource.ReservationSalleResource;
import net.eni.gestion.pedagogie.service.ReservationSalleService;

import com.google.inject.Inject;

/**
 * @author jollivier
 * Classe d'implémentation pour le module de gestion des reservationSalles
 */
@Path("/reservationSalles")
public class ReservationSalleResourceImpl extends AResourceImpl<ReservationSalle, Integer, ReservationSalleService> implements ReservationSalleResource {

    /**
     * Constructeur
     * @param reservationSalleService
     */
    @Inject
    public ReservationSalleResourceImpl(ReservationSalleService reservationSalleService) {
    	super(reservationSalleService, ReservationSalle.class);
    }

}
