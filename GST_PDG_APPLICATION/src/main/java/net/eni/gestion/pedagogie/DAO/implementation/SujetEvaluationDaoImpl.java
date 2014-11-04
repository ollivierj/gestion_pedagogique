package net.eni.gestion.pedagogie.DAO.implementation;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;

import net.eni.gestion.pedagogie.DAO.SujetEvaluationDao;
import net.eni.gestion.pedagogie.commun.composant.connexion.Connexion;
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
		super(Connexion.getConnexion(), SujetEvaluation.class);
	}
	
	public HashMap<String, String> getTitleMap() throws Exception {
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
			throw new Exception(
					"Echec de chargement de la liste d'enregistrements depuis la base de données");
		}
	}


}
