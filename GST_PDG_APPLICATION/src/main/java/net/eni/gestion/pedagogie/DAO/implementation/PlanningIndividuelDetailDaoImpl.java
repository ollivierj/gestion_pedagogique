package net.eni.gestion.pedagogie.DAO.implementation;

import java.sql.SQLException;
import java.util.ArrayList;

import net.eni.gestion.pedagogie.DAO.PlanningIndividuelDetailDao;
import net.eni.gestion.pedagogie.commun.composant.Connexion;
import net.eni.gestion.pedagogie.commun.outil.CRUDHelper;
import net.eni.gestion.pedagogie.modele.PlanningIndividuelDetail;

import com.google.inject.Singleton;
import com.j256.ormlite.dao.BaseDaoImpl;

/**
 * @author jollivier
 * Service métier "PlanningIndividuelDetail"
 */
@Singleton
public class PlanningIndividuelDetailDaoImpl extends BaseDaoImpl<PlanningIndividuelDetail, String> implements PlanningIndividuelDetailDao{
	
	
	/**
	 * Constructeur de la DAO PlanningIndividuelDetailBase
	 * @throws SQLException
	 */
	public PlanningIndividuelDetailDaoImpl() throws SQLException {
		super(Connexion.getConnexion(), PlanningIndividuelDetail.class);
	}
	
	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.contrat.generique.CRUDBase#charger(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public ArrayList<PlanningIndividuelDetail> charger(int page, int pageSize, String orderColumn, String orderDirection, String searchText) throws Exception {
		return CRUDHelper.charger(this, page, pageSize, orderColumn, orderDirection, searchText);
	}
	
	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.generique.CRUDBase#chargerDetail(net.eni.gestion.pedagogie.modele.PlanningIndividuelDetail)
	 */
	public PlanningIndividuelDetail chargerDetail(String pId) throws Exception {
		return CRUDHelper.chargerDetail(this, pId);
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.contrat.generique.CRUDBase#ajouter(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public PlanningIndividuelDetail ajouter(PlanningIndividuelDetail pPlanningIndividuelDetail) throws Exception {
		return CRUDHelper.ajouter(this, pPlanningIndividuelDetail);
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.contrat.generique.CRUDBase#mettreAJour(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public PlanningIndividuelDetail mettreAJour(PlanningIndividuelDetail pPlanningIndividuelDetail) throws Exception {
		return CRUDHelper.mettreAJour(this, pPlanningIndividuelDetail);
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.contrat.generique.CRUDBase#supprimer(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public String supprimer(String pId) throws Exception {
		return CRUDHelper.supprimer(this, pId);
	}

}
