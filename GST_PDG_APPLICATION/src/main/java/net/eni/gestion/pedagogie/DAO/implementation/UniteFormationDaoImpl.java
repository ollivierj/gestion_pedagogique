package net.eni.gestion.pedagogie.DAO.implementation;

import java.sql.SQLException;
import java.util.ArrayList;

import net.eni.gestion.pedagogie.DAO.UniteFormationDao;
import net.eni.gestion.pedagogie.commun.composant.Connexion;
import net.eni.gestion.pedagogie.commun.outil.CRUDHelper;
import net.eni.gestion.pedagogie.modele.UniteFormation;

import com.google.inject.Singleton;
import com.j256.ormlite.dao.BaseDaoImpl;

/**
 * @author jollivier
 * Service métier "UniteFormation"
 */
@Singleton
public class UniteFormationDaoImpl extends BaseDaoImpl<UniteFormation, Integer> implements UniteFormationDao{
	
	
	/**
	 * Constructeur de la DAO UniteFormationBase
	 * @throws SQLException
	 */
	public UniteFormationDaoImpl() throws SQLException {
		super(Connexion.getConnexion(), UniteFormation.class);
	}
	
	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.contrat.generique.CRUDBase#charger(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public ArrayList<UniteFormation> charger(UniteFormation pUniteFormation) throws Exception {
		return CRUDHelper.charger(this, pUniteFormation);
	}
	
	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.generique.CRUDBase#chargerDetail(net.eni.gestion.pedagogie.modele.UniteFormation)
	 */
	public UniteFormation chargerDetail(UniteFormation pUniteFormation) throws Exception {
		return CRUDHelper.chargerDetail(this, pUniteFormation);
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.contrat.generique.CRUDBase#ajouter(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public UniteFormation ajouter(UniteFormation pUniteFormation) throws Exception {
		return CRUDHelper.ajouter(this, pUniteFormation);
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.contrat.generique.CRUDBase#mettreAJour(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public UniteFormation mettreAJour(UniteFormation pUniteFormation) throws Exception {
		return CRUDHelper.mettreAJour(this, pUniteFormation);
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.contrat.generique.CRUDBase#supprimer(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public UniteFormation supprimer(UniteFormation pUniteFormation) throws Exception {
		return CRUDHelper.supprimer(this, pUniteFormation);
	}

}
