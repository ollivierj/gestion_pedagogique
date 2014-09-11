package net.eni.gestion.pedagogie.DAO.implementation;

import java.sql.SQLException;

import net.eni.gestion.pedagogie.DAO.SessionValidationStagiaireDao;
import net.eni.gestion.pedagogie.commun.composant.Connexion;
import net.eni.gestion.pedagogie.modele.SessionValidationStagiaire;

import com.google.inject.Singleton;

/**
 * @author jollivier
 * Service métier "InstanceSessionValidationStagiaire"
 */
@Singleton
public class SessionValidationStagiaireDaoImpl extends ADaoImpl<SessionValidationStagiaire, Integer> implements SessionValidationStagiaireDao{
	
	/**
	 * Constructeur de la DAO InstanceSessionValidationStagiaireBase
	 * @throws SQLException
	 */
	public SessionValidationStagiaireDaoImpl() throws SQLException {
		super(Connexion.getConnexion(), SessionValidationStagiaire.class);
	}

}
