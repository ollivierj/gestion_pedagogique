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
import net.eni.gestion.pedagogie.modele.TitreProfessionnel;
import net.eni.gestion.pedagogie.ressource.TitreProfessionnelRessource;
import net.eni.gestion.pedagogie.service.TitreProfessionnelService;
import com.google.inject.Inject;

/**
 * @author jollivier
 * Classe d'implémentation pour le module de gestion des titres professionnels
 */
@Path("/titreProfessionnels")
public class TitreProfessionnelRessourceImpl implements TitreProfessionnelRessource {

    /**
     * Unité métier titre professionnel
     */
    private final TitreProfessionnelService titreProfessionnelService;

    /**
     * Constructeur
     * @param TitreProfessionnelService
     */
    @Inject
    public TitreProfessionnelRessourceImpl(TitreProfessionnelService TitreProfessionnelService) {
        this.titreProfessionnelService = TitreProfessionnelService;
    }

    /* (non-Javadoc)
     * @see net.eni.gestion.pedagogie.service.contrat.generique.CRUDService#charger()
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<TitreProfessionnel> charger() throws GenericException {
        return titreProfessionnelService.charger(new TitreProfessionnel());
    }
        
	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.service.generique.CRUDService#chargerDetail(java.lang.Integer)
	 */
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
	public TitreProfessionnel chargerDetail(@PathParam("id") Integer id) throws GenericException {
		return titreProfessionnelService.chargerDetail(new TitreProfessionnel(id));

	}

    /* (non-Javadoc)
     * @see net.eni.gestion.pedagogie.service.contrat.generique.CRUDService#ajouter(net.eni.gestion.pedagogie.modele.AModele)
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public TitreProfessionnel ajouter(TitreProfessionnel pModel) throws GenericException {
		return this.titreProfessionnelService.ajouter(pModel);
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.service.contrat.generique.CRUDService#mettreAJour(net.eni.gestion.pedagogie.modele.AModele)
	 */
	@PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public TitreProfessionnel mettreAJour(TitreProfessionnel pModel) throws GenericException {
		return this.titreProfessionnelService.mettreAJour(pModel);
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.service.contrat.generique.CRUDService#supprimer(net.eni.gestion.pedagogie.modele.AModele)
	 */
	@DELETE
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public TitreProfessionnel supprimer(@PathParam("id") Integer id) throws GenericException {
		return this.titreProfessionnelService.supprimer(new TitreProfessionnel(id));
	}


}
