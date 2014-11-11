package net.eni.gestion.pedagogie.DAO.implementation;

import java.sql.SQLException;
import java.util.ArrayList;

import net.eni.gestion.pedagogie.DAO.PromotionDao;
import net.eni.gestion.pedagogie.commun.composant.erreur.ApplicationException;
import net.eni.gestion.pedagogie.commun.modele.Promotion;

import com.google.inject.Singleton;
import com.j256.ormlite.dao.RawRowMapper;

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
		super( Promotion.class);
	}
	
	public ArrayList<Promotion> chargerForAutocompleteSearch(String pSearchText) throws ApplicationException {
		try {
			StringBuilder lQuery = new StringBuilder();
			lQuery.append(" SELECT TOP 10  Promotion.CodePromotion FROM Promotion INNER JOIN STAGIAIRE_PROMOTION ON STAGIAIRE_PROMOTION.CodePromotion = Promotion.CodePromotion WHERE CONTAINS((Promotion.CodePromotion), '\"");
			lQuery.append(pSearchText);
			lQuery.append("*\"')");
			lQuery.append(" GROUP BY Promotion.CodePromotion ORDER BY Promotion.CodePromotion ASC");
			return new ArrayList<Promotion>(
				this.queryRaw(lQuery.toString(), 
				new RawRowMapper<Promotion>() {
					public Promotion mapRow(String[] columnNames, String[] resultColumns) throws SQLException {
						Promotion lPromotion = new Promotion();
						lPromotion.setId(resultColumns[0]);
						return lPromotion;
					}
				}
	            ).getResults());
		} catch (Exception exception) {
			throw new ApplicationException("Echec de chargement de la liste d'enregistrements depuis la base de données");
		}
	}

}
