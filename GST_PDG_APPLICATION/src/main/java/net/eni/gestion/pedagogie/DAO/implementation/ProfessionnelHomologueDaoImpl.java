package net.eni.gestion.pedagogie.DAO.implementation;

import java.sql.SQLException;
import java.util.ArrayList;

import net.eni.gestion.pedagogie.DAO.ProfessionnelHomologueDao;
import net.eni.gestion.pedagogie.commun.composant.Connexion;
import net.eni.gestion.pedagogie.commun.outil.CRUDHelper;
import net.eni.gestion.pedagogie.modele.ProfessionnelHomologue;

import com.google.inject.Singleton;
import com.j256.ormlite.dao.BaseDaoImpl;

/**
 * @author jollivier
 * Service métier "ProfessionnelHomologue"
 */
@Singleton
public class ProfessionnelHomologueDaoImpl extends BaseDaoImpl<ProfessionnelHomologue, Integer> implements ProfessionnelHomologueDao{
	
	
	/**
	 * Constructeur de la DAO ProfessionnelHomologueBase
	 * @throws SQLException
	 */
	public ProfessionnelHomologueDaoImpl() throws SQLException {
		super(Connexion.getConnexion(), ProfessionnelHomologue.class);
	}
	
	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.contrat.generique.CRUDBase#charger(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public ArrayList<ProfessionnelHomologue> charger(ProfessionnelHomologue pProfessionnelHomologue) throws Exception {
		return CRUDHelper.charger(this, pProfessionnelHomologue);
	}
	
	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.generique.CRUDBase#chargerDetail(net.eni.gestion.pedagogie.modele.ProfessionnelHomologue)
	 */
	public ProfessionnelHomologue chargerDetail(ProfessionnelHomologue pProfessionnelHomologue) throws Exception {
		return CRUDHelper.chargerDetail(this, pProfessionnelHomologue);
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.contrat.generique.CRUDBase#ajouter(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public ProfessionnelHomologue ajouter(ProfessionnelHomologue pProfessionnelHomologue) throws Exception {
		return CRUDHelper.ajouter(this, pProfessionnelHomologue);
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.contrat.generique.CRUDBase#mettreAJour(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public ProfessionnelHomologue mettreAJour(ProfessionnelHomologue pProfessionnelHomologue) throws Exception {
		return CRUDHelper.mettreAJour(this, pProfessionnelHomologue);
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.contrat.generique.CRUDBase#supprimer(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public ProfessionnelHomologue supprimer(ProfessionnelHomologue pProfessionnelHomologue) throws Exception {
		return CRUDHelper.supprimer(this, pProfessionnelHomologue);
	}

}
