package net.eni.gestion.pedagogie.DAO.implementation;

import java.sql.SQLException;
import java.util.ArrayList;
import net.eni.gestion.pedagogie.DAO.DroitDao;
import net.eni.gestion.pedagogie.commun.composant.Connexion;
import net.eni.gestion.pedagogie.commun.outil.CRUDHelper;
import net.eni.gestion.pedagogie.modele.Droit;
import com.google.inject.Singleton;
import com.j256.ormlite.dao.BaseDaoImpl;

/**
 * @author jollivier
 * Service métier "Droit"
 */
@Singleton
public class DroitDaoImpl extends BaseDaoImpl<Droit, Integer> implements DroitDao{
	
	
	/**
	 * Constructeur de la DAO DroitBase
	 * @throws SQLException
	 */
	public DroitDaoImpl() throws SQLException {
		super(Connexion.getConnexion(), Droit.class);
	}
	
	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.contrat.generique.CRUDBase#charger(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public ArrayList<Droit> charger(Droit pDroit) throws Exception {
		return CRUDHelper.charger(this, pDroit);
	}
	
	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.generique.CRUDBase#chargerDetail(net.eni.gestion.pedagogie.modele.Droit)
	 */
	public Droit chargerDetail(Droit pDroit) throws Exception {
		return CRUDHelper.chargerDetail(this, pDroit);
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.contrat.generique.CRUDBase#ajouter(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public Droit ajouter(Droit pDroit) throws Exception {
		return CRUDHelper.ajouter(this, pDroit);
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.contrat.generique.CRUDBase#mettreAJour(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public Droit mettreAJour(Droit pDroit) throws Exception {
		return CRUDHelper.mettreAJour(this, pDroit);
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.contrat.generique.CRUDBase#supprimer(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public Droit supprimer(Droit pDroit) throws Exception {
		return CRUDHelper.supprimer(this, pDroit);
	}

}
