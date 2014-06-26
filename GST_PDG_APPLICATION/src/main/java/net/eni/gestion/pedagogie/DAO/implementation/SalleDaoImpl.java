package net.eni.gestion.pedagogie.DAO.implementation;

import java.sql.SQLException;
import java.util.ArrayList;

import net.eni.gestion.pedagogie.DAO.SalleDao;
import net.eni.gestion.pedagogie.commun.composant.Connexion;
import net.eni.gestion.pedagogie.commun.outil.CRUDHelper;
import net.eni.gestion.pedagogie.modele.Salle;

import com.google.inject.Singleton;
import com.j256.ormlite.dao.BaseDaoImpl;

/**
 * @author jollivier
 * Service métier "Salle"
 */
@Singleton
public class SalleDaoImpl extends BaseDaoImpl<Salle, String> implements SalleDao{
	
	
	/**
	 * Constructeur de la DAO SalleBase
	 * @throws SQLException
	 */
	public SalleDaoImpl() throws SQLException {
		super(Connexion.getConnexion(), Salle.class);
	}
	
	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.contrat.generique.CRUDBase#charger(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public ArrayList<Salle> charger(Salle pSalle) throws Exception {
		return CRUDHelper.charger(this, pSalle);
	}
	
	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.generique.CRUDBase#chargerDetail(net.eni.gestion.pedagogie.modele.Salle)
	 */
	public Salle chargerDetail(Salle pSalle) throws Exception {
		return CRUDHelper.chargerDetail(this, pSalle);
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.contrat.generique.CRUDBase#ajouter(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public Salle ajouter(Salle pSalle) throws Exception {
		return CRUDHelper.ajouter(this, pSalle);
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.contrat.generique.CRUDBase#mettreAJour(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public Salle mettreAJour(Salle pSalle) throws Exception {
		return CRUDHelper.mettreAJour(this, pSalle);
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.contrat.generique.CRUDBase#supprimer(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public Salle supprimer(Salle pSalle) throws Exception {
		return CRUDHelper.supprimer(this, pSalle);
	}

}
