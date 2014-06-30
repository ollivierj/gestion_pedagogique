package net.eni.gestion.pedagogie.DAO.implementation;

import java.sql.SQLException;
import java.util.ArrayList;

import net.eni.gestion.pedagogie.DAO.InstanceSessionValidationDao;
import net.eni.gestion.pedagogie.commun.composant.Connexion;
import net.eni.gestion.pedagogie.commun.outil.CRUDHelper;
import net.eni.gestion.pedagogie.modele.InstanceSessionValidation;

import com.google.inject.Singleton;
import com.j256.ormlite.dao.BaseDaoImpl;

/**
 * @author jollivier
 * Service métier "InstanceSessionValidation"
 */
@Singleton
public class InstanceSessionValidationDaoImpl extends BaseDaoImpl<InstanceSessionValidation, Integer> implements InstanceSessionValidationDao{
	
	
	/**
	 * Constructeur de la DAO InstanceSessionValidationBase
	 * @throws SQLException
	 */
	public InstanceSessionValidationDaoImpl() throws SQLException {
		super(Connexion.getConnexion(), InstanceSessionValidation.class);
	}
	
	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.contrat.generique.CRUDBase#charger(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public ArrayList<InstanceSessionValidation> charger(int page, int pageSize, String orderColumn, String orderDirection, String searchText) throws Exception {
		return CRUDHelper.charger(this, page, pageSize, orderColumn, orderDirection, searchText);
	}
	
	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.generique.CRUDBase#chargerDetail(net.eni.gestion.pedagogie.modele.InstanceSessionValidation)
	 */
	public InstanceSessionValidation chargerDetail(Integer pId) throws Exception {
		return CRUDHelper.chargerDetail(this, pId);
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.contrat.generique.CRUDBase#ajouter(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public InstanceSessionValidation ajouter(InstanceSessionValidation pInstanceSessionValidation) throws Exception {
		return CRUDHelper.ajouter(this, pInstanceSessionValidation);
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.contrat.generique.CRUDBase#mettreAJour(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public InstanceSessionValidation mettreAJour(InstanceSessionValidation pInstanceSessionValidation) throws Exception {
		return CRUDHelper.mettreAJour(this, pInstanceSessionValidation);
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.contrat.generique.CRUDBase#supprimer(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public Integer supprimer(Integer pId) throws Exception {
		return CRUDHelper.supprimer(this, pId);
	}

}
