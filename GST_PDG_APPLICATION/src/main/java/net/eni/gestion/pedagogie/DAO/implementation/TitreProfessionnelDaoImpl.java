package net.eni.gestion.pedagogie.DAO.implementation;

import java.sql.SQLException;
import java.util.ArrayList;

import net.eni.gestion.pedagogie.DAO.TitreProfessionnelDao;
import net.eni.gestion.pedagogie.commun.composant.Connexion;
import net.eni.gestion.pedagogie.commun.outil.CRUDHelper;
import net.eni.gestion.pedagogie.modele.TitreProfessionnel;

import com.google.inject.Singleton;
import com.j256.ormlite.dao.BaseDaoImpl;

/**
 * @author jollivier
 * Service métier "TitreProfessionnel"
 */
@Singleton
public class TitreProfessionnelDaoImpl extends BaseDaoImpl<TitreProfessionnel, Integer> implements TitreProfessionnelDao{
	
	
	/**
	 * Constructeur de la DAO TitreProfessionnelBase
	 * @throws SQLException
	 */
	public TitreProfessionnelDaoImpl() throws SQLException {
		super(Connexion.getConnexion(), TitreProfessionnel.class);
	}
	
	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.contrat.generique.CRUDBase#charger(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public ArrayList<TitreProfessionnel> charger(int page, int pageSize, String orderColumn, String orderDirection, String searchText) throws Exception {
		return CRUDHelper.charger(this, page, pageSize, orderColumn, orderDirection, searchText);
	}
	
	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.generique.CRUDBase#chargerDetail(net.eni.gestion.pedagogie.modele.TitreProfessionnel)
	 */
	public TitreProfessionnel chargerDetail(Integer pId) throws Exception {
		return CRUDHelper.chargerDetail(this, pId);
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.contrat.generique.CRUDBase#ajouter(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public TitreProfessionnel ajouter(TitreProfessionnel pTitreProfessionnel) throws Exception {
		return CRUDHelper.ajouter(this, pTitreProfessionnel);
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.contrat.generique.CRUDBase#mettreAJour(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public TitreProfessionnel mettreAJour(TitreProfessionnel pTitreProfessionnel) throws Exception {
		return CRUDHelper.mettreAJour(this, pTitreProfessionnel);
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.contrat.generique.CRUDBase#supprimer(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public Integer supprimer(Integer pId) throws Exception {
		return CRUDHelper.supprimer(this, pId);
	}

}
