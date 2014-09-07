package net.eni.gestion.pedagogie.resource.implementation;

import javax.ws.rs.Path;

import com.google.inject.Inject;

import net.eni.gestion.pedagogie.modele.StagiairePromotion;
import net.eni.gestion.pedagogie.resource.StagiairePromotionResource;
import net.eni.gestion.pedagogie.service.StagiairePromotionService;

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

}
