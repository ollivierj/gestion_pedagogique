package net.eni.gestion.pedagogie.ressource.implementation;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import net.eni.gestion.pedagogie.commun.composant.GenericException;
import net.eni.gestion.pedagogie.modele.ReservationSalle;
import net.eni.gestion.pedagogie.ressource.ReservationSalleRessource;
import net.eni.gestion.pedagogie.service.ReservationSalleService;
import com.google.inject.Inject;

/**
 * @author jollivier
 * Classe d'implémentation pour le module de reservation des salles
 */
@Path("/reservationSalles")
public class ReservationSalleRessourceImpl implements ReservationSalleRessource {

    /**
     * Unité métier reservation des salles
     */
    private final ReservationSalleService reservationSalleService;

    /**
     * Constructeur
     * @param ReservationSalleService
     */
    @Inject
    public ReservationSalleRessourceImpl(ReservationSalleService ReservationSalleService) {
        this.reservationSalleService = ReservationSalleService;
    }

    /* (non-Javadoc)
     * @see net.eni.gestion.pedagogie.service.contrat.generique.CRUDService#charger()
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ReservationSalle> charger() throws GenericException {
        return reservationSalleService.charger(new ReservationSalle());
    }
        
	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.service.generique.CRUDService#chargerDetail(java.lang.Integer)
	 */
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
	public ReservationSalle chargerDetail(@PathParam("id") Integer id) throws GenericException {
		return reservationSalleService.chargerDetail(new ReservationSalle(id));

	}

    /* (non-Javadoc)
     * @see net.eni.gestion.pedagogie.service.contrat.generique.CRUDService#ajouter(net.eni.gestion.pedagogie.modele.AModele)
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public ReservationSalle ajouter(ReservationSalle pModel) throws GenericException {
		return this.reservationSalleService.ajouter(pModel);
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.service.contrat.generique.CRUDService#mettreAJour(net.eni.gestion.pedagogie.modele.AModele)
	 */
	@PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public ReservationSalle mettreAJour(ReservationSalle pModel) throws GenericException {
		return this.reservationSalleService.mettreAJour(pModel);
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.service.contrat.generique.CRUDService#supprimer(net.eni.gestion.pedagogie.modele.AModele)
	 */
	@DELETE
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ReservationSalle supprimer(@PathParam("id") Integer id) throws GenericException {
		return this.reservationSalleService.supprimer(new ReservationSalle(id));
	}


}
