package net.eni.gestion.pedagogie.DAO.implementation;

import java.sql.SQLException;

import net.eni.gestion.pedagogie.DAO.ReservationSalleDao;
import net.eni.gestion.pedagogie.commun.modele.ReservationSalle;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.j256.ormlite.support.ConnectionSource;

/**
 * @author jollivier
 * Service métier "ReservationSalle"
 */
@Singleton
public class ReservationSalleDaoImpl extends ADaoImpl<ReservationSalle, Integer> implements ReservationSalleDao{
	
	/**
	 * Constructeur de la DAO ReservationSalleBase
	 * @throws SQLException
	 */
	@Inject
	public ReservationSalleDaoImpl(Provider<ConnectionSource> connection) throws SQLException {
		super(connection, ReservationSalle.class);
	}

}
