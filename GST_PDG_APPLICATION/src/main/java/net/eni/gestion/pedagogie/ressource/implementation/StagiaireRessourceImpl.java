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
import net.eni.gestion.pedagogie.modele.Stagiaire;
import net.eni.gestion.pedagogie.ressource.StagiaireRessource;
import net.eni.gestion.pedagogie.service.StagiaireService;

import com.google.inject.Inject;

/**
 * @author jollivier
 * Classe d'implémentation pour le module de gestion des stagiaires
 */
@Path("/stagiaires")
public class StagiaireRessourceImpl implements StagiaireRessource {

    /**
     * Unité métier stagiaire
     */
    private final StagiaireService stagiaireService;

    /**
     * Constructeur
     * @param StagiaireService
     */
    @Inject
    public StagiaireRessourceImpl(StagiaireService StagiaireService) {
        this.stagiaireService = StagiaireService;
    }

    /* (non-Javadoc)
     * @see net.eni.gestion.pedagogie.service.contrat.generique.CRUDService#charger()
     */
    @GET
    @Path("/{page}/{pageSize}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Stagiaire> charger(@PathParam("page") int page, @PathParam("pageSize") int pageSize, String orderColumn, String orderDirection, String searchText) throws GenericException {
        return stagiaireService.charger(page, pageSize, Stagiaire.ID_FIELD_NAME, "ASC", "");
    }
        
	/* (non-Javadoc)St
	 * @see net.eni.gestion.pedagogie.service.generique.CRUDService#chargerDetail(java.lang.Integer)
	 */
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
	public Stagiaire chargerDetail(@PathParam("id") Integer pId) throws GenericException {
		return stagiaireService.chargerDetail(pId);

	}

    /* (non-Javadoc)
     * @see net.eni.gestion.pedagogie.service.contrat.generique.CRUDService#ajouter(net.eni.gestion.pedagogie.modele.AModele)
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public Stagiaire ajouter(Stagiaire pModel) throws GenericException {
		return this.stagiaireService.ajouter(pModel);
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.service.contrat.generique.CRUDService#mettreAJour(net.eni.gestion.pedagogie.modele.AModele)
	 */
	@PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public Stagiaire mettreAJour(Stagiaire pModel) throws GenericException {
		return this.stagiaireService.mettreAJour(pModel);
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.service.contrat.generique.CRUDService#supprimer(net.eni.gestion.pedagogie.modele.AModele)
	 */
	@DELETE
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Integer supprimer(@PathParam("id") Integer pId) throws GenericException {
		return this.stagiaireService.supprimer(pId);
	}
	
   



}
