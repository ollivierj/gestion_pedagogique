package net.eni.gestion.pedagogie.DAO.implementation;

import java.sql.SQLException;

import net.eni.gestion.pedagogie.DAO.UniteParFormationDao;
import net.eni.gestion.pedagogie.commun.modele.UniteParFormation;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.j256.ormlite.support.ConnectionSource;

/**
 * @author jollivier
 * Service métier "UniteParFormation"
 */
@Singleton
public class UniteParFormationDaoImpl extends ADaoImpl<UniteParFormation, Integer> implements UniteParFormationDao{
	
	/**
	 * Constructeur de la DAO UniteParFormationBase
	 * @throws SQLException
	 */
	@Inject
	public UniteParFormationDaoImpl(Provider<ConnectionSource> connection) throws SQLException {
		super(connection, UniteParFormation.class);
	}

}
