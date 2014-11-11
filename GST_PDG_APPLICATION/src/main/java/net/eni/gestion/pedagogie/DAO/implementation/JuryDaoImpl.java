package net.eni.gestion.pedagogie.DAO.implementation;

import java.sql.SQLException;

import net.eni.gestion.pedagogie.DAO.JuryDao;
import net.eni.gestion.pedagogie.commun.modele.Jury;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.j256.ormlite.support.ConnectionSource;

/**
 * @author jollivier
 * Service métier "Jury"
 */
@Singleton
public class JuryDaoImpl extends ADaoImpl<Jury, Integer> implements JuryDao{
	
	/**
	 * Constructeur de la DAO JuryBase
	 * @throws SQLException
	 */
	@Inject
	public JuryDaoImpl(Provider<ConnectionSource> connection) throws SQLException {
		super(connection, Jury.class);
	}

}
