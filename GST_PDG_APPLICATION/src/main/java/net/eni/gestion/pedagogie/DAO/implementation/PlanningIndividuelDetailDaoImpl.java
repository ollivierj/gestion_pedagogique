package net.eni.gestion.pedagogie.DAO.implementation;

import java.sql.SQLException;

import net.eni.gestion.pedagogie.DAO.PlanningIndividuelDetailDao;
import net.eni.gestion.pedagogie.commun.modele.PlanningIndividuelDetail;

import com.google.inject.Singleton;

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
	public PlanningIndividuelDetailDaoImpl() throws SQLException {
		super(PlanningIndividuelDetail.class);
	}

}
