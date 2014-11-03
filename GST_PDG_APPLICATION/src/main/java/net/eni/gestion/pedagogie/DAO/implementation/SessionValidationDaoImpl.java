package net.eni.gestion.pedagogie.DAO.implementation;

import java.sql.SQLException;

import net.eni.gestion.pedagogie.DAO.SessionValidationDao;
import net.eni.gestion.pedagogie.commun.composant.connexion.Connexion;
import net.eni.gestion.pedagogie.modele.SessionValidation;

import com.google.inject.Singleton;

/**
 * @author jollivier
 * Service métier "SessionValidation"
 */
@Singleton
public class SessionValidationDaoImpl extends ADaoImpl<SessionValidation, Integer> implements SessionValidationDao{
	
	/**
	 * Constructeur de la DAO SessionValidationBase
	 * @throws SQLException
	 */
	public SessionValidationDaoImpl() throws SQLException {
		super(Connexion.getConnexion(), SessionValidation.class);
	}

}
