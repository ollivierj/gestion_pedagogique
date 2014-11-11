package net.eni.gestion.pedagogie.DAO.implementation;

import java.sql.SQLException;

import net.eni.gestion.pedagogie.DAO.PlanningIndividuelDetailDao;
import net.eni.gestion.pedagogie.commun.modele.PlanningIndividuelDetail;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.j256.ormlite.support.ConnectionSource;

/**
 * @author jollivier
 * Service métier "PlanningIndividuelDetail"
 */
@Singleton
public class PlanningIndividuelDetailDaoImpl extends ADaoImpl<PlanningIndividuelDetail, String> implements PlanningIndividuelDetailDao{
	
	/**
	 * Constructeur de la DAO PlanningIndividuelDetailBase
	 * @throws SQLException
	 */
	@Inject
	public PlanningIndividuelDetailDaoImpl(Provider<ConnectionSource> connection) throws SQLException {
		super(connection, PlanningIndividuelDetail.class);
	}

}
