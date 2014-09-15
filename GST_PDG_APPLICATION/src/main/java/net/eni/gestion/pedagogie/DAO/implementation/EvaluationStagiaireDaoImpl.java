package net.eni.gestion.pedagogie.DAO.implementation;

import java.sql.SQLException;

import net.eni.gestion.pedagogie.DAO.EvaluationStagiaireDao;
import net.eni.gestion.pedagogie.commun.composant.Connexion;
import net.eni.gestion.pedagogie.modele.EvaluationStagiaire;

import com.google.inject.Singleton;

/**
 * @author jollivier
 * Service métier "InstanceEvaluationStagiaire"
 */
@Singleton
public class EvaluationStagiaireDaoImpl extends ADaoImpl<EvaluationStagiaire, Integer> implements EvaluationStagiaireDao{
	
	/**
	 * Constructeur de la DAO InstanceEvaluationStagiaireBase
	 * @throws SQLException
	 */
	public EvaluationStagiaireDaoImpl() throws SQLException {
		super(Connexion.getConnexion(), EvaluationStagiaire.class);
	}

}
