package net.eni.gestion.pedagogie.DAO.implementation;

import java.sql.SQLException;

import net.eni.gestion.pedagogie.DAO.ParametreDao;
import net.eni.gestion.pedagogie.commun.modele.Parametre;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.j256.ormlite.support.ConnectionSource;

/**
 * @author jollivier
 * Service métier "Parametre"
 */
@Singleton
public class ParametreDaoImpl extends ADaoImpl<Parametre, String> implements ParametreDao{
	
	/**
	 * Constructeur de la DAO ParametreBase
	 * @throws SQLException
	 */
	@Inject
	public ParametreDaoImpl(Provider<ConnectionSource> connection) throws SQLException {
		super(connection, Parametre.class);
	}

}
