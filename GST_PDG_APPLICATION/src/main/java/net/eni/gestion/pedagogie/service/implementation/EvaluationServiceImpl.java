package net.eni.gestion.pedagogie.service.implementation;

import java.sql.SQLException;
import java.util.ArrayList;

import net.eni.gestion.pedagogie.DAO.EvaluationDao;
import net.eni.gestion.pedagogie.DAO.EvaluationStagiaireDao;
import net.eni.gestion.pedagogie.commun.composant.erreur.ApplicationException;
import net.eni.gestion.pedagogie.commun.modele.Evaluation;
import net.eni.gestion.pedagogie.commun.modele.EvaluationStagiaire;
import net.eni.gestion.pedagogie.service.EvaluationService;

import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * @author jollivier
 * Classe d'implémentation pour le module de suivi des evaluations
 */
@Singleton
public class EvaluationServiceImpl extends AServiceImpl<Evaluation, Integer, EvaluationDao> implements EvaluationService {

	protected final EvaluationStagiaireDao evaluationStagiaireDao;
	
       /**
     * Constructeur
     * @param DAO evaluation
     * @throws SQLException
     */
    @Inject
    public EvaluationServiceImpl(EvaluationDao pEvaluationDao, EvaluationStagiaireDao pEvaluationStagiaireDao) throws SQLException {
        super(pEvaluationDao);
        this.evaluationStagiaireDao = pEvaluationStagiaireDao;
    }
    
    @Override
	public Evaluation chargerDetail(Integer pId)
			throws ApplicationException {
		Evaluation lEvaluation = super
				.chargerDetail(pId);
		lEvaluation.getEvaluationStagiaires();
		return lEvaluation;
	}
    
    @Override
	public Evaluation ajouter(Evaluation pModel)
			throws ApplicationException {
		Evaluation lUpdatedModel = super.ajouter(pModel);
		try {
			ArrayList<EvaluationStagiaire> lEvaluationStagiaires = lUpdatedModel.getEvaluationStagiaires();
			Evaluation lEvaluation = new Evaluation();
			lEvaluation.setId(pModel.getId());
			for (EvaluationStagiaire lEvaluationStagiaire : lEvaluationStagiaires) {
				lEvaluationStagiaire.setEvaluation(lEvaluation);
			}
			this.evaluationStagiaireDao.mettreAJourCollectionStagiaireForEvaluation(lUpdatedModel, lEvaluationStagiaires);
			return lUpdatedModel;
		} catch (Exception e) {
			throw new ApplicationException(
					"Echec lors de la mise à jour en base de données.");
		}
	}

	@Override
	public Evaluation mettreAJour(Evaluation pModel)
			throws ApplicationException {
		Evaluation lUpdatedModel = super.mettreAJour(pModel);
		try {
			ArrayList<EvaluationStagiaire> lEvaluationStagiaires = lUpdatedModel.getEvaluationStagiaires();
			Evaluation lEvaluation = new Evaluation();
			lEvaluation.setId(pModel.getId());
			for (EvaluationStagiaire lEvaluationStagiaire : lEvaluationStagiaires) {
				lEvaluationStagiaire.setEvaluation(lEvaluation);
			}
			this.evaluationStagiaireDao.mettreAJourCollectionStagiaireForEvaluation(lUpdatedModel, lEvaluationStagiaires);
			return lUpdatedModel;
		} catch (Exception e) {
			throw new ApplicationException(
					"Echec lors de la mise à jour en base de données.");
		}
	}

}
