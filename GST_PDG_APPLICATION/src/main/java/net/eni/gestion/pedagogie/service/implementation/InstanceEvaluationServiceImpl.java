package net.eni.gestion.pedagogie.service.implementation;

import java.sql.SQLException;
import java.util.List;

import net.eni.gestion.pedagogie.DAO.InstanceEvaluationDao;
import net.eni.gestion.pedagogie.commun.composant.erreur.ApplicationException;
import net.eni.gestion.pedagogie.commun.modele.Evaluation;
import net.eni.gestion.pedagogie.commun.modele.InstanceEvaluation;
import net.eni.gestion.pedagogie.service.InstanceEvaluationService;

import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * @author jollivier Classe d'implémentation pour le module de suivi des
 *         evaluations
 */
@Singleton
public class InstanceEvaluationServiceImpl extends
		AServiceImpl<InstanceEvaluation, Integer, InstanceEvaluationDao>
		implements InstanceEvaluationService {

	/**
	 * Constructeur
	 * 
	 * @param DAO
	 *            evaluation
	 * @throws SQLException
	 */
	@Inject
	public InstanceEvaluationServiceImpl(
			InstanceEvaluationDao pInstanceEvaluationDao) throws SQLException {
		super(pInstanceEvaluationDao);
	}

	@Override
	public List<InstanceEvaluation> getInstancesByEvaluation(
			Evaluation evaluation) throws ApplicationException {

		List<InstanceEvaluation> instances = null;
		instances = dao.getInstancesByEvaluation(evaluation);
		return instances;
	}

}
