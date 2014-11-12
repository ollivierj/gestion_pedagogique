package net.eni.gestion.pedagogie.DAO.implementation;

import java.sql.SQLException;

import net.eni.gestion.pedagogie.DAO.DroitDao;
import net.eni.gestion.pedagogie.commun.modele.Droit;

import com.google.inject.Singleton;
import com.j256.ormlite.support.ConnectionSource;

/**
 * @author jollivier
 * Service métier "Droit"
 */
@Singleton
public class DroitDaoImpl extends ADaoImpl<Droit, Integer> implements DroitDao{

	
	/**
	 * Constructeur de la DAO DroitBase
	 * @throws SQLException
	 */
	public DroitDaoImpl() throws SQLException {
		super(Droit.class);
	}
	
	public DroitDaoImpl(ConnectionSource connection) throws SQLException {
		super(Droit.class);
	}

}
