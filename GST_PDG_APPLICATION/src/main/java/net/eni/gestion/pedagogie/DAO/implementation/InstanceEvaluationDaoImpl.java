package net.eni.gestion.pedagogie.DAO.implementation;

import java.sql.SQLException;
import java.util.List;

import net.eni.gestion.pedagogie.DAO.InstanceEvaluationDao;
import net.eni.gestion.pedagogie.commun.composant.connexion.Connexion;
import net.eni.gestion.pedagogie.commun.modele.Evaluation;
import net.eni.gestion.pedagogie.commun.modele.InstanceEvaluation;

import com.google.inject.Singleton;

/**
 * @author jollivier
 * Service métier "InstanceEvaluation"
 */
@Singleton
public class InstanceEvaluationDaoImpl extends ADaoImpl<InstanceEvaluation, Integer> implements InstanceEvaluationDao{
	
	/**
	 * Constructeur de la DAO InstanceEvaluationBase
	 * @throws SQLException
	 */
	public InstanceEvaluationDaoImpl() throws SQLException {
		super(Connexion.getConnexion(), InstanceEvaluation.class);
	}
	
	@Override
	public List<InstanceEvaluation> getInstancesByEvaluation(Evaluation evaluation) throws Exception {
		
		List<InstanceEvaluation> instances = null;
		
		try {
			instances = this.queryBuilder().where().eq(InstanceEvaluation.EVALUATION_FIELD_NAME, evaluation.getId()).query();
		} catch (SQLException e) {
			throw new Exception("Impossible de récupérer les instances d'évaluationO");
		}
		
		return instances;
	}

}
