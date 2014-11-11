package net.eni.gestion.pedagogie.DAO.implementation;

import java.sql.SQLException;

import net.eni.gestion.pedagogie.DAO.EchangeDao;
import net.eni.gestion.pedagogie.commun.modele.Echange;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.j256.ormlite.support.ConnectionSource;

/**
 * @author jollivier
 * Service métier "Echange"
 */
@Singleton
public class EchangeDaoImpl extends ADaoImpl<Echange, Integer> implements EchangeDao{
	
	/**
	 * Constructeur de la DAO EchangeBase
	 * @throws SQLException
	 */
	@Inject
	public EchangeDaoImpl(Provider<ConnectionSource> connection) throws SQLException {
		super(connection, Echange.class);
	}

}
