package net.eni.gestion.pedagogie.DAO.implementation;

import java.sql.SQLException;
import java.util.ArrayList;
import net.eni.gestion.pedagogie.DAO.PlanningIndividuelFormationDao;
import net.eni.gestion.pedagogie.commun.composant.Connexion;
import net.eni.gestion.pedagogie.commun.outil.CRUDHelper;
import net.eni.gestion.pedagogie.modele.PlanningIndividuelFormation;
import com.google.inject.Singleton;
import com.j256.ormlite.dao.BaseDaoImpl;

/**
 * @author jollivier
 * Service métier "PlanningIndividuelFormation"
 */
@Singleton
public class PlanningIndividuelFormationDaoImpl extends BaseDaoImpl<PlanningIndividuelFormation, Integer> implements PlanningIndividuelFormationDao{
	
	
	/**
	 * Constructeur de la DAO PlanningIndividuelFormationBase
	 * @throws SQLException
	 */
	public PlanningIndividuelFormationDaoImpl() throws SQLException {
		super(Connexion.getConnexion(), PlanningIndividuelFormation.class);
	}
	
	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.contrat.generique.CRUDBase#charger(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public ArrayList<PlanningIndividuelFormation> charger(PlanningIndividuelFormation pPlanningIndividuelFormation) throws Exception {
		return CRUDHelper.charger(this, pPlanningIndividuelFormation);
	}
	
	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.generique.CRUDBase#chargerDetail(net.eni.gestion.pedagogie.modele.PlanningIndividuelFormation)
	 */
	public PlanningIndividuelFormation chargerDetail(PlanningIndividuelFormation pPlanningIndividuelFormation) throws Exception {
		return CRUDHelper.chargerDetail(this, pPlanningIndividuelFormation);
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.contrat.generique.CRUDBase#ajouter(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public PlanningIndividuelFormation ajouter(PlanningIndividuelFormation pPlanningIndividuelFormation) throws Exception {
		return CRUDHelper.ajouter(this, pPlanningIndividuelFormation);
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.contrat.generique.CRUDBase#mettreAJour(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public PlanningIndividuelFormation mettreAJour(PlanningIndividuelFormation pPlanningIndividuelFormation) throws Exception {
		return CRUDHelper.mettreAJour(this, pPlanningIndividuelFormation);
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.contrat.generique.CRUDBase#supprimer(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public PlanningIndividuelFormation supprimer(PlanningIndividuelFormation pPlanningIndividuelFormation) throws Exception {
		return CRUDHelper.supprimer(this, pPlanningIndividuelFormation);
	}

}
