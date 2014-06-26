package net.eni.gestion.pedagogie.DAO.implementation;

import java.sql.SQLException;
import java.util.ArrayList;

import net.eni.gestion.pedagogie.DAO.PromotionDao;
import net.eni.gestion.pedagogie.commun.composant.Connexion;
import net.eni.gestion.pedagogie.commun.outil.CRUDHelper;
import net.eni.gestion.pedagogie.modele.Promotion;

import com.google.inject.Singleton;
import com.j256.ormlite.dao.BaseDaoImpl;

/**
 * @author jollivier
 * Service métier "Promotion"
 */
@Singleton
public class PromotionDaoImpl extends BaseDaoImpl<Promotion, String> implements PromotionDao{
	
	
	/**
	 * Constructeur de la DAO PromotionBase
	 * @throws SQLException
	 */
	public PromotionDaoImpl() throws SQLException {
		super(Connexion.getConnexion(), Promotion.class);
	}
	
	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.contrat.generique.CRUDBase#charger(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public ArrayList<Promotion> charger(Promotion pPromotion) throws Exception {
		return CRUDHelper.charger(this, pPromotion);
	}
	
	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.generique.CRUDBase#chargerDetail(net.eni.gestion.pedagogie.modele.Promotion)
	 */
	public Promotion chargerDetail(Promotion pPromotion) throws Exception {
		return CRUDHelper.chargerDetail(this, pPromotion);
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.contrat.generique.CRUDBase#ajouter(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public Promotion ajouter(Promotion pPromotion) throws Exception {
		return CRUDHelper.ajouter(this, pPromotion);
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.contrat.generique.CRUDBase#mettreAJour(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public Promotion mettreAJour(Promotion pPromotion) throws Exception {
		return CRUDHelper.mettreAJour(this, pPromotion);
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.contrat.generique.CRUDBase#supprimer(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public Promotion supprimer(Promotion pPromotion) throws Exception {
		return CRUDHelper.supprimer(this, pPromotion);
	}

}
