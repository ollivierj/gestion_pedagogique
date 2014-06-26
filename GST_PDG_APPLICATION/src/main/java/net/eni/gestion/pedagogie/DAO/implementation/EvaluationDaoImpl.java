package net.eni.gestion.pedagogie.DAO.implementation;

import java.sql.SQLException;
import java.util.ArrayList;

import net.eni.gestion.pedagogie.DAO.EvaluationDao;
import net.eni.gestion.pedagogie.commun.composant.Connexion;
import net.eni.gestion.pedagogie.commun.outil.CRUDHelper;
import net.eni.gestion.pedagogie.modele.Evaluation;

import com.google.inject.Singleton;
import com.j256.ormlite.dao.BaseDaoImpl;

/**
 * @author jollivier
 * Service métier "Evaluation"
 */
@Singleton
public class EvaluationDaoImpl extends BaseDaoImpl<Evaluation, Integer> implements EvaluationDao{
	
	
	/**
	 * Constructeur de la DAO EvaluationBase
	 * @throws SQLException
	 */
	public EvaluationDaoImpl() throws SQLException {
		super(Connexion.getConnexion(), Evaluation.class);
	}
	
	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.contrat.generique.CRUDBase#charger(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public ArrayList<Evaluation> charger(Evaluation pEvaluation) throws Exception {
		return CRUDHelper.charger(this, pEvaluation);
	}
	
	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.generique.CRUDBase#chargerDetail(net.eni.gestion.pedagogie.modele.Evaluation)
	 */
	public Evaluation chargerDetail(Evaluation pEvaluation) throws Exception {
		return CRUDHelper.chargerDetail(this, pEvaluation);
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.contrat.generique.CRUDBase#ajouter(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public Evaluation ajouter(Evaluation pEvaluation) throws Exception {
		return CRUDHelper.ajouter(this, pEvaluation);
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.contrat.generique.CRUDBase#mettreAJour(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public Evaluation mettreAJour(Evaluation pEvaluation) throws Exception {
		return CRUDHelper.mettreAJour(this, pEvaluation);
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.contrat.generique.CRUDBase#supprimer(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public Evaluation supprimer(Evaluation pEvaluation) throws Exception {
		return CRUDHelper.supprimer(this, pEvaluation);
	}

}
