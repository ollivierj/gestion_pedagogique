package net.eni.gestion.pedagogie.DAO.implementation;

import java.sql.SQLException;
import java.util.ArrayList;

import net.eni.gestion.pedagogie.DAO.StagiairePromotionDao;
import net.eni.gestion.pedagogie.commun.composant.erreur.ApplicationException;
import net.eni.gestion.pedagogie.commun.modele.StagiairePromotion;

import com.google.inject.Singleton;

@Singleton
public class StagiairePromotionDaoImpl extends ADaoImpl<StagiairePromotion, Integer> implements StagiairePromotionDao {

	/**
	 * Constructeur de la DAO StagiairePromotion
	 * @throws SQLException
	 */
	public StagiairePromotionDaoImpl() throws SQLException {
		super( StagiairePromotion.class);
	}
	
	public ArrayList<StagiairePromotion> chargerByStagiaireId(Integer id) throws ApplicationException{
		try {
			return new ArrayList<StagiairePromotion>(this.queryForEq(StagiairePromotion.ID_FIELD_NAME, id));
		} catch (SQLException e) {
			throw new ApplicationException("Echec de chargement de la liste d'enregistrements depuis la base de données");
		}
	}
	
	public ArrayList<StagiairePromotion> chargerByPromotionId(String id) throws ApplicationException{
		try {
			return new ArrayList<StagiairePromotion>(this.queryForEq(StagiairePromotion.ID_PROMO_NAME, id));
		} catch (SQLException e) {
			throw new ApplicationException("Echec de chargement de la liste d'enregistrements depuis la base de données");
		}
	}
	

	
}
