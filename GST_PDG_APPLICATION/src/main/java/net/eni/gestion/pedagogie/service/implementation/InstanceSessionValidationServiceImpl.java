package net.eni.gestion.pedagogie.service.implementation;

import java.sql.SQLException;
import java.util.List;

import net.eni.gestion.pedagogie.DAO.InstanceSessionValidationDao;
import net.eni.gestion.pedagogie.commun.composant.erreur.ApplicationException;
import net.eni.gestion.pedagogie.commun.modele.InstanceSessionValidation;
import net.eni.gestion.pedagogie.commun.modele.SessionValidation;
import net.eni.gestion.pedagogie.service.InstanceSessionValidationService;

import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * @author jollivier Classe d'implémentation pour le module de suivi des
 *         evaluations
 */
@Singleton
public class InstanceSessionValidationServiceImpl
		extends
		AServiceImpl<InstanceSessionValidation, Integer, InstanceSessionValidationDao>
		implements InstanceSessionValidationService {

	/**
	 * Constructeur
	 * 
	 * @param DAO
	 *            evaluation
	 * @throws SQLException
	 */
	@Inject
	public InstanceSessionValidationServiceImpl(
			InstanceSessionValidationDao pInstanceSessionValidationDao)
			throws SQLException {
		super(pInstanceSessionValidationDao);
	}

	@Override
	public List<InstanceSessionValidation> getInstancesBySession(
			SessionValidation sessionValidation) throws ApplicationException {

		List<InstanceSessionValidation> instances = null;
		instances = dao.getInstancesBySession(sessionValidation);
		return instances;
	}

}
