package net.eni.gestion.pedagogie.service.implementation;

import java.util.ArrayList;

import net.eni.gestion.pedagogie.DAO.StagiairePromotionDao;
import net.eni.gestion.pedagogie.commun.composant.erreur.ApplicationException;
import net.eni.gestion.pedagogie.commun.modele.StagiairePromotion;
import net.eni.gestion.pedagogie.service.StagiairePromotionService;

import com.google.inject.Inject;
import com.google.inject.Singleton;

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
	
	 public ArrayList<StagiairePromotion> chargerByStagiaireId(Integer id) throws ApplicationException {
	        try {
				return dao.chargerByStagiaireId(id);
			} catch (Exception e) {
				throw new ApplicationException("Echec lors du chargement depuis la base de données.");
			}
	    }
	 
	 public ArrayList<StagiairePromotion> chargerByPromotionId(String id) throws ApplicationException {
	        try {
				return dao.chargerByPromotionId(id);
			} catch (Exception e) {
				throw new ApplicationException("Echec lors du chargement depuis la base de données.");
			}
	    }

}
	
