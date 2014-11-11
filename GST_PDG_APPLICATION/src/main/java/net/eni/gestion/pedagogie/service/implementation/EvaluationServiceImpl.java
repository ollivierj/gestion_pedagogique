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
 * @author jollivier Classe d'implémentation pour le module de suivi des
 *         evaluations
 */
@Singleton
public class EvaluationServiceImpl extends
		AServiceImpl<Evaluation, Integer, EvaluationDao> implements
		EvaluationService {

	protected final EvaluationStagiaireDao evaluationStagiaireDao;
	protected final ReservationSalleDao reservationSalleDao;
	protected final InstanceEvaluationDao instanceEvaluationDao;

	/**
	 * Constructeur
	 * 
	 * @param DAO
	 *            evaluation
	 * @throws SQLException
	 */
	@Inject
	public EvaluationServiceImpl(EvaluationDao pEvaluationDao,
			EvaluationStagiaireDao pEvaluationStagiaireDao,
			ReservationSalleDao reservationSalleDao,
			InstanceEvaluationDaoImpl instanceEvaluationDaoImpl)
			throws SQLException {
		super(pEvaluationDao);
		this.evaluationStagiaireDao = pEvaluationStagiaireDao;
		this.reservationSalleDao = reservationSalleDao;
		this.instanceEvaluationDao = instanceEvaluationDaoImpl;
	}

	@Override
	public Evaluation chargerDetail(Integer pId) throws ApplicationException {
		Evaluation lEvaluation = super.chargerDetail(pId);
		lEvaluation.getEvaluationStagiaires();
		return lEvaluation;
	}

	@Override
	public Evaluation ajouter(Evaluation pModel) throws ApplicationException {
		Evaluation lUpdatedModel = super.ajouter(pModel);
		ArrayList<EvaluationStagiaire> lEvaluationStagiaires = lUpdatedModel
				.getEvaluationStagiaires();
		Evaluation lEvaluation = new Evaluation();
		lEvaluation.setId(pModel.getId());
		for (EvaluationStagiaire lEvaluationStagiaire : lEvaluationStagiaires) {
			lEvaluationStagiaire.setEvaluation(lEvaluation);
		}
		this.evaluationStagiaireDao
				.mettreAJourCollectionStagiaireForEvaluation(lUpdatedModel,
						lEvaluationStagiaires);
		return lUpdatedModel;
	}

	@Override
	public Evaluation mettreAJour(Evaluation pModel)
			throws ApplicationException {
		Evaluation lUpdatedModel = super.mettreAJour(pModel);
		ArrayList<EvaluationStagiaire> lEvaluationStagiaires = lUpdatedModel
				.getEvaluationStagiaires();
		Evaluation lEvaluation = new Evaluation();
		lEvaluation.setId(pModel.getId());
		for (EvaluationStagiaire lEvaluationStagiaire : lEvaluationStagiaires) {
			lEvaluationStagiaire.setEvaluation(lEvaluation);
		}
		this.evaluationStagiaireDao
				.mettreAJourCollectionStagiaireForEvaluation(lUpdatedModel,
						lEvaluationStagiaires);
		return lUpdatedModel;
	}

	@Override
	public Evaluation saveInstanceData(
			InstancePlanning<InstanceEvaluation, EvaluationStagiaire> instances)
			throws ApplicationException {
		
		//Enregistrement des stagiaires liéss aux instances
		for (Pair<InstanceEvaluation, List<EvaluationStagiaire>> instance : instances.getInstances()) {
			ReservationSalle reservationSalle = null;
			InstanceEvaluation instanceEvaluation = null;
			//Réservation de la salle
			reservationSalle = reservationSalleDao.addOrUpdate(instance.getFirst().getReservationSalle());
			
			instance.getFirst().setReservationSalle(reservationSalle);
			//Enregistrement de l'instance
			instanceEvaluation = instanceEvaluationDao.addOrUpdate(instance.getFirst());
			
			//Enregistrement de stagiaires liés à l'instance
			for (EvaluationStagiaire instanceStagiaire : instance.getSecond()) {
				//Affectation de l'instance précédemment enregistrée pour récupérer son id.
				instanceStagiaire.setInstanceEvaluation(instanceEvaluation);
				evaluationStagiaireDao.addOrUpdate(instanceStagiaire);
			}

		}
		
		//Enregistrement des stagiaires non associés à une instance
		for (EvaluationStagiaire evaluationStagiaire : instances.getInstancesStagiaires()) {
			evaluationStagiaireDao.addOrUpdate(evaluationStagiaire);
		}
		
		//Suppression des instances
		for (InstanceEvaluation instanceEvaluation : instances.getInstancesToDelete()) {
			ReservationSalle reservationSalle = instanceEvaluation.getReservationSalle();
			try {
				instanceEvaluationDao.supprimer(instanceEvaluation.getId());
			} catch (Exception e) {
				throw new ApplicationException("Erreur lors de la suppression d'une instance.");
			}
			try {
				reservationSalleDao.supprimer(reservationSalle.getId());
			} catch (Exception e) {
				throw new ApplicationException("Erreur lors de la suppression d'une réservation de salle.");
			}
		}

		return null;
	}

}
