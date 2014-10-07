package net.eni.gestion.pedagogie.DAO.implementation;

import java.sql.SQLException;
import java.util.ArrayList;

import net.eni.gestion.pedagogie.DAO.PromotionDao;
import net.eni.gestion.pedagogie.commun.composant.Connexion;
import net.eni.gestion.pedagogie.modele.Promotion;

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
		super(Connexion.getConnexion(), Promotion.class);
	}
	
	public ArrayList<Promotion> chargerForAutocompleteSearch(String pSearchText) throws Exception {
		try {
			StringBuilder lQuery = new StringBuilder();
			lQuery.append(" SELECT TOP 10  CodePromotion FROM Promotion WHERE CONTAINS((Promotion.CodePromotion), '\"");
			lQuery.append(pSearchText);
			lQuery.append("*\"')");
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
			throw new Exception("Echec de chargement de la liste d'enregistrements depuis la base de données");
		}
	}

}
