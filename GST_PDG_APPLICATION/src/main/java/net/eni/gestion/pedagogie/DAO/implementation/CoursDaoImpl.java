package net.eni.gestion.pedagogie.DAO.implementation;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

import net.eni.gestion.pedagogie.DAO.CoursDao;
import net.eni.gestion.pedagogie.commun.composant.Connexion;
import net.eni.gestion.pedagogie.commun.outil.CRUDHelper;
import net.eni.gestion.pedagogie.modele.Cours;

import com.google.inject.Singleton;
import com.j256.ormlite.dao.BaseDaoImpl;

/**
 * @author jollivier
 * Service métier "Cours"
 */
@Singleton
public class CoursDaoImpl extends BaseDaoImpl<Cours, UUID> implements CoursDao{
	
	
	/**
	 * Constructeur de la DAO CoursBase
	 * @throws SQLException
	 */
	public CoursDaoImpl() throws SQLException {
		super(Connexion.getConnexion(), Cours.class);
	}
	
	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.contrat.generique.CRUDBase#charger(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public ArrayList<Cours> charger(Cours pCours) throws Exception {
		return CRUDHelper.charger(this, pCours);
	}
	
	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.generique.CRUDBase#chargerDetail(net.eni.gestion.pedagogie.modele.Cours)
	 */
	public Cours chargerDetail(Cours pCours) throws Exception {
		return CRUDHelper.chargerDetail(this, pCours);
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.contrat.generique.CRUDBase#ajouter(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public Cours ajouter(Cours pCours) throws Exception {
		return CRUDHelper.ajouter(this, pCours);
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.contrat.generique.CRUDBase#mettreAJour(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public Cours mettreAJour(Cours pCours) throws Exception {
		return CRUDHelper.mettreAJour(this, pCours);
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.contrat.generique.CRUDBase#supprimer(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public Cours supprimer(Cours pCours) throws Exception {
		return CRUDHelper.supprimer(this, pCours);
	}

}
