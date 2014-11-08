package net.eni.gestion.pedagogie.service.implementation;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.eni.gestion.pedagogie.DAO.EvaluationDao;
import net.eni.gestion.pedagogie.DAO.EvaluationStagiaireDao;
import net.eni.gestion.pedagogie.DAO.InstanceEvaluationDao;
import net.eni.gestion.pedagogie.DAO.ReservationSalleDao;
import net.eni.gestion.pedagogie.DAO.implementation.InstanceEvaluationDaoImpl;
import net.eni.gestion.pedagogie.commun.composant.erreur.ApplicationException;
import net.eni.gestion.pedagogie.commun.composant.instancePlanning.InstancePlanning;
import net.eni.gestion.pedagogie.commun.composant.tuple.Pair;
import net.eni.gestion.pedagogie.commun.modele.Evaluation;
import net.eni.gestion.pedagogie.commun.modele.EvaluationStagiaire;
import net.eni.gestion.pedagogie.commun.modele.InstanceEvaluation;
import net.eni.gestion.pedagogie.commun.modele.ReservationSalle;
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
	protected final ReservationSalleDao reservationSalleDao;
	protected final InstanceEvaluationDao instanceEvaluationDao;
	
       /**
     * Constructeur
     * @param DAO evaluation
     * @throws SQLException
     */
    @Inject
    public EvaluationServiceImpl(EvaluationDao pEvaluationDao, EvaluationStagiaireDao pEvaluationStagiaireDao, 
    		ReservationSalleDao reservationSalleDao, InstanceEvaluationDaoImpl instanceEvaluationDaoImpl) throws SQLException {
        super(pEvaluationDao);
        this.evaluationStagiaireDao = pEvaluationStagiaireDao;
        this.reservationSalleDao = reservationSalleDao;
        this.instanceEvaluationDao = instanceEvaluationDaoImpl;
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
	
	@Override
	public Evaluation saveInstanceData(InstancePlanning<InstanceEvaluation, EvaluationStagiaire> instances)
			throws ApplicationException {
		
		for (Pair<InstanceEvaluation, List<EvaluationStagiaire>> instance : instances.getInstances()) {
			ReservationSalle reservationSalle = null;
			InstanceEvaluation instanceEvaluation = null;
			try {
				reservationSalle = reservationSalleDao.addOrUpdate(instance.getFirst().getReservationSalle());
			} catch (Exception e) {
				throw new ApplicationException("Erreur lors de l'enregistrement de la réservation de salle");
			}
			
			instance.getFirst().setReservationSalle(reservationSalle);
			try {
				instanceEvaluation = instanceEvaluationDao.addOrUpdate(instance.getFirst());
			} catch (Exception e) {
				throw new ApplicationException("Erreur lors de l'enregistrement des instances d'évaluations");
			}
			
			for (EvaluationStagiaire instanceStagiaire : instance.getSecond()) {
				instanceStagiaire.setInstanceEvaluation(instanceEvaluation);
				try {
					evaluationStagiaireDao.addOrUpdate(instanceStagiaire);
				} catch (Exception e) {
					throw new ApplicationException("Erreur lors de l'enregistrement des instances stagiaires");
				}
			}
			
		}
		
		for (EvaluationStagiaire evaluationStagiaire : instances.getInstancesStagiaires()) {
			try {
				evaluationStagiaireDao.addOrUpdate(evaluationStagiaire);
			} catch (Exception e) {
				throw new ApplicationException("Erreur lors de l'enregistrement des stagiaires sans instance");
			}
		}
		
		return null;
	}

}
