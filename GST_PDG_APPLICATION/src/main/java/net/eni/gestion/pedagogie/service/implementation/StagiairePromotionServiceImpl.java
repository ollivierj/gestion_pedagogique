package net.eni.gestion.pedagogie.service.implementation;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import net.eni.gestion.pedagogie.DAO.StagiairePromotionDao;
import net.eni.gestion.pedagogie.modele.StagiairePromotion;
import net.eni.gestion.pedagogie.service.StagiairePromotionService;

@Singleton
public class StagiairePromotionServiceImpl extends AServiceImpl<StagiairePromotion, Integer, StagiairePromotionDao> implements StagiairePromotionService {

	/**
	 * Constructeur du service
	 * @param pStagiairePromotionDao
	 */
	@Inject
	public StagiairePromotionServiceImpl(StagiairePromotionDao pStagiairePromotionDao) {
		super(pStagiairePromotionDao);
	}

}
	
