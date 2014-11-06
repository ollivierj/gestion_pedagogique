package net.eni.gestion.pedagogie.resource.implementation;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import net.eni.gestion.pedagogie.commun.composant.erreur.ApplicationException;
import net.eni.gestion.pedagogie.commun.modele.ReservationSalle;
import net.eni.gestion.pedagogie.commun.modele.Salle;
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
    
    @GET
    @Path("/salles")
    @Produces(MediaType.APPLICATION_JSON)
	public List<Salle> chargerSalle() throws ApplicationException {
    	return service.getSalles();
	}

}
