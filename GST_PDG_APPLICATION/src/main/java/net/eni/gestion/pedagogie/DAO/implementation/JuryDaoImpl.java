package net.eni.gestion.pedagogie.DAO.implementation;

import java.sql.SQLException;
import java.util.ArrayList;

import net.eni.gestion.pedagogie.DAO.JuryDao;
import net.eni.gestion.pedagogie.commun.composant.Connexion;
import net.eni.gestion.pedagogie.commun.outil.CRUDHelper;
import net.eni.gestion.pedagogie.modele.Jury;

import com.google.inject.Singleton;
import com.j256.ormlite.dao.BaseDaoImpl;

/**
 * @author jollivier
 * Service métier "Jury"
 */
@Singleton
public class JuryDaoImpl extends BaseDaoImpl<Jury, String> implements JuryDao{
	
	
	/**
	 * Constructeur de la DAO JuryBase
	 * @throws SQLException
	 */
	public JuryDaoImpl() throws SQLException {
		super(Connexion.getConnexion(), Jury.class);
	}
	
	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.contrat.generique.CRUDBase#charger(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public ArrayList<Jury> charger(Jury pJury) throws Exception {
		return CRUDHelper.charger(this, pJury);
	}
	
	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.generique.CRUDBase#chargerDetail(net.eni.gestion.pedagogie.modele.Jury)
	 */
	public Jury chargerDetail(Jury pJury) throws Exception {
		return CRUDHelper.chargerDetail(this, pJury);
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.contrat.generique.CRUDBase#ajouter(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public Jury ajouter(Jury pJury) throws Exception {
		return CRUDHelper.ajouter(this, pJury);
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.contrat.generique.CRUDBase#mettreAJour(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public Jury mettreAJour(Jury pJury) throws Exception {
		return CRUDHelper.mettreAJour(this, pJury);
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.contrat.generique.CRUDBase#supprimer(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public Jury supprimer(Jury pJury) throws Exception {
		return CRUDHelper.supprimer(this, pJury);
	}

}
