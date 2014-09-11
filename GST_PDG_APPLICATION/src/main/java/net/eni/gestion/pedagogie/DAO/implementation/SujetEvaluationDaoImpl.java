package net.eni.gestion.pedagogie.DAO.implementation;

import java.sql.SQLException;

import net.eni.gestion.pedagogie.DAO.SujetEvaluationDao;
import net.eni.gestion.pedagogie.commun.composant.Connexion;
import net.eni.gestion.pedagogie.modele.SujetEvaluation;

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


}
