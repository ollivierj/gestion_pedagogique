package net.eni.gestion.pedagogie.DAO.implementation;

import java.sql.SQLException;
import java.util.ArrayList;

import net.eni.gestion.pedagogie.DAO.DroitProfilDao;
import net.eni.gestion.pedagogie.commun.composant.Connexion;
import net.eni.gestion.pedagogie.commun.outil.CRUDHelper;
import net.eni.gestion.pedagogie.modele.DroitProfil;

import com.google.inject.Singleton;
import com.j256.ormlite.dao.BaseDaoImpl;

/**
 * @author jollivier
 * Service métier "DroitProfil"
 */
@Singleton
public class DroitProfilDaoImpl extends BaseDaoImpl<DroitProfil, Integer> implements DroitProfilDao{
	
	
	/**
	 * Constructeur de la DAO DroitProfilBase
	 * @throws SQLException
	 */
	public DroitProfilDaoImpl() throws SQLException {
		super(Connexion.getConnexion(), DroitProfil.class);
	}
	
	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.contrat.generique.CRUDBase#charger(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public ArrayList<DroitProfil> charger(int page, int pageSize, String orderColumn, String orderDirection, String searchText) throws Exception {
		return CRUDHelper.charger(this, page, pageSize, orderColumn, orderDirection, searchText);
	}
	
	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.generique.CRUDBase#chargerDetail(net.eni.gestion.pedagogie.modele.DroitProfil)
	 */
	public DroitProfil chargerDetail(Integer pId) throws Exception {
		return CRUDHelper.chargerDetail(this, pId);
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.contrat.generique.CRUDBase#ajouter(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public DroitProfil ajouter(DroitProfil pDroitProfil) throws Exception {
		return CRUDHelper.ajouter(this, pDroitProfil);
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.contrat.generique.CRUDBase#mettreAJour(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public DroitProfil mettreAJour(DroitProfil pDroitProfil) throws Exception {
		return CRUDHelper.mettreAJour(this, pDroitProfil);
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.contrat.generique.CRUDBase#supprimer(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public Integer supprimer(Integer pId) throws Exception {
		return CRUDHelper.supprimer(this, pId);
	}

}
