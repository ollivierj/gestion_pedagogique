package net.eni.gestion.pedagogie.DAO.implementation;

import java.sql.SQLException;
import java.util.ArrayList;

import net.eni.gestion.pedagogie.DAO.EvaluationDao;
import net.eni.gestion.pedagogie.commun.composant.connexion.Connexion;
import net.eni.gestion.pedagogie.commun.composant.erreur.ApplicationException;
import net.eni.gestion.pedagogie.commun.composant.pagination.Pager;
import net.eni.gestion.pedagogie.commun.composant.tuple.Pair;
import net.eni.gestion.pedagogie.commun.configuration.ModeleMetier;
import net.eni.gestion.pedagogie.commun.modele.Evaluation;
import net.eni.gestion.pedagogie.commun.modele.InstanceEvaluation;
import net.eni.gestion.pedagogie.commun.modele.Module;
import net.eni.gestion.pedagogie.commun.modele.SujetEvaluation;
import net.eni.gestion.pedagogie.commun.outil.ORMLiteHelper;

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
				lKeyword = " OR ";
				lQuery.append(lFullTextSearchWhereClause);
			}
			lFullTextSearchWhereClause = ORMLiteHelper.getFullTextSearchWhereClause(new Module().getFullTextSearchFieldNames() , pPager.getFilterOptions().getFilterText());
			if (null !=lFullTextSearchWhereClause){
				lQuery.append(lKeyword);
				lQuery.append(lFullTextSearchWhereClause);
			}
			lQuery.append(") AS sub ");
			if (null!=pPager.getPagingOptions()){
				lQuery.append(" WHERE sub.RowNum BETWEEN ");
				lQuery.append(String.valueOf(((pPager.getPagingOptions().getCurrentPage()-1)*pPager.getPagingOptions().getPageSize()) + 1));
				lQuery.append(" AND ");
				lQuery.append(String.valueOf(pPager.getPagingOptions().getCurrentPage()*pPager.getPagingOptions().getPageSize()));
			}
			return new Pair<ArrayList<Evaluation>, Long>(new ArrayList<Evaluation>(this.queryRaw(lQuery.toString(), this.getRawRowMapper()).getResults()), this.countOf());
		} catch (Exception exception) {
			throw new Exception("Echec de chargement de la liste d'enregistrements depuis la base de données");
		}
	}

	@Override
	protected boolean validerAvantSuppression(Integer pId) throws  ApplicationException {
		StringBuilder lQuery = new StringBuilder();
		lQuery.append("SELECT TOP 1 1 FROM ");
		lQuery.append(ModeleMetier.INSTANCE_EVALUATION_TABLE_NAME);
		lQuery.append(" WHERE ");
		lQuery.append(InstanceEvaluation.EVALUATION_FIELD_NAME);
		lQuery.append("=");
		lQuery.append(pId);
		boolean instanceExist;
		try {
			instanceExist = this.queryRaw(lQuery.toString()).getFirstResult().length==1;
		} catch (SQLException e) {
			throw new ApplicationException("Echec lors de la validation en base de données");
		}
		if (!instanceExist){
			return true;
		}else {
			throw new ApplicationException("Il existe au moins une instance d'évaluation déclarée pour cette évaluation.\n Il n'est donc pas possible de supprimer cette évaluation");
		}
	}

	@Override
	protected boolean validerAvantMiseAJour(Evaluation pMember)
			throws ApplicationException {
		boolean isValid=false;
		int lInstanceNbr=0;
		Evaluation lEvaluation = null;
		try {
			lEvaluation = this.queryForId(pMember.getId());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			throw new ApplicationException("Echec lors de la validation en base de données");
		}
		StringBuilder lQuery = new StringBuilder();
		lQuery.append("SELECT TOP 1 1 FROM ");
		lQuery.append(ModeleMetier.INSTANCE_EVALUATION_TABLE_NAME);
		lQuery.append(" WHERE ");
		lQuery.append(InstanceEvaluation.EVALUATION_FIELD_NAME);
		lQuery.append("=");
		lQuery.append(pMember.getId());
		try {
			lInstanceNbr = this.queryRaw(lQuery.toString()).getFirstResult().length;
		} catch (SQLException e) {
			throw new ApplicationException("Echec lors de la validation en base de données");
		}
		
		if (0==lInstanceNbr){
			isValid=true;
		}else{
			isValid = 
				(null!= lEvaluation)? 
					(lEvaluation.getDateHeureDebutPassage().equals(pMember.getDateHeureDebutPassage()) && lEvaluation.getDateHeureFinPassage().equals(pMember.getDateHeureFinPassage()))
					:
					false; 
		}
	
		if (isValid){
			return true;
		}else {
			throw new ApplicationException("Il existe au moins une instance de cours déclarée pour cette évaluation.\n Il n'est donc pas possible de modifier les dates pour cette évaluation");
		}
	}	
}
