package net.eni.gestion.pedagogie.DAO.implementation;

import java.sql.SQLException;

import com.google.inject.Singleton;

import net.eni.gestion.pedagogie.DAO.StagiairePromotionDao;
import net.eni.gestion.pedagogie.commun.composant.Connexion;
import net.eni.gestion.pedagogie.modele.StagiairePromotion;

@Singleton
public class StagiairePromotionDaoImpl extends ADaoImpl<StagiairePromotion, Integer> implements StagiairePromotionDao {

	/**
	 * Constructeur de la DAO StagiairePromotion
	 * @throws SQLException
	 */
	public StagiairePromotionDaoImpl() throws SQLException {
		super(Connexion.getConnexion(), StagiairePromotion.class);
	}

	
}
