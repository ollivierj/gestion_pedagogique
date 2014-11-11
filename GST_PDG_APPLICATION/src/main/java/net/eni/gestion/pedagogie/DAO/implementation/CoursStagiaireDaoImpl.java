package net.eni.gestion.pedagogie.DAO.implementation;

import java.sql.SQLException;

import net.eni.gestion.pedagogie.DAO.CoursStagiaireDao;
import net.eni.gestion.pedagogie.commun.modele.CoursStagiaire;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.j256.ormlite.support.ConnectionSource;

/**
 * @author jollivier
 * Service métier "CoursStagiaire"
 */
@Singleton
public class CoursStagiaireDaoImpl extends ADaoImpl<CoursStagiaire, Integer> implements CoursStagiaireDao{
	
	
	/**
	 * Constructeur de la DAO CoursStagiaireBase
	 * @throws SQLException
	 */
	@Inject
	public CoursStagiaireDaoImpl(Provider<ConnectionSource> connection) throws SQLException {
		super(connection, CoursStagiaire.class);
	}

}
