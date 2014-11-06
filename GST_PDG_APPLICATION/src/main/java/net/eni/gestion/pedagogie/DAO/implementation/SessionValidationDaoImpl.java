package net.eni.gestion.pedagogie.DAO.implementation;

import java.sql.SQLException;

import net.eni.gestion.pedagogie.DAO.SessionValidationDao;
import net.eni.gestion.pedagogie.commun.configuration.ModeleMetier;
import net.eni.gestion.pedagogie.commun.modele.InstanceSessionValidation;
import net.eni.gestion.pedagogie.commun.modele.ReservationSalle;
import net.eni.gestion.pedagogie.commun.modele.Salle;
import net.eni.gestion.pedagogie.commun.modele.SessionValidation;
import net.eni.gestion.pedagogie.commun.modele.SessionValidationStagiaire;
import net.eni.gestion.pedagogie.commun.modele.StagiairePromotion;
import net.eni.gestion.pedagogie.commun.composant.connexion.Connexion;


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
		super(Connexion.getConnexion(), SessionValidation.class);
	}

	@Override
	public SessionValidation getInstance(Integer id) throws Exception {
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
			throw new Exception("Echec de chargement des données des intances de session de validationq");
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
	
}

