package net.eni.gestion.pedagogie.DAO.implementation;

import java.sql.SQLException;
import java.util.ArrayList;

import net.eni.gestion.pedagogie.DAO.InstanceCoursStagiaireDao;
import net.eni.gestion.pedagogie.commun.composant.Connexion;
import net.eni.gestion.pedagogie.commun.outil.CRUDHelper;
import net.eni.gestion.pedagogie.modele.InstanceCoursStagiaire;

import com.google.inject.Singleton;
import com.j256.ormlite.dao.BaseDaoImpl;

/**
 * @author jollivier
 * Service métier "InstanceCoursStagiaire"
 */
@Singleton
public class InstanceCoursStagiaireDaoImpl extends BaseDaoImpl<InstanceCoursStagiaire, Integer> implements InstanceCoursStagiaireDao{
	
	
	/**
	 * Constructeur de la DAO InstanceCoursStagiaireBase
	 * @throws SQLException
	 */
	public InstanceCoursStagiaireDaoImpl() throws SQLException {
		super(Connexion.getConnexion(), InstanceCoursStagiaire.class);
	}
	
	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.contrat.generique.CRUDBase#charger(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public ArrayList<InstanceCoursStagiaire> charger(InstanceCoursStagiaire pInstanceCoursStagiaire) throws Exception {
		return CRUDHelper.charger(this, pInstanceCoursStagiaire);
	}
	
	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.generique.CRUDBase#chargerDetail(net.eni.gestion.pedagogie.modele.InstanceCoursStagiaire)
	 */
	public InstanceCoursStagiaire chargerDetail(InstanceCoursStagiaire pInstanceCoursStagiaire) throws Exception {
		return CRUDHelper.chargerDetail(this, pInstanceCoursStagiaire);
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.contrat.generique.CRUDBase#ajouter(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public InstanceCoursStagiaire ajouter(InstanceCoursStagiaire pInstanceCoursStagiaire) throws Exception {
		return CRUDHelper.ajouter(this, pInstanceCoursStagiaire);
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.contrat.generique.CRUDBase#mettreAJour(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public InstanceCoursStagiaire mettreAJour(InstanceCoursStagiaire pInstanceCoursStagiaire) throws Exception {
		return CRUDHelper.mettreAJour(this, pInstanceCoursStagiaire);
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.contrat.generique.CRUDBase#supprimer(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public InstanceCoursStagiaire supprimer(InstanceCoursStagiaire pInstanceCoursStagiaire) throws Exception {
		return CRUDHelper.supprimer(this, pInstanceCoursStagiaire);
	}

}
