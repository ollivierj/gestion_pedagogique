package net.eni.gestion.pedagogie.service.implementation;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.eni.gestion.pedagogie.DAO.InstanceSessionValidationDao;
import net.eni.gestion.pedagogie.DAO.ReservationSalleDao;
import net.eni.gestion.pedagogie.DAO.SessionValidationDao;
import net.eni.gestion.pedagogie.DAO.SessionValidationStagiaireDao;
import net.eni.gestion.pedagogie.commun.composant.erreur.ApplicationException;
import net.eni.gestion.pedagogie.commun.composant.instancePlanning.InstancePlanning;
import net.eni.gestion.pedagogie.commun.composant.tuple.Pair;
import net.eni.gestion.pedagogie.commun.modele.InstanceSessionValidation;
import net.eni.gestion.pedagogie.commun.modele.ReservationSalle;
import net.eni.gestion.pedagogie.commun.modele.SessionValidation;
import net.eni.gestion.pedagogie.commun.modele.SessionValidationStagiaire;
import net.eni.gestion.pedagogie.service.SessionValidationService;

import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * @author jollivier Classe d'implémentation pour le module de suivi des
 *         sessionValidations
 */
@Singleton
public class SessionValidationServiceImpl extends
		AServiceImpl<SessionValidation, Integer, SessionValidationDao>
		implements SessionValidationService {

	protected final SessionValidationStagiaireDao sessionValidationStagiaireDao;
	protected final ReservationSalleDao reservationSalleDao;
	protected final InstanceSessionValidationDao instanceSessionValidationDao;

	/**
	 * Constructeur
	 * 
	 * @param DAO
	 *            sessionValidation
	 * @throws SQLException
	 */
	@Inject
	public SessionValidationServiceImpl(
			SessionValidationDao pSessionValidationDao,
			SessionValidationStagiaireDao pSessionValidationStagiaireDao,
			ReservationSalleDao reservationSalleDao,
			InstanceSessionValidationDao instanceSessionValidationDao)
			throws SQLException {
		super(pSessionValidationDao);
		this.sessionValidationStagiaireDao = pSessionValidationStagiaireDao;
		this.reservationSalleDao = reservationSalleDao;
		this.instanceSessionValidationDao = instanceSessionValidationDao;

	}

	@Override
	public SessionValidation chargerDetail(Integer pId)
			throws ApplicationException {
		SessionValidation lSessionValidation = super.chargerDetail(pId);
		lSessionValidation.getSessionValidationStagiaires();
		return lSessionValidation;
	}

	@Override
	public SessionValidation ajouter(SessionValidation pModel)
			throws ApplicationException {
		SessionValidation lUpdatedModel = super.ajouter(pModel);
		ArrayList<SessionValidationStagiaire> lSessionValidationStagiaires = lUpdatedModel
				.getSessionValidationStagiaires();
		SessionValidation lSessionValidation = new SessionValidation();
		lSessionValidation.setId(pModel.getId());
		for (SessionValidationStagiaire lSessionValidationStagiaire : lSessionValidationStagiaires) {
			lSessionValidationStagiaire
					.setSessionValidation(lSessionValidation);
		}
		this.sessionValidationStagiaireDao
				.mettreAJourCollectionStagiaireForSessionValidation(
						lUpdatedModel, lSessionValidationStagiaires);
		return lUpdatedModel;
	}

	@Override
	public SessionValidation mettreAJour(SessionValidation pModel)
			throws ApplicationException {
		SessionValidation lUpdatedModel = super.mettreAJour(pModel);
		ArrayList<SessionValidationStagiaire> lSessionValidationStagiaires = lUpdatedModel
				.getSessionValidationStagiaires();
		SessionValidation lSessionValidation = new SessionValidation();
		lSessionValidation.setId(pModel.getId());
		for (SessionValidationStagiaire lSessionValidationStagiaire : lSessionValidationStagiaires) {
			lSessionValidationStagiaire
					.setSessionValidation(lSessionValidation);
		}
		this.sessionValidationStagiaireDao
				.mettreAJourCollectionStagiaireForSessionValidation(
						lUpdatedModel, lSessionValidationStagiaires);
		return lUpdatedModel;
	}

	@Override
	public SessionValidation saveInstanceData(
			InstancePlanning<InstanceSessionValidation, SessionValidationStagiaire> instances)
			throws ApplicationException {

		for (Pair<InstanceSessionValidation, List<SessionValidationStagiaire>> instance : instances
				.getInstances()) {
			ReservationSalle reservationSalle = null;
			InstanceSessionValidation instanceSessionValidation = null;
			try {
				reservationSalle = reservationSalleDao.addOrUpdate(instance
						.getFirst().getReservationSalle());
			} catch (Exception e) {
				throw new ApplicationException(
						"Erreur lors de l'enregistrement de la réservation de salle");
			}

			instance.getFirst().setReservationSalle(reservationSalle);
			instanceSessionValidation = instanceSessionValidationDao
					.addOrUpdate(instance.getFirst());

			for (SessionValidationStagiaire instanceStagiaire : instance
					.getSecond()) {
				instanceStagiaire
						.setInstanceSessionValidation(instanceSessionValidation);
				sessionValidationStagiaireDao.addOrUpdate(instanceStagiaire);
			}

		}

		for (SessionValidationStagiaire sessionValidationStagiaire : instances
				.getInstancesStagiaires()) {
			sessionValidationStagiaireDao
					.addOrUpdate(sessionValidationStagiaire);
		}

		return null;
	}
}
