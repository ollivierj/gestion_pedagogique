package net.eni.gestion.pedagogie.DAO.implementation;

import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;

import net.eni.gestion.pedagogie.DAO.StagiaireDao;
import net.eni.gestion.pedagogie.DAO.StagiairePromotionDao;
import net.eni.gestion.pedagogie.commun.composant.erreur.ApplicationException;
import net.eni.gestion.pedagogie.commun.composant.pagination.Pager;
import net.eni.gestion.pedagogie.commun.composant.tuple.Pair;
import net.eni.gestion.pedagogie.commun.configuration.ModeleMetier;
import net.eni.gestion.pedagogie.commun.modele.Stagiaire;
import net.eni.gestion.pedagogie.commun.modele.StagiairePromotion;
import net.eni.gestion.pedagogie.commun.outil.ORMLiteHelper;

import com.google.inject.Singleton;

@Singleton
public class StagiairePromotionDaoImpl extends ADaoImpl<StagiairePromotion, Integer> implements StagiairePromotionDao {

	StagiaireDao stagiaireDao;
	
	/**
	 * Constructeur de la DAO StagiairePromotion
	 * @throws SQLException
	 */
	public StagiairePromotionDaoImpl() throws SQLException {
		super(StagiairePromotion.class);
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

	@Override
	public Pair<ArrayList<StagiairePromotion>, Long> charger(Pager pPager)
			throws ApplicationException {
		try {
			StagiaireDaoImpl stagiaireDao = new StagiaireDaoImpl();
			StringBuilder lQuery = new StringBuilder();
			lQuery.append("SELECT ");
			lQuery.append(StringUtils.join(ORMLiteHelper.getProjectionFields(this.getTableInfo()), ","));
			lQuery.append(" FROM ( ");
			lQuery.append(" SELECT STAGIAIRE_PROMOTION.*, ");
			lQuery.append(" ROW_NUMBER() OVER (");
			lQuery.append(ORMLiteHelper.getOrderByClauseFromSortOptions(this.getTableInfo(), pPager.getSortOptions()));
			lQuery.append(") AS RowNum ");
			lQuery.append(" FROM ");
			lQuery.append(this.getTableInfo().getTableName());
			lQuery.append(" INNER JOIN ");
			lQuery.append(ModeleMetier.STAGIAIRE_TABLE_NAME);
			lQuery.append(" ON ");
			lQuery.append(ModeleMetier.STAGIAIRE_TABLE_NAME);
			lQuery.append(".");
			lQuery.append(Stagiaire.ID_FIELD_NAME);
			lQuery.append("=");
			lQuery.append(ModeleMetier.STAGIAIREPROMO_VIEW_NAME);
			lQuery.append(".");
			lQuery.append(StagiairePromotion.ID_FIELD_NAME);
			lQuery.append(" ");
			String lFullTextSearchWhereClause = ORMLiteHelper.getFullTextSearchWhereClause(stagiaireDao.getDataClass().newInstance().getFullTextSearchFieldNames() , pPager.getFilterOptions().getFilterText());
			if (null !=lFullTextSearchWhereClause){
				lQuery.append(" WHERE ");
				lQuery.append(lFullTextSearchWhereClause);
			}
			lQuery.append(") AS sub ");
			if (null!=pPager.getPagingOptions()){
				lQuery.append(" WHERE sub.RowNum BETWEEN ");
				lQuery.append(String.valueOf(((pPager.getPagingOptions().getCurrentPage()-1)*pPager.getPagingOptions().getPageSize()) + 1));
				lQuery.append(" AND ");
				lQuery.append(String.valueOf(pPager.getPagingOptions().getCurrentPage()*pPager.getPagingOptions().getPageSize()));
			}
			return new Pair<ArrayList<StagiairePromotion>, Long>(new ArrayList<StagiairePromotion>(this.queryRaw(lQuery.toString(), this.getRawRowMapper()).getResults()), this.countOf());
		} catch (Exception exception) {
			throw new ApplicationException("Echec de chargement de la liste d'enregistrements depuis la base de données");
		}
	}
	

	
}
