package net.eni.gestion.pedagogie.DAO.implementation;

import java.sql.SQLException;
import java.util.ArrayList;

import net.eni.gestion.pedagogie.DAO.InstanceEvaluationStagiaireDao;
import net.eni.gestion.pedagogie.commun.composant.Connexion;
import net.eni.gestion.pedagogie.commun.outil.CRUDHelper;
import net.eni.gestion.pedagogie.modele.InstanceEvaluationStagiaire;

import com.google.inject.Singleton;
import com.j256.ormlite.dao.BaseDaoImpl;

/**
 * @author jollivier
 * Service métier "InstanceEvaluationStagiaire"
 */
@Singleton
public class InstanceEvaluationStagiaireDaoImpl extends BaseDaoImpl<InstanceEvaluationStagiaire, String> implements InstanceEvaluationStagiaireDao{
	
	
	/**
	 * Constructeur de la DAO InstanceEvaluationStagiaireBase
	 * @throws SQLException
	 */
	public InstanceEvaluationStagiaireDaoImpl() throws SQLException {
		super(Connexion.getConnexion(), InstanceEvaluationStagiaire.class);
	}
	
	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.contrat.generique.CRUDBase#charger(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public ArrayList<InstanceEvaluationStagiaire> charger(InstanceEvaluationStagiaire pInstanceEvaluationStagiaire) throws Exception {
		return CRUDHelper.charger(this, pInstanceEvaluationStagiaire);
	}
	
	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.generique.CRUDBase#chargerDetail(net.eni.gestion.pedagogie.modele.InstanceEvaluationStagiaire)
	 */
	public InstanceEvaluationStagiaire chargerDetail(InstanceEvaluationStagiaire pInstanceEvaluationStagiaire) throws Exception {
		return CRUDHelper.chargerDetail(this, pInstanceEvaluationStagiaire);
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.contrat.generique.CRUDBase#ajouter(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public InstanceEvaluationStagiaire ajouter(InstanceEvaluationStagiaire pInstanceEvaluationStagiaire) throws Exception {
		return CRUDHelper.ajouter(this, pInstanceEvaluationStagiaire);
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.contrat.generique.CRUDBase#mettreAJour(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public InstanceEvaluationStagiaire mettreAJour(InstanceEvaluationStagiaire pInstanceEvaluationStagiaire) throws Exception {
		return CRUDHelper.mettreAJour(this, pInstanceEvaluationStagiaire);
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.contrat.generique.CRUDBase#supprimer(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public InstanceEvaluationStagiaire supprimer(InstanceEvaluationStagiaire pInstanceEvaluationStagiaire) throws Exception {
		return CRUDHelper.supprimer(this, pInstanceEvaluationStagiaire);
	}

}
