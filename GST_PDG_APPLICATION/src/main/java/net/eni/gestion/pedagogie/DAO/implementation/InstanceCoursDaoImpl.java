package net.eni.gestion.pedagogie.DAO.implementation;

import java.sql.SQLException;
import java.util.ArrayList;

import net.eni.gestion.pedagogie.DAO.InstanceCoursDao;
import net.eni.gestion.pedagogie.commun.composant.connexion.Connexion;
import net.eni.gestion.pedagogie.commun.composant.pagination.Pager;
import net.eni.gestion.pedagogie.commun.composant.tuple.Pair;
import net.eni.gestion.pedagogie.commun.outil.ORMLiteHelper;
import net.eni.gestion.pedagogie.modele.InstanceCours;

import org.apache.commons.lang3.StringUtils;

import com.google.inject.Singleton;

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
	public InstanceCoursDaoImpl() throws SQLException {
		super(Connexion.getConnexion(), InstanceCours.class);
	}

	@Override
	public Pair<ArrayList<InstanceCours>, Long> charger(Pager pPager)
			throws Exception {
		try {
			if (null == pPager.getConnectedUser()){
				throw new Exception("Vous devez être connecté.");
			}
			StringBuilder lQuery = new StringBuilder();
			lQuery.append("SELECT ");
			lQuery.append(StringUtils.join(ORMLiteHelper.getProjectionFields(this.getTableInfo()), ","));
			lQuery.append(" FROM ( ");
			lQuery.append(" SELECT *, ");
			lQuery.append(" ROW_NUMBER() OVER (");
			lQuery.append(ORMLiteHelper.getOrderByClauseFromSortOptions(this.getTableInfo(), pPager.getSortOptions()));
			lQuery.append(") AS RowNum ");
			lQuery.append(" FROM ");
			lQuery.append(this.getTableInfo().getTableName());
			String lFullTextSearchWhereClause = ORMLiteHelper.getFullTextSearchWhereClause(this.getDataClass().newInstance().getFullTextSearchFieldNames() , pPager.getFilterOptions().getFilterText());
			lQuery.append(" WHERE 1=1");
			if (null !=lFullTextSearchWhereClause){
				lQuery.append(" AND ");
				lQuery.append(lFullTextSearchWhereClause);
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
			throw new Exception("Echec de chargement de la liste d'enregistrements depuis la base de données");
		}
	}

}
