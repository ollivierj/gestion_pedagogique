package net.eni.gestion.pedagogie.DAO.implementation;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.eni.gestion.pedagogie.DAO.InstanceCoursDao;
import net.eni.gestion.pedagogie.commun.composant.erreur.ApplicationException;
import net.eni.gestion.pedagogie.commun.composant.pagination.Pager;
import net.eni.gestion.pedagogie.commun.composant.tuple.Pair;
import net.eni.gestion.pedagogie.commun.configuration.ModeleMetier;
import net.eni.gestion.pedagogie.commun.modele.Cours;
import net.eni.gestion.pedagogie.commun.modele.InstanceCours;
import net.eni.gestion.pedagogie.commun.modele.Promotion;
import net.eni.gestion.pedagogie.commun.outil.ORMLiteHelper;

import org.apache.commons.lang3.StringUtils;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.j256.ormlite.support.ConnectionSource;

/**
 * @author jollivier
 * Service métier "InstanceCours"
 */
@Singleton
public class InstanceCoursDaoImpl extends ADaoImpl<InstanceCours, Integer> implements InstanceCoursDao{
	
	/**
	 * Constructeur de la DAO InstanceCoursBase
	 * @throws SQLException
	 */
	@Inject
	public InstanceCoursDaoImpl(Provider<ConnectionSource> connection) throws SQLException {
		super(connection, InstanceCours.class);
	}

	@Override
	public Pair<ArrayList<InstanceCours>, Long> charger(Pager pPager)
			throws ApplicationException {
		try {
			if (null == pPager.getConnectedUser()){
				throw new Exception("Vous devez être connecté.");
			}
			StringBuilder lQuery = new StringBuilder();
			lQuery.append("SELECT ");
			lQuery.append(StringUtils.join(ORMLiteHelper.getProjectionFields(this.getTableInfo()), ","));
			lQuery.append(" FROM ( ");
			lQuery.append(" SELECT ");
			lQuery.append(ModeleMetier.INSTANCE_COURS_TABLE_NAME);
			lQuery.append(".*, ");
			lQuery.append(" ROW_NUMBER() OVER (");
			lQuery.append(ORMLiteHelper.getOrderByClauseFromSortOptions(this.getTableInfo(), pPager.getSortOptions()));
			lQuery.append(") AS RowNum ");
			lQuery.append(" FROM ");
			lQuery.append(this.getTableInfo().getTableName());
			lQuery.append(" INNER JOIN ");
			lQuery.append(ModeleMetier.COURS_TABLE_NAME);
			lQuery.append(" ON ");
			lQuery.append(ModeleMetier.INSTANCE_COURS_TABLE_NAME);
			lQuery.append(".");
			lQuery.append(InstanceCours.COURS_FIELD_NAME);
			lQuery.append("=");
			lQuery.append(ModeleMetier.COURS_TABLE_NAME);
			lQuery.append(".");
			lQuery.append(Cours.ID_FIELD_NAME);
			lQuery.append(" INNER JOIN ");
			lQuery.append(ModeleMetier.PROMOTION_TABLE_NAME);
			lQuery.append(" ON ");
			lQuery.append(ModeleMetier.COURS_TABLE_NAME);
			lQuery.append(".");
			lQuery.append(Cours.CODE_PROMOTION_FIELD_NAME);
			lQuery.append("=");
			lQuery.append(ModeleMetier.PROMOTION_TABLE_NAME);
			lQuery.append(".");
			lQuery.append(Promotion.ID_FIELD_NAME);
			String lFullTextSearchWhereClause = ORMLiteHelper.getFullTextSearchWhereClause(this.getDataClass().newInstance().getFullTextSearchFieldNames() , pPager.getFilterOptions().getFilterText());
			lQuery.append(" WHERE 1=1 ");
			if (null !=lFullTextSearchWhereClause){
				lQuery.append(" AND ");
				lQuery.append(lFullTextSearchWhereClause);
			}
			lFullTextSearchWhereClause = ORMLiteHelper.getFullTextSearchWhereClause(new Cours().getFullTextSearchFieldNames() , pPager.getFilterOptions().getFilterText());
			if (null !=lFullTextSearchWhereClause){
				lQuery.append(" AND (");
				lQuery.append(lFullTextSearchWhereClause);
			}
			lFullTextSearchWhereClause = ORMLiteHelper.getFullTextSearchWhereClause(new Promotion().getFullTextSearchFieldNames() , pPager.getFilterOptions().getFilterText());
			if (null !=lFullTextSearchWhereClause){
				lQuery.append(" OR ");
				lQuery.append(lFullTextSearchWhereClause);
				lQuery.append(") ");
			}

			lQuery.append(" AND ");
			lQuery.append(" INST_COURS_ANIMATEUR = ");
			lQuery.append(pPager.getConnectedUser().getId());
			lQuery.append(") AS sub ");
			if (null!=pPager.getPagingOptions()){
				lQuery.append(" WHERE sub.RowNum BETWEEN ");
				lQuery.append(String.valueOf(((pPager.getPagingOptions().getCurrentPage()-1)*pPager.getPagingOptions().getPageSize()) + 1));
				lQuery.append(" AND ");
				lQuery.append(String.valueOf(pPager.getPagingOptions().getCurrentPage()*pPager.getPagingOptions().getPageSize()));
			}
			return new Pair<ArrayList<InstanceCours>, Long>(new ArrayList<InstanceCours>(this.queryRaw(lQuery.toString(), this.getRawRowMapper()).getResults()), this.countOf());
		} catch (Exception exception) {
			throw new ApplicationException("Echec de chargement de la liste d'enregistrements depuis la base de données");
		}
	}

	@Override
	public List<InstanceCours> getInstancesByCours(Cours cours)
			throws ApplicationException {
		List<InstanceCours> instances = null;
		
		try {
			instances = this.queryBuilder().where().eq(InstanceCours.COURS_FIELD_NAME, cours.getId()).query();
		} catch (SQLException e) {
			throw new ApplicationException("Impossible de récupérer les instances de cours");
		}
		
		return instances;
	}
	

}
