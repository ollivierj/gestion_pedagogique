package net.eni.gestion.pedagogie.DAO.implementation;

import java.sql.SQLException;
import java.util.UUID;

import net.eni.gestion.pedagogie.DAO.CoursDao;
import net.eni.gestion.pedagogie.commun.modele.Cours;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.j256.ormlite.support.ConnectionSource;

/**
 * @author jollivier
 * Service métier "Cours"
 */
@Singleton
public class CoursDaoImpl extends ADaoImpl<Cours, UUID> implements CoursDao{
	
	/**
	 * Constructeur de la DAO CoursBase
	 * @throws SQLException
	 */
	@Inject
	public CoursDaoImpl(Provider<ConnectionSource> connection) throws SQLException {
		super(connection, Cours.class);
	}

}
