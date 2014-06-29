package net.eni.gestion.pedagogie.DAO.implementation;

import java.sql.SQLException;
import java.util.ArrayList;
import net.eni.gestion.pedagogie.DAO.FormationDao;
import net.eni.gestion.pedagogie.commun.composant.Connexion;
import net.eni.gestion.pedagogie.commun.outil.CRUDHelper;
import net.eni.gestion.pedagogie.modele.Formation;
import com.google.inject.Singleton;
import com.j256.ormlite.dao.BaseDaoImpl;

/**
 * @author jollivier
 * Service métier "Formation"
 */
@Singleton
public class FormationDaoImpl extends BaseDaoImpl<Formation, String> implements FormationDao{
	
	
	/**
	 * Constructeur de la DAO FormationBase
	 * @throws SQLException
	 */
	public FormationDaoImpl() throws SQLException {
		super(Connexion.getConnexion(), Formation.class);
	}
	
	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.contrat.generique.CRUDBase#charger(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public ArrayList<Formation> charger(Formation pFormation) throws Exception {
		return CRUDHelper.charger(this, pFormation);
	}
	
	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.generique.CRUDBase#chargerDetail(net.eni.gestion.pedagogie.modele.Formation)
	 */
	public Formation chargerDetail(Formation pFormation) throws Exception {
		return CRUDHelper.chargerDetail(this, pFormation);
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.contrat.generique.CRUDBase#ajouter(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public Formation ajouter(Formation pFormation) throws Exception {
		return CRUDHelper.ajouter(this, pFormation);
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.contrat.generique.CRUDBase#mettreAJour(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public Formation mettreAJour(Formation pFormation) throws Exception {
		return CRUDHelper.mettreAJour(this, pFormation);
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.contrat.generique.CRUDBase#supprimer(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public Formation supprimer(Formation pFormation) throws Exception {
		return CRUDHelper.supprimer(this, pFormation);
	}

}
