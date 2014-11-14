package net.eni.gestion.pedagogie.DAO.implementation;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;

import net.eni.gestion.pedagogie.DAO.SujetEvaluationDao;
import net.eni.gestion.pedagogie.commun.composant.erreur.ApplicationException;
import net.eni.gestion.pedagogie.commun.configuration.ModeleMetier;
import net.eni.gestion.pedagogie.commun.modele.Evaluation;
import net.eni.gestion.pedagogie.commun.modele.SujetEvaluation;

import com.google.inject.Singleton;

/**
 * @author jollivier Service métier "Stagiaire"
 */
@Singleton
public class SujetEvaluationDaoImpl extends ADaoImpl<SujetEvaluation, Integer> implements
		SujetEvaluationDao {

	/**
	 * Constructeur de la DAO StagiaireBase
	 * 
	 * @throws SQLException
	 */
	public SujetEvaluationDaoImpl() throws SQLException {
		super(SujetEvaluation.class);
	}
	
	public HashMap<String, String> getTitleMap() throws ApplicationException {
		try{
			Iterator<SujetEvaluation> lSujetEvaluations = this.queryForAll().iterator();
			HashMap<String, String> lResults = new HashMap<String, String>();
			while (lSujetEvaluations.hasNext()) {
				SujetEvaluation lSujetEvaluation = lSujetEvaluations.next();
				StringBuilder strBuilder = new StringBuilder();
				strBuilder.append(lSujetEvaluation.getModule().getLibelle());
				strBuilder.append(" - ");
				strBuilder.append(lSujetEvaluation.getVersion());
				lResults.put(lSujetEvaluation.getId().toString(), strBuilder.toString());
			}
			return lResults;
		} catch (Exception exception) {
		throw new ApplicationException(
					"Echec de chargement de la liste d'enregistrements depuis la base de données");
		}
	}
	
	@Override
	protected boolean validerAvantSuppression(Integer pId) throws  ApplicationException {
		StringBuilder lQuery = new StringBuilder();
		lQuery.append("SELECT TOP 1 1 FROM ");
		lQuery.append(ModeleMetier.EVALUATION_TABLE_NAME);
		lQuery.append(" WHERE ");
		lQuery.append(Evaluation.SUJET_EVALUATION_FIELD_NAME);
		lQuery.append("=");
		lQuery.append(pId);
		String[] instanceExist;
		try {
			instanceExist = this.queryRaw(lQuery.toString()).getFirstResult();
		} catch (SQLException e) {
			throw new ApplicationException("Echec lors de la validation en base de données");
		}
		if (null==instanceExist){
			return true;
		}else {
			throw new ApplicationException("Il existe au moins une évaluation référençant ce sujet d'évaluation.\n Vous ne pouvez pas supprimer ce sujet d'évaluation");
		}
	}


}
