package net.eni.gestion.pedagogie.DAO.implementation;

import java.sql.SQLException;

import net.eni.gestion.pedagogie.DAO.PlanningIndividuelFormationDao;
import net.eni.gestion.pedagogie.commun.modele.PlanningIndividuelFormation;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.j256.ormlite.support.ConnectionSource;

/**
 * @author jollivier
 * Service métier "PlanningIndividuelFormation"
 */
@Singleton
public class PlanningIndividuelFormationDaoImpl extends ADaoImpl<PlanningIndividuelFormation, Integer> implements PlanningIndividuelFormationDao{
	
	/**
	 * Constructeur de la DAO PlanningIndividuelFormationBase
	 * @throws SQLException
	 */
	@Inject
	public PlanningIndividuelFormationDaoImpl(Provider<ConnectionSource> connection) throws SQLException {
		super(connection, PlanningIndividuelFormation.class);
	}

}
