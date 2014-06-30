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
import net.eni.gestion.pedagogie.modele.Evaluation;
import net.eni.gestion.pedagogie.ressource.EvaluationRessource;
import net.eni.gestion.pedagogie.service.EvaluationService;

import com.google.inject.Inject;

/**
 * @author jollivier
 * Classe d'implémentation pour le module de gestion des evaluations
 */
@Path("/evaluations")
public class EvaluationRessourceImpl implements EvaluationRessource {

    /**
     * Unité métier evaluation
     */
    private final EvaluationService evaluationService;

    /**
     * Constructeur
     * @param EvaluationService
     */
    @Inject
    public EvaluationRessourceImpl(EvaluationService EvaluationService) {
        this.evaluationService = EvaluationService;
    }

    /* (non-Javadoc)
     * @see net.eni.gestion.pedagogie.service.contrat.generique.CRUDService#charger()
     */
    @GET
    @Path("/{page}/{pageSize}/{orderBy}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Evaluation> charger(int page, int pageSize, String orderColumn, String orderDirection, String searchText) throws GenericException {
        return evaluationService.charger(page, pageSize, orderColumn, orderDirection, searchText);
    }
        
	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.service.generique.CRUDService#chargerDetail(java.lang.Integer)
	 */
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
	public Evaluation chargerDetail(@PathParam("id") Integer pId) throws GenericException {
		return evaluationService.chargerDetail(pId);

	}

    /* (non-Javadoc)
     * @see net.eni.gestion.pedagogie.service.contrat.generique.CRUDService#ajouter(net.eni.gestion.pedagogie.modele.AModele)
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public Evaluation ajouter(Evaluation pModel) throws GenericException {
		return this.evaluationService.ajouter(pModel);
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.service.contrat.generique.CRUDService#mettreAJour(net.eni.gestion.pedagogie.modele.AModele)
	 */
	@PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public Evaluation mettreAJour(Evaluation pModel) throws GenericException {
		return this.evaluationService.mettreAJour(pModel);
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.service.contrat.generique.CRUDService#supprimer(net.eni.gestion.pedagogie.modele.AModele)
	 */
	@DELETE
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Integer supprimer(@PathParam("id") Integer pId) throws GenericException {
		return this.evaluationService.supprimer(pId);
	}
	
   



}
