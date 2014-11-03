package net.eni.gestion.pedagogie.DAO.implementation;

import java.sql.SQLException;

import net.eni.gestion.pedagogie.DAO.PlanningIndividuelFormationDao;
import net.eni.gestion.pedagogie.commun.composant.connexion.Connexion;
import net.eni.gestion.pedagogie.commun.modele.PlanningIndividuelFormation;

import com.google.inject.Singleton;

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
	public PlanningIndividuelFormationDaoImpl() throws SQLException {
		super(Connexion.getConnexion(), PlanningIndividuelFormation.class);
	}

}
