package net.eni.gestion.pedagogie.DAO.implementation;

import java.sql.SQLException;
import java.util.ArrayList;

import net.eni.gestion.pedagogie.DAO.UniteParFormationDao;
import net.eni.gestion.pedagogie.commun.composant.Connexion;
import net.eni.gestion.pedagogie.commun.outil.CRUDHelper;
import net.eni.gestion.pedagogie.modele.UniteParFormation;

import com.google.inject.Singleton;
import com.j256.ormlite.dao.BaseDaoImpl;

/**
 * @author jollivier
 * Service métier "UniteParFormation"
 */
@Singleton
public class UniteParFormationDaoImpl extends BaseDaoImpl<UniteParFormation, Integer> implements UniteParFormationDao{
	
	
	/**
	 * Constructeur de la DAO UniteParFormationBase
	 * @throws SQLException
	 */
	public UniteParFormationDaoImpl() throws SQLException {
		super(Connexion.getConnexion(), UniteParFormation.class);
	}
	
	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.contrat.generique.CRUDBase#charger(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public ArrayList<UniteParFormation> charger(UniteParFormation pUniteParFormation) throws Exception {
		return CRUDHelper.charger(this, pUniteParFormation);
	}
	
	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.generique.CRUDBase#chargerDetail(net.eni.gestion.pedagogie.modele.UniteParFormation)
	 */
	public UniteParFormation chargerDetail(UniteParFormation pUniteParFormation) throws Exception {
		return CRUDHelper.chargerDetail(this, pUniteParFormation);
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.contrat.generique.CRUDBase#ajouter(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public UniteParFormation ajouter(UniteParFormation pUniteParFormation) throws Exception {
		return CRUDHelper.ajouter(this, pUniteParFormation);
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.contrat.generique.CRUDBase#mettreAJour(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public UniteParFormation mettreAJour(UniteParFormation pUniteParFormation) throws Exception {
		return CRUDHelper.mettreAJour(this, pUniteParFormation);
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.contrat.generique.CRUDBase#supprimer(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public UniteParFormation supprimer(UniteParFormation pUniteParFormation) throws Exception {
		return CRUDHelper.supprimer(this, pUniteParFormation);
	}

}
