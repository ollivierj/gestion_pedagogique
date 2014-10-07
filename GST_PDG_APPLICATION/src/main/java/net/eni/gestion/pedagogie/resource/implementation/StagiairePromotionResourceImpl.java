package net.eni.gestion.pedagogie.resource.implementation;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import net.eni.gestion.pedagogie.commun.composant.GenericException;
import net.eni.gestion.pedagogie.modele.StagiairePromotion;
import net.eni.gestion.pedagogie.resource.StagiairePromotionResource;
import net.eni.gestion.pedagogie.service.StagiairePromotionService;

import com.google.inject.Inject;

@Path("/stagiairesPromotions")
public class StagiairePromotionResourceImpl extends AResourceImpl<StagiairePromotion, Integer, StagiairePromotionService> implements StagiairePromotionResource {

	/**
	 * Constructeur de la ressource stagiaire promotion
	 * @param stagiairePromotionService
	 */
	@Inject
	public StagiairePromotionResourceImpl(StagiairePromotionService stagiairePromotionService) {
		super(stagiairePromotionService, StagiairePromotion.class);
	}

    @GET
    @Path("/stagiaires/{type}/{id}")
    @Produces(MediaType.APPLICATION_JSON)
	public ArrayList<StagiairePromotion> chargerStagiaireOrPromotion(
			@PathParam("type") String type, @PathParam("id") String id) throws GenericException {
		switch (type) {
		case "Promotion":
			return service.chargerByPromotionId(id);
		case "Stagiaire":
			return service.chargerByStagiaireId(Integer.parseInt(id));
		}
		return null;
	}


}
