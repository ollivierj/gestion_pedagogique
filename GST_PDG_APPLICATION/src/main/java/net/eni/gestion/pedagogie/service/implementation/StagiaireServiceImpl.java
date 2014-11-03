package net.eni.gestion.pedagogie.service.implementation;

import java.sql.SQLException;
import java.util.ArrayList;

import net.eni.gestion.pedagogie.DAO.AbsenceDao;
import net.eni.gestion.pedagogie.DAO.AvisDao;
import net.eni.gestion.pedagogie.DAO.EchangeDao;
import net.eni.gestion.pedagogie.DAO.PromotionDao;
import net.eni.gestion.pedagogie.DAO.StagiaireDao;
import net.eni.gestion.pedagogie.commun.composant.erreur.ApplicationException;
import net.eni.gestion.pedagogie.commun.modele.Promotion;
import net.eni.gestion.pedagogie.commun.modele.Stagiaire;
import net.eni.gestion.pedagogie.service.StagiaireService;

import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * @author jollivier
 * Classe d'implémentation pour le module de suivi des stagiaires
 */
@Singleton
public class StagiaireServiceImpl extends AServiceImpl<Stagiaire, Integer, StagiaireDao> implements StagiaireService {

	protected final AbsenceDao absenceDao;
	protected final EchangeDao echangeDao;
	protected final AvisDao avisDao;
	protected final PromotionDao promotionDao;
	
	/**
     * Constructeur
     * @param DAO stagiaire
     * @throws SQLException
     */
    @Inject
    public StagiaireServiceImpl(StagiaireDao pStagiaireDao, AbsenceDao absenceDao, EchangeDao echangeDao, AvisDao avisDao, PromotionDao promotionDao) throws SQLException {
        super(pStagiaireDao);
        this.absenceDao = absenceDao;
        this.echangeDao = echangeDao;
        this.avisDao = avisDao;
        this.promotionDao = promotionDao;
    }
    
    @Override
    public Stagiaire chargerDetail(Integer pId) throws ApplicationException {
    	Stagiaire stagiaire = super.chargerDetail(pId);
    	return stagiaire;
    }

	@Override
	public ArrayList<Promotion> chargerPromotionForAutocompleteSearch(
			String pSearchText) throws ApplicationException {
		try {
			return this.promotionDao.chargerForAutocompleteSearch(pSearchText);
		} catch (Exception e) {
			throw new ApplicationException("Echec lors du chargement depuis la base de données.");
		}
	}
}
