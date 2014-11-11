package net.eni.gestion.pedagogie.DAO.implementation;

import java.sql.SQLException;

import net.eni.gestion.pedagogie.DAO.SalleDao;
import net.eni.gestion.pedagogie.commun.modele.Salle;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.j256.ormlite.support.ConnectionSource;

/**
 * @author jollivier
 * Service métier "Salle"
 */
@Singleton
public class SalleDaoImpl extends ADaoImpl<Salle, Integer> implements SalleDao{
	
	/**
	 * Constructeur de la DAO SalleBase
	 * @throws SQLException
	 */
	@Inject
	public SalleDaoImpl(Provider<ConnectionSource> connection) throws SQLException {
		super(connection, Salle.class);
	}
	
}
