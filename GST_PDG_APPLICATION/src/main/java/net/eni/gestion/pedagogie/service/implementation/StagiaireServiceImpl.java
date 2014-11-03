package net.eni.gestion.pedagogie.service.implementation;

import java.sql.SQLException;
import java.util.ArrayList;

import net.eni.gestion.pedagogie.DAO.PromotionDao;
import net.eni.gestion.pedagogie.DAO.StagiaireDao;
import net.eni.gestion.pedagogie.commun.composant.GenericException;
import net.eni.gestion.pedagogie.modele.Promotion;
import net.eni.gestion.pedagogie.modele.Stagiaire;
import net.eni.gestion.pedagogie.service.StagiaireService;

import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * @author jollivier
 * Classe d'implémentation pour le module de suivi des stagiaires
 */
@Singleton
public class StagiaireServiceImpl extends AServiceImpl<Stagiaire, Integer, StagiaireDao> implements StagiaireService {

	protected final PromotionDao promotionDao;
	
	/**
     * Constructeur
     * @param DAO stagiaire
     * @throws SQLException
     */
    @Inject
    public StagiaireServiceImpl(StagiaireDao pStagiaireDao, PromotionDao promotionDao) throws SQLException {
        super(pStagiaireDao);
        this.promotionDao = promotionDao;
    }
    
    @Override
    public Stagiaire chargerDetail(Integer pId) throws GenericException {
    	Stagiaire stagiaire = super.chargerDetail(pId);
    	return stagiaire;
    }

	@Override
	public ArrayList<Promotion> chargerPromotionForAutocompleteSearch(
			String pSearchText) throws GenericException {
		try {
			return this.promotionDao.chargerForAutocompleteSearch(pSearchText);
		} catch (Exception e) {
			throw new GenericException("Echec lors du chargement depuis la base de données.");
		}
	}
}
