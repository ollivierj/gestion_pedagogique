package net.eni.gestion.pedagogie.DAO.implementation;

import java.sql.SQLException;

import net.eni.gestion.pedagogie.DAO.SessionValidationDao;
import net.eni.gestion.pedagogie.commun.composant.erreur.ApplicationException;
import net.eni.gestion.pedagogie.commun.configuration.ModeleMetier;
import net.eni.gestion.pedagogie.commun.modele.InstanceSessionValidation;
import net.eni.gestion.pedagogie.commun.modele.ReservationSalle;
import net.eni.gestion.pedagogie.commun.modele.Salle;
import net.eni.gestion.pedagogie.commun.modele.SessionValidation;
import net.eni.gestion.pedagogie.commun.modele.SessionValidationStagiaire;
import net.eni.gestion.pedagogie.commun.modele.StagiairePromotion;

import com.google.inject.Singleton;

/**
 * @author jollivier
 * Service métier "SessionValidation"
 */
@Singleton
public class SessionValidationDaoImpl extends ADaoImpl<SessionValidation, Integer> implements SessionValidationDao{
	
	/**
	 * Constructeur de la DAO SessionValidationBase
	 * @throws SQLException
	 */
	public SessionValidationDaoImpl() throws SQLException {
		super( SessionValidation.class);
	}

	@Override
	public SessionValidation getInstance(Integer id) throws ApplicationException {
		try {
			StringBuilder lQuery = new StringBuilder();
			lQuery.append("SELECT * ");
//			lQuery.append(StringUtils.join(ORMLiteHelper.getProjectionFields(this.getTableInfo()), ","));
			lQuery.append(" FROM  ");
			
			lQuery.append(ModeleMetier.SESSION_VALIDATION_TABLE_NAME);
			
			lQuery.append(getJoinSessionStagiaire());
			lQuery.append(getJoinStagiaire());
			lQuery.append(getJoinSessionInstance());
			lQuery.append(getJoinSessionResa());
			lQuery.append(getJoinSessionSalle());
			
			lQuery.append(" WHERE ");
			lQuery.append(SessionValidation.ID_FIELD_NAME);
			lQuery.append(" = ");
			lQuery.append(id);

			return this.queryRaw(lQuery.toString(), this.getRawRowMapper()).getFirstResult();
		} catch (Exception exception) {
			throw new ApplicationException("Echec de chargement des données des intances de session de validationq");
		}
	}
	
	private String getJoinSessionSalle() {
		return getJoin(ModeleMetier.SALLE_TABLE_NAME, Salle.ID_FIELD_NAME,
				ModeleMetier.RESERVATION_SALLE_TABLE_NAME, ReservationSalle.SALLE_FIELD_NAME);
	}
	
	private String getJoinSessionResa() {
		return getJoin(ModeleMetier.RESERVATION_SALLE_TABLE_NAME, ReservationSalle.ID_FIELD_NAME, 
				ModeleMetier.INSTANCE_SESSION_VALIDATION_TABLE_NAME, InstanceSessionValidation.RESERVATION_SALLE_FIELD_NAME);
	}
	
	private String getJoinSessionInstance() {
		return getJoin(ModeleMetier.INSTANCE_SESSION_VALIDATION_TABLE_NAME, InstanceSessionValidation.SESSION_VALIDATION_FIELD_NAME, 
				ModeleMetier.SESSION_VALIDATION_TABLE_NAME, SessionValidation.ID_FIELD_NAME);
	}
	
	private String getJoinStagiaire() {
		return getJoin(ModeleMetier.STAGIAIREPROMO_VIEW_NAME, StagiairePromotion.ID_FIELD_NAME, 
				ModeleMetier.SESSION_VALIDATION_STAGIAIRE_TABLE_NAME, SessionValidationStagiaire.STAGIAIRE_FIELD_NAME);
	}
	
	private String getJoinSessionStagiaire() {
		return getJoin(ModeleMetier.SESSION_VALIDATION_STAGIAIRE_TABLE_NAME, SessionValidationStagiaire.SESSION_VALIDATION_FIELD_NAME,
				ModeleMetier.SESSION_VALIDATION_TABLE_NAME, SessionValidation.ID_FIELD_NAME);
	}
	
	private String getJoin(String tableA, String tableFieldA, String tableB, String tableFieldB) {
		String join = " INNER JOIN ";
		join = join.concat(tableA).concat(" ON ");
		join = join.concat(tableA).concat(".").concat(tableFieldA);
		join = join.concat(" = ");
		join = join.concat(tableB).concat(".").concat(tableFieldB);
		return join;
	}
	
	@Override
	protected boolean validerAvantSuppression(Integer pId) throws  ApplicationException {
		StringBuilder lQuery = new StringBuilder();
		lQuery.append("SELECT TOP 1 1 FROM ");
		lQuery.append(ModeleMetier.INSTANCE_SESSION_VALIDATION_TABLE_NAME);
		lQuery.append(" WHERE ");
		lQuery.append(InstanceSessionValidation.SESSION_VALIDATION_FIELD_NAME);
		lQuery.append("=");
		lQuery.append(pId);
		String[] instanceExist;
		try {
			instanceExist = this.queryRaw(lQuery.toString()).getFirstResult();
		} catch (SQLException e) {
			throw new ApplicationException("Echec lors de la validation en base de données");
		}
		if (null==instanceExist){
			return true;
		}else {
			throw new ApplicationException("Il existe au moins une instance de session de validation déclarée pour cette session de validation.\n Il n'est donc pas possible de supprimer cette session de validation");
		}
	}

	@Override
	protected boolean validerAvantMiseAJour(SessionValidation pMember)
			throws ApplicationException {
		boolean isValid=false;
		String[] lInstances=null;
		SessionValidation lSessionValidation = null;
		try {
			lSessionValidation = this.queryForId(pMember.getId());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			throw new ApplicationException("Echec lors de la validation en base de données");
		}
		StringBuilder lQuery = new StringBuilder();
		lQuery.append("SELECT TOP 1 1 FROM ");
		lQuery.append(ModeleMetier.INSTANCE_SESSION_VALIDATION_TABLE_NAME);
		lQuery.append(" WHERE ");
		lQuery.append(InstanceSessionValidation.SESSION_VALIDATION_FIELD_NAME);
		lQuery.append("=");
		lQuery.append(pMember.getId());
		try {
			lInstances = this.queryRaw(lQuery.toString()).getFirstResult();
		} catch (SQLException e) {
			throw new ApplicationException("Echec lors de la validation en base de données");
		}
		
		if (null==lInstances){
			isValid=true;
		}else{
			isValid = 
				(null!= lSessionValidation)? 
					(lSessionValidation.getDateDebut().equals(pMember.getDateDebut()) && lSessionValidation.getDateFin().equals(pMember.getDateFin()))
					:
					false; 
		}
	
		if (isValid){
			return true;
		}else {
			throw new ApplicationException("Il existe au moins une instance de cours déclarée pour cette évaluation.\n Il n'est donc pas possible de modifier les dates pour cette évaluation");
		}
	}	
}
	


