package net.eni.gestion.pedagogie.DAO.implementation;

import java.sql.SQLException;

import net.eni.gestion.pedagogie.DAO.FormationDao;
import net.eni.gestion.pedagogie.commun.modele.Formation;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.j256.ormlite.support.ConnectionSource;

/**
 * @author jollivier
 * Service métier "Formation"
 */
@Singleton
public class FormationDaoImpl extends ADaoImpl<Formation, String> implements FormationDao{
	
	/**
	 * Constructeur de la DAO FormationBase
	 * @throws SQLException
	 */
	@Inject
	public FormationDaoImpl(Provider<ConnectionSource> connection) throws SQLException {
		super(connection, Formation.class);
	}

}
