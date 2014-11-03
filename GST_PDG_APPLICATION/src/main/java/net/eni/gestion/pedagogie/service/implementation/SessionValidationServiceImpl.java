package net.eni.gestion.pedagogie.service.implementation;

import java.sql.SQLException;
import java.util.ArrayList;

import net.eni.gestion.pedagogie.DAO.SessionValidationDao;
import net.eni.gestion.pedagogie.DAO.SessionValidationStagiaireDao;
import net.eni.gestion.pedagogie.commun.composant.GenericException;
import net.eni.gestion.pedagogie.modele.SessionValidation;
import net.eni.gestion.pedagogie.modele.SessionValidationStagiaire;
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
			SessionValidationStagiaireDao pSessionValidationStagiaireDao)
			throws SQLException {
		super(pSessionValidationDao);
		this.sessionValidationStagiaireDao = pSessionValidationStagiaireDao;

	}
    
    @Override
	public SessionValidation chargerDetail(Integer pId)
			throws GenericException {
		SessionValidation lSessionValidation = super.chargerDetail(pId);
		lSessionValidation.getSessionValidationStagiaires();
		return lSessionValidation;
	}
    
    

	@Override
	public SessionValidation ajouter(SessionValidation pModel)
			throws GenericException {
		SessionValidation lUpdatedModel = super.ajouter(pModel);
		try {
			ArrayList<SessionValidationStagiaire> lSessionValidationStagiaires = lUpdatedModel.getSessionValidationStagiaires();
			SessionValidation lSessionValidation = new SessionValidation();
			lSessionValidation.setId(pModel.getId());
			for (SessionValidationStagiaire lSessionValidationStagiaire : lSessionValidationStagiaires) {
				lSessionValidationStagiaire.setSessionValidation(lSessionValidation);
			}
			this.sessionValidationStagiaireDao
					.mettreAJourCollectionStagiaireForSessionValidation(
							lUpdatedModel,
							lSessionValidationStagiaires);
			return lUpdatedModel;
		} catch (Exception e) {
			throw new GenericException(
					"Echec lors de la mise à jour en base de données.");
		}
	}

	@Override
	public SessionValidation mettreAJour(SessionValidation pModel)
			throws GenericException {
		SessionValidation lUpdatedModel = super.mettreAJour(pModel);
		try {
			ArrayList<SessionValidationStagiaire> lSessionValidationStagiaires = lUpdatedModel.getSessionValidationStagiaires();
			SessionValidation lSessionValidation = new SessionValidation();
			lSessionValidation.setId(pModel.getId());
			for (SessionValidationStagiaire lSessionValidationStagiaire : lSessionValidationStagiaires) {
				lSessionValidationStagiaire.setSessionValidation(lSessionValidation);
			}
			this.sessionValidationStagiaireDao
					.mettreAJourCollectionStagiaireForSessionValidation(
							lUpdatedModel,
							lSessionValidationStagiaires);
			return lUpdatedModel;
		} catch (Exception e) {
			throw new GenericException(
					"Echec lors de la mise à jour en base de données.");
		}
	}
	
	@Override
	public SessionValidation getInstanceData(Integer id) throws GenericException {
		try {
			SessionValidation session = dao.chargerDetail(id);
			session.getSessionValidationStagiaires();
			for (SessionValidationStagiaire svs : session.getSessionValidationStagiaires()) {
				svs.getInstanceSessionValidation().getReservationSalle().getSalle();
			}
			
			return session;
//			return dao.getInstance(id);
		} catch (Exception e) {
			throw new GenericException("Impossible de récupérer les instances de session de validation.");
		}
	}
}
