package net.eni.gestion.pedagogie.DAO.implementation;

import java.sql.SQLException;

import net.eni.gestion.pedagogie.DAO.InstanceEvaluationStagiaireDao;
import net.eni.gestion.pedagogie.commun.composant.Connexion;
import net.eni.gestion.pedagogie.modele.InstanceEvaluationStagiaire;

import com.google.inject.Singleton;

/**
 * @author jollivier
 * Service métier "InstanceEvaluationStagiaire"
 */
@Singleton
public class InstanceEvaluationStagiaireDaoImpl extends ADaoImpl<InstanceEvaluationStagiaire, Integer> implements InstanceEvaluationStagiaireDao{
	
	/**
	 * Constructeur de la DAO InstanceEvaluationStagiaireBase
	 * @throws SQLException
	 */
	public InstanceEvaluationStagiaireDaoImpl() throws SQLException {
		super(Connexion.getConnexion(), InstanceEvaluationStagiaire.class);
	}

}
