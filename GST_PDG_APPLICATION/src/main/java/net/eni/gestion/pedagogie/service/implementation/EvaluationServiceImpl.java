package net.eni.gestion.pedagogie.service.implementation;

import java.sql.SQLException;
import java.util.ArrayList;

import net.eni.gestion.pedagogie.DAO.EvaluationDao;
import net.eni.gestion.pedagogie.commun.composant.GenericException;
import net.eni.gestion.pedagogie.modele.Evaluation;
import net.eni.gestion.pedagogie.service.EvaluationService;

import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * @author jollivier
 * Classe d'implémentation pour le module de gestion des évaluations
 */
@Singleton
public class EvaluationServiceImpl implements EvaluationService {

    /**
     * DAO evaluation
     */
    private final EvaluationDao evaluationDao;

    /**
     * Constructeur
     * @param DAO evaluation
     * @throws SQLException
     */
    @Inject
    public EvaluationServiceImpl(EvaluationDao pEvaluationDao) throws SQLException {
        this.evaluationDao = pEvaluationDao;
    }

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.metier.contrat.generique.CRUDUnit#charger(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public ArrayList<Evaluation> charger(Evaluation pEvaluation)
			throws GenericException {
		try {
			return this.evaluationDao.charger(pEvaluation);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	

	public Evaluation chargerDetail(Evaluation pModel) throws GenericException {
		// TODO Auto-generated method stub
		try {
			return this.evaluationDao.chargerDetail(pModel);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.metier.contrat.generique.CRUDUnit#ajouter(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public Evaluation ajouter(Evaluation pModel) throws GenericException {
		try {
			return this.evaluationDao.ajouter(pModel);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.metier.contrat.generique.CRUDUnit#mettreAJour(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public Evaluation mettreAJour(Evaluation pModel) throws GenericException {
		try {
			return this.evaluationDao.mettreAJour(pModel);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.metier.contrat.generique.CRUDUnit#supprimer(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public Evaluation supprimer(Evaluation pModel) throws GenericException {
		try {
			return this.evaluationDao.supprimer(pModel);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
