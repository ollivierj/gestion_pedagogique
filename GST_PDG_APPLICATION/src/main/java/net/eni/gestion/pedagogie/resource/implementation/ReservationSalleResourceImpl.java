package net.eni.gestion.pedagogie.resource.implementation;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Callable;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.inject.Inject;

import net.eni.gestion.pedagogie.commun.composant.connexion.Connexion;
import net.eni.gestion.pedagogie.commun.composant.erreur.ApplicationException;
import net.eni.gestion.pedagogie.commun.modele.ReservationSalle;
import net.eni.gestion.pedagogie.commun.modele.Salle;
import net.eni.gestion.pedagogie.resource.ReservationSalleResource;
import net.eni.gestion.pedagogie.service.ReservationSalleService;

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
    	try {
			return Connexion.getTransactionManager().callInTransaction(
					new Callable<List<Salle>>() {
						public List<Salle> call()
								throws ApplicationException {
							return service.getSalles();
						}
					});
		} catch (SQLException e) {
			throw new ApplicationException(
					"Erreur lors de la lecture en base de données");
		}
	}

}
