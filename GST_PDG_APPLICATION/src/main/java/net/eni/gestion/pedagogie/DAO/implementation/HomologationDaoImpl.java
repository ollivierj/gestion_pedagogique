package net.eni.gestion.pedagogie.DAO.implementation;

import java.sql.SQLException;
import java.util.ArrayList;

import net.eni.gestion.pedagogie.DAO.HomologationDao;
import net.eni.gestion.pedagogie.commun.composant.Connexion;
import net.eni.gestion.pedagogie.commun.outil.CRUDHelper;
import net.eni.gestion.pedagogie.modele.Homologation;

import com.google.inject.Singleton;
import com.j256.ormlite.dao.BaseDaoImpl;

/**
 * @author jollivier
 * Service métier "Homologation"
 */
@Singleton
public class HomologationDaoImpl extends BaseDaoImpl<Homologation, Integer> implements HomologationDao{
	
	
	/**
	 * Constructeur de la DAO HomologationBase
	 * @throws SQLException
	 */
	public HomologationDaoImpl() throws SQLException {
		super(Connexion.getConnexion(), Homologation.class);
	}
	
	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.contrat.generique.CRUDBase#charger(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public ArrayList<Homologation> charger(Homologation pHomologation) throws Exception {
		return CRUDHelper.charger(this, pHomologation);
	}
	
	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.generique.CRUDBase#chargerDetail(net.eni.gestion.pedagogie.modele.Homologation)
	 */
	public Homologation chargerDetail(Homologation pHomologation) throws Exception {
		return CRUDHelper.chargerDetail(this, pHomologation);
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.contrat.generique.CRUDBase#ajouter(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public Homologation ajouter(Homologation pHomologation) throws Exception {
		return CRUDHelper.ajouter(this, pHomologation);
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.contrat.generique.CRUDBase#mettreAJour(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public Homologation mettreAJour(Homologation pHomologation) throws Exception {
		return CRUDHelper.mettreAJour(this, pHomologation);
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.contrat.generique.CRUDBase#supprimer(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public Homologation supprimer(Homologation pHomologation) throws Exception {
		return CRUDHelper.supprimer(this, pHomologation);
	}

}
