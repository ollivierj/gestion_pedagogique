package net.eni.gestion.pedagogie.DAO.implementation;

import java.sql.SQLException;
import java.util.ArrayList;

import net.eni.gestion.pedagogie.DAO.AbsenceDao;
import net.eni.gestion.pedagogie.commun.composant.Connexion;
import net.eni.gestion.pedagogie.commun.outil.CRUDHelper;
import net.eni.gestion.pedagogie.modele.Absence;

import com.google.inject.Singleton;
import com.j256.ormlite.dao.BaseDaoImpl;

/**
 * @author jollivier
 * Service métier "Absence"
 */
@Singleton
public class AbsenceDaoImpl extends BaseDaoImpl<Absence, Integer> implements AbsenceDao{
	
	
	/**
	 * Constructeur de la DAO AbsenceBase
	 * @throws SQLException
	 */
	public AbsenceDaoImpl() throws SQLException {
		super(Connexion.getConnexion(), Absence.class);
	}
	
	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.contrat.generique.CRUDBase#charger(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public ArrayList<Absence> charger(Absence pAbsence) throws Exception {
		return CRUDHelper.charger(this, pAbsence);
	}
	
	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.generique.CRUDBase#chargerDetail(net.eni.gestion.pedagogie.modele.Absence)
	 */
	public Absence chargerDetail(Absence pAbsence) throws Exception {
		return CRUDHelper.chargerDetail(this, pAbsence);
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.contrat.generique.CRUDBase#ajouter(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public Absence ajouter(Absence pAbsence) throws Exception {
		return CRUDHelper.ajouter(this, pAbsence);
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.contrat.generique.CRUDBase#mettreAJour(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public Absence mettreAJour(Absence pAbsence) throws Exception {
		return CRUDHelper.mettreAJour(this, pAbsence);
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.contrat.generique.CRUDBase#supprimer(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public Absence supprimer(Absence pAbsence) throws Exception {
		return CRUDHelper.supprimer(this, pAbsence);
	}

}
