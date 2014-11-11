package net.eni.gestion.pedagogie.DAO.implementation;

import java.sql.SQLException;

import net.eni.gestion.pedagogie.DAO.UniteFormationDao;
import net.eni.gestion.pedagogie.commun.modele.UniteFormation;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.j256.ormlite.support.ConnectionSource;

/**
 * @author jollivier
 * Service métier "UniteFormation"
 */
@Singleton
public class UniteFormationDaoImpl extends ADaoImpl<UniteFormation, Integer> implements UniteFormationDao{
	
	/**
	 * Constructeur de la DAO UniteFormationBase
	 * @throws SQLException
	 */
	@Inject
	public UniteFormationDaoImpl(Provider<ConnectionSource> connection) throws SQLException {
		super(connection, UniteFormation.class);
	}

}
