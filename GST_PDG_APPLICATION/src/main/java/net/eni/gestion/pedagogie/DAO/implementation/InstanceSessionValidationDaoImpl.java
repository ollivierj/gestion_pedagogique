package net.eni.gestion.pedagogie.DAO.implementation;

import java.sql.SQLException;
import java.util.List;

import net.eni.gestion.pedagogie.DAO.InstanceSessionValidationDao;
import net.eni.gestion.pedagogie.commun.composant.erreur.ApplicationException;
import net.eni.gestion.pedagogie.commun.modele.InstanceSessionValidation;
import net.eni.gestion.pedagogie.commun.modele.SessionValidation;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.j256.ormlite.support.ConnectionSource;

/**
 * @author jollivier
 * Service métier "InstanceSessionValidation"
 */
@Singleton
public class InstanceSessionValidationDaoImpl extends ADaoImpl<InstanceSessionValidation, Integer> implements InstanceSessionValidationDao{
		
	/**
	 * Constructeur de la DAO InstanceSessionValidationBase
	 * @throws SQLException
	 */
	@Inject
	public InstanceSessionValidationDaoImpl(Provider<ConnectionSource> connection) throws SQLException {
		super(connection, InstanceSessionValidation.class);
	}

	@Override
	public List<InstanceSessionValidation> getInstancesBySession(
			SessionValidation sessionValidation) throws ApplicationException {
		
		List<InstanceSessionValidation> instances = null;
		
		try {
			instances = this.queryBuilder().where().eq(InstanceSessionValidation.SESSION_VALIDATION_FIELD_NAME, sessionValidation.getId()).query();
		} catch (SQLException e) {
			throw new ApplicationException("Impossible de récupérer les instances de session de validation");
		}
		
		return instances;
	}

}
