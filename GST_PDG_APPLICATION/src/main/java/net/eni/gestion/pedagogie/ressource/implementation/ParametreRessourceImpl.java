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
import net.eni.gestion.pedagogie.modele.Parametre;
import net.eni.gestion.pedagogie.ressource.ParametreRessource;
import net.eni.gestion.pedagogie.service.ParametreService;
import com.google.inject.Inject;

/**
 * @author jollivier
 * Classe d'implémentation pour le module de paramètrage
 */
@Path("/parametres")
public class ParametreRessourceImpl implements ParametreRessource {

    /**
     * Unité métier parametre
     */
    private final ParametreService parametreService;

    /**
     * Constructeur
     * @param ParametreService
     */
    @Inject
    public ParametreRessourceImpl(ParametreService ParametreService) {
        this.parametreService = ParametreService;
    }

    /* (non-Javadoc)
     * @see net.eni.gestion.pedagogie.service.contrat.generique.CRUDService#charger()
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Parametre> charger() throws GenericException {
        return parametreService.charger(new Parametre());
    }
        
	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.service.generique.CRUDService#chargerDetail(java.lang.Integer)
	 */
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
	public Parametre chargerDetail(@PathParam("id") String id) throws GenericException {
		return parametreService.chargerDetail(new Parametre(id));

	}

    /* (non-Javadoc)
     * @see net.eni.gestion.pedagogie.service.contrat.generique.CRUDService#ajouter(net.eni.gestion.pedagogie.modele.AModele)
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public Parametre ajouter(Parametre pModel) throws GenericException {
		return this.parametreService.ajouter(pModel);
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.service.contrat.generique.CRUDService#mettreAJour(net.eni.gestion.pedagogie.modele.AModele)
	 */
	@PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public Parametre mettreAJour(Parametre pModel) throws GenericException {
		return this.parametreService.mettreAJour(pModel);
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.service.contrat.generique.CRUDService#supprimer(net.eni.gestion.pedagogie.modele.AModele)
	 */
	@DELETE
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Parametre supprimer(@PathParam("id") String id) throws GenericException {
		return this.parametreService.supprimer(new Parametre(id));
	}


}