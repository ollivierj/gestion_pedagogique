package net.eni.gestion.pedagogie.DAO.implementation;

import java.sql.SQLException;
import java.util.ArrayList;

import net.eni.gestion.pedagogie.DAO.InstanceSessionValidationStagiaireDao;
import net.eni.gestion.pedagogie.commun.composant.Connexion;
import net.eni.gestion.pedagogie.commun.outil.CRUDHelper;
import net.eni.gestion.pedagogie.modele.InstanceSessionValidationStagiaire;

import com.google.inject.Singleton;
import com.j256.ormlite.dao.BaseDaoImpl;

/**
 * @author jollivier
 * Service métier "InstanceSessionValidationStagiaire"
 */
@Singleton
public class InstanceSessionValidationStagiaireDaoImpl extends BaseDaoImpl<InstanceSessionValidationStagiaire, Integer> implements InstanceSessionValidationStagiaireDao{
	
	
	/**
	 * Constructeur de la DAO InstanceSessionValidationStagiaireBase
	 * @throws SQLException
	 */
	public InstanceSessionValidationStagiaireDaoImpl() throws SQLException {
		super(Connexion.getConnexion(), InstanceSessionValidationStagiaire.class);
	}
	
	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.contrat.generique.CRUDBase#charger(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public ArrayList<InstanceSessionValidationStagiaire> charger(int page, int pageSize, String orderColumn, String orderDirection, String searchText) throws Exception {
		return CRUDHelper.charger(this, page, pageSize, orderColumn, orderDirection, searchText);
	}
	
	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.generique.CRUDBase#chargerDetail(net.eni.gestion.pedagogie.modele.InstanceSessionValidationStagiaire)
	 */
	public InstanceSessionValidationStagiaire chargerDetail(Integer pId) throws Exception {
		return CRUDHelper.chargerDetail(this, pId);
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.contrat.generique.CRUDBase#ajouter(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public InstanceSessionValidationStagiaire ajouter(InstanceSessionValidationStagiaire pInstanceSessionValidationStagiaire) throws Exception {
		return CRUDHelper.ajouter(this, pInstanceSessionValidationStagiaire);
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.contrat.generique.CRUDBase#mettreAJour(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public InstanceSessionValidationStagiaire mettreAJour(InstanceSessionValidationStagiaire pInstanceSessionValidationStagiaire) throws Exception {
		return CRUDHelper.mettreAJour(this, pInstanceSessionValidationStagiaire);
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.contrat.generique.CRUDBase#supprimer(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public Integer supprimer(Integer pId) throws Exception {
		return CRUDHelper.supprimer(this, pId);
	}

}
