package net.eni.gestion.pedagogie.DAO.implementation;

import java.sql.SQLException;
import java.util.ArrayList;

import net.eni.gestion.pedagogie.DAO.EvaluationDao;
import net.eni.gestion.pedagogie.commun.composant.Connexion;
import net.eni.gestion.pedagogie.commun.composant.Pager;
import net.eni.gestion.pedagogie.commun.composant.Pair;
import net.eni.gestion.pedagogie.commun.constante.ModeleMetier;
import net.eni.gestion.pedagogie.commun.outil.ORMLiteHelper;
import net.eni.gestion.pedagogie.modele.Evaluation;
import net.eni.gestion.pedagogie.modele.Module;
import net.eni.gestion.pedagogie.modele.SujetEvaluation;

import org.apache.commons.lang3.StringUtils;

import com.google.inject.Singleton;

/**
 * @author jollivier
 * Service métier "Evaluation"
 */
@Singleton
public class EvaluationDaoImpl extends ADaoImpl<Evaluation, Integer> implements EvaluationDao{
	
	/**
	 * Constructeur de la DAO EvaluationBase
	 * @throws SQLException
	 */
	public EvaluationDaoImpl() throws SQLException {
		super(Connexion.getConnexion(), Evaluation.class);
	}
	
	@Override
	public Pair<ArrayList<Evaluation>, Long> charger(Pager pPager)
			throws Exception {
		try {
			StringBuilder lQuery = new StringBuilder();
			lQuery.append("SELECT ");
			lQuery.append(StringUtils.join(ORMLiteHelper.getProjectionFields(this.getTableInfo()), ","));
			lQuery.append(" FROM ( ");
			lQuery.append(" SELECT *, ");
			lQuery.append(" ROW_NUMBER() OVER (");
			lQuery.append(ORMLiteHelper.getOrderByClauseFromSortOptions(this.getTableInfo(), pPager.getSortOptions()));
			lQuery.append(") AS RowNum ");
			lQuery.append(" FROM ");
			lQuery.append(ModeleMetier.EVALUATION_TABLE_NAME);
			lQuery.append(" INNER JOIN ");
			lQuery.append(ModeleMetier.SUJET_EVALUATION_TABLE_NAME);
			lQuery.append(" ON ");
			lQuery.append(ModeleMetier.EVALUATION_TABLE_NAME);
			lQuery.append(".");
			lQuery.append(Evaluation.SUJET_EVALUATION_FIELD_NAME);
			lQuery.append("=");
			lQuery.append(ModeleMetier.SUJET_EVALUATION_TABLE_NAME);
			lQuery.append(".");
			lQuery.append(SujetEvaluation.ID_FIELD_NAME);
			lQuery.append(" INNER JOIN ");
			lQuery.append(ModeleMetier.MODULE_TABLE_NAME);
			lQuery.append(" ON ");
			lQuery.append(ModeleMetier.SUJET_EVALUATION_TABLE_NAME);
			lQuery.append(".");
			lQuery.append(SujetEvaluation.MODULE_FIELD_NAME);
			lQuery.append("=");
			lQuery.append(ModeleMetier.MODULE_TABLE_NAME);
			lQuery.append(".");
			lQuery.append(Module.ID_FIELD_NAME);
			String lFullTextSearchWhereClause = ORMLiteHelper.getFullTextSearchWhereClause(this.getDataClass().newInstance().getFullTextSearchFieldNames() , pPager.getFilterOptions().getFilterText());
			String lKeyword = " WHERE ";
			if (null !=lFullTextSearchWhereClause){
				lQuery.append(lKeyword);
				lKeyword = " AND ";
				lQuery.append(lFullTextSearchWhereClause);
			}
			lFullTextSearchWhereClause = ORMLiteHelper.getFullTextSearchWhereClause(new Module().getFullTextSearchFieldNames() , pPager.getFilterOptions().getFilterText());
			if (null !=lFullTextSearchWhereClause){
				lQuery.append(lKeyword);
				lQuery.append(lFullTextSearchWhereClause);
			}
			lQuery.append(") AS sub ");
			lQuery.append(" WHERE sub.RowNum BETWEEN ");
			lQuery.append(String.valueOf((pPager.getPagingOptions().getCurrentPage()-1)*pPager.getPagingOptions().getPageSize()));
			lQuery.append(" AND ");
			lQuery.append(String.valueOf(pPager.getPagingOptions().getCurrentPage()*pPager.getPagingOptions().getPageSize()-1));
			return new Pair<ArrayList<Evaluation>, Long>(new ArrayList<Evaluation>(this.queryRaw(lQuery.toString(), this.getRawRowMapper()).getResults()), this.countOf());
		} catch (Exception exception) {
			throw new Exception("Echec de chargement de la liste d'enregistrements depuis la base de données");
		}
	}	
}
