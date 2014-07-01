package net.eni.gestion.pedagogie.DAO.implementation;

import java.sql.SQLException;

import net.eni.gestion.pedagogie.DAO.EvaluationDao;
import net.eni.gestion.pedagogie.commun.composant.Connexion;
import net.eni.gestion.pedagogie.modele.Evaluation;

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

}
