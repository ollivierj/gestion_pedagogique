package net.eni.gestion.pedagogie.DAO.implementation;

import java.sql.SQLException;

import net.eni.gestion.pedagogie.DAO.PromotionDao;
import net.eni.gestion.pedagogie.commun.composant.Connexion;
import net.eni.gestion.pedagogie.modele.Promotion;

import com.google.inject.Singleton;

/**
 * @author jollivier
 * Service métier "Promotion"
 */
@Singleton
public class PromotionDaoImpl extends ADaoImpl<Promotion, String> implements PromotionDao{
	
	/**
	 * Constructeur de la DAO PromotionBase
	 * @throws SQLException
	 */
	public PromotionDaoImpl() throws SQLException {
		super(Connexion.getConnexion(), Promotion.class);
	}

}
