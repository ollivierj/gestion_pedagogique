package net.eni.gestion.pedagogie.DAO.implementation;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import net.eni.gestion.pedagogie.DAO.UtilisateurDao;
import net.eni.gestion.pedagogie.commun.composant.erreur.ApplicationException;
import net.eni.gestion.pedagogie.commun.configuration.ModeleMetier;
import net.eni.gestion.pedagogie.commun.modele.Absence;
import net.eni.gestion.pedagogie.commun.modele.Avis;
import net.eni.gestion.pedagogie.commun.modele.Echange;
import net.eni.gestion.pedagogie.commun.modele.Evaluation;
import net.eni.gestion.pedagogie.commun.modele.InstanceCours;
import net.eni.gestion.pedagogie.commun.modele.InstanceEvaluation;
import net.eni.gestion.pedagogie.commun.modele.SessionValidation;
import net.eni.gestion.pedagogie.commun.modele.SujetEvaluation;
import net.eni.gestion.pedagogie.commun.modele.Utilisateur;
import net.eni.gestion.pedagogie.commun.outil.ORMLiteHelper;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import com.google.inject.Singleton;

/**
 * @author jollivier Service métier "Utilisateur"
 */
@Singleton
public class UtilisateurDaoImpl extends ADaoImpl<Utilisateur, Integer>
		implements UtilisateurDao {

	/**
	 * Constructeur de la DAO UtilisateurBase
	 * 
	 * @throws SQLException
	 */
	public UtilisateurDaoImpl() throws SQLException {
		super(Utilisateur.class);
	}

	public HashMap<String, String> getTitleMap() throws ApplicationException {
		try {
			Iterator<Utilisateur> lUtilisateurs = this.queryForAll().iterator();
			HashMap<String, String> lResults = new HashMap<String, String>();
			while (lUtilisateurs.hasNext()) {
				Utilisateur lUtilisateur = lUtilisateurs.next();
				StringBuilder lStrBuilder = new StringBuilder();
				lStrBuilder.append(lUtilisateur.getPrenom());
				lStrBuilder.append(" ");
				lStrBuilder.append(lUtilisateur.getNom());
				lResults.put(lUtilisateur.getId().toString(),
						lStrBuilder.toString());
			}
			return lResults;
		} catch (Exception exception) {
			throw new ApplicationException(
					"Echec de chargement de la liste d'enregistrements depuis la base de données");
		}
	}

	public Integer checkConnection(String pLogin, String pMotdePasse,
			boolean loginOnly) throws ApplicationException {
		try {
			StringBuilder lQuery = new StringBuilder();
			lQuery.append("SELECT TOP 1 UTIL_ID ");
			lQuery.append(" FROM UTILISATEUR");
			lQuery.append(" WHERE UTILISATEUR.UTIL_LOGIN = '");
			lQuery.append(pLogin);
			lQuery.append("'");
			if (!loginOnly) {
				lQuery.append("' AND UTILISATEUR.UTIL_MOT_PASSE = '");
				lQuery.append(pMotdePasse);
				lQuery.append("'");
			}
			String[] lResult = this.queryRaw(lQuery.toString())
					.getFirstResult();
			if (null != lResult) {
				return Integer.parseInt(lResult[0]);
			} else {
				return null;
			}
		} catch (Exception exception) {
			throw new ApplicationException(
					"Echec de chargement de la liste d'enregistrements depuis la base de données");
		}
	}

	public boolean checkToken(String token) throws ApplicationException {
		try {
			StringBuilder lQuery = new StringBuilder();
			lQuery.append("SELECT TOP 1 UTIL_ID ");
			lQuery.append(" FROM UTILISATEUR");
			lQuery.append(" WHERE ");
			lQuery.append(Utilisateur.TOKEN_FIELD_NAME);
			lQuery.append("=");
			lQuery.append("'");
			lQuery.append(token);
			lQuery.append("'");
			lQuery.append(" AND ");
			lQuery.append(Utilisateur.DATE_EXPIRATION_FIELD_NAME);
			lQuery.append(">");
			lQuery.append("'");
			lQuery.append(DateFormatUtils.format(new Date(),
					"yyyy-dd-MM HH:mm:ss"));
			lQuery.append("'");
			return (this.queryRaw(lQuery.toString()).getFirstResult().length == 1);
		} catch (Exception exception) {
			throw new ApplicationException(
					"Echec de chargement de la liste d'enregistrements depuis la base de données");
		}
	}

	public Utilisateur loginwithtoken(String token) throws ApplicationException {
		try {
			StringBuilder lQuery = new StringBuilder();
			lQuery.append("SELECT TOP 1 * ");
			lQuery.append(" FROM UTILISATEUR");
			lQuery.append(" WHERE ");
			lQuery.append(Utilisateur.TOKEN_FIELD_NAME);
			lQuery.append("=");
			lQuery.append("'");
			lQuery.append(token);
			lQuery.append("'");
			lQuery.append(" AND ");
			lQuery.append(Utilisateur.DATE_EXPIRATION_FIELD_NAME);
			lQuery.append(">");
			lQuery.append("'");
			lQuery.append(DateFormatUtils.format(new Date(),
					"yyyy-dd-MM HH:mm:ss"));
			lQuery.append("'");
			return this.queryRaw(lQuery.toString(), this.getRawRowMapper())
					.getFirstResult();
		} catch (Exception exception) {
			throw new ApplicationException(
					"Echec de chargement de la liste d'enregistrements depuis la base de données");
		}
	}

	@Override
	public List<Utilisateur> getFormateurs(String pSearchText)
			throws ApplicationException {
		List<Utilisateur> utilisateur = null;

		try {

			StringBuilder lQuery = new StringBuilder();

			lQuery.append("SELECT TOP 10 ");
			lQuery.append(StringUtils.join(
					ORMLiteHelper.getProjectionFields(this.getTableInfo()), ","));
			lQuery.append(" FROM ");
			lQuery.append(this.getTableInfo().getTableName());
			String lFullTextSearchWhereClause = ORMLiteHelper
					.getFullTextSearchWhereClause(this.getDataClass()
							.newInstance().getFullTextSearchFieldNames(),
							pSearchText);
			lQuery.append(" WHERE ");
			lQuery.append(Utilisateur.IS_FORMATEUR_FIELD_NAME);
			lQuery.append(" = ");
			lQuery.append(" 1 ");
			if (null != lFullTextSearchWhereClause) {
				lQuery.append(" AND ");
				lQuery.append(lFullTextSearchWhereClause);
			}

			utilisateur = new ArrayList<Utilisateur>(this.queryRaw(
					lQuery.toString(), this.getRawRowMapper()).getResults());
		} catch (Exception exception) {
			throw new ApplicationException(
					"Echec de chargement de la liste d'enregistrements depuis la base de données");
		}

		return utilisateur;
	}

	/*
	 * @Override public List<Utilisateur> getFormateurs(String pSearchText,
	 * String debut, String fin) throws Exception { List<Utilisateur>
	 * utilisateur = null;
	 * 
	 * try {
	 * 
	 * StringBuilder lQuery = new StringBuilder();
	 * 
	 * lQuery.append("SELECT TOP 10 ");
	 * lQuery.append(Utilisateur.ID_FIELD_NAME); lQuery.append(",");
	 * lQuery.append(Utilisateur.FONCTION_FIELD_NAME); lQuery.append(",");
	 * lQuery.append(Utilisateur.CIVILITE_FIELD_NAME); lQuery.append(",");
	 * lQuery.append(Utilisateur.NOM_FIELD_NAME); lQuery.append(",");
	 * lQuery.append(Utilisateur.PRENOM_FIELD_NAME); lQuery.append(",");
	 * lQuery.append(Utilisateur.TELEPHONE_FIXE_FIELD_NAME); lQuery.append(",");
	 * lQuery.append(Utilisateur.TELEPHONE_PORTABLE_FIELD_NAME);
	 * lQuery.append(","); lQuery.append(Utilisateur.EMAIL_FIELD_NAME);
	 * lQuery.append(","); lQuery.append(Utilisateur.PHOTO_FIELD_NAME);
	 * lQuery.append(","); lQuery.append(Utilisateur.MOT_PASSE_FIELD_NAME);
	 * lQuery.append(","); lQuery.append(Utilisateur.PROFIL_FIELD_NAME);
	 * lQuery.append(","); lQuery.append(Utilisateur.LOGIN_FIELD_NAME);
	 * lQuery.append(","); lQuery.append(Utilisateur.TOKEN_FIELD_NAME);
	 * lQuery.append(",");
	 * lQuery.append(Utilisateur.DATE_EXPIRATION_FIELD_NAME);
	 * lQuery.append(","); lQuery.append(Utilisateur.IS_FORMATEUR_FIELD_NAME);
	 * lQuery.append(",");
	 * 
	 * //Récupère le nombre de fois que le formateur est réservé.
	 * lQuery.append(" ( SELECT COUNT(*) FROM INSTANCE_COURS "); lQuery.append(
	 * " INNER JOIN RESERVATION_SALLE ON RESERVATION_SALLE.RES_SALLE_ID = INSTANCE_COURS.INST_COURS_RESERVATION_SALLE "
	 * ); lQuery.append(" WHERE INST_COURS_ANIMATEUR = UTIL_ID ");
	 * lQuery.append(
	 * " AND ( (RESERVATION_SALLE.RES_SALLE_DATE_DEBUT <= convert(datetime, '");
	 * lQuery.append(debut); lQuery.append(
	 * "', 103) AND RESERVATION_SALLE.RES_SALLE_DATE_DEBUT >= convert(datetime, '"
	 * ); lQuery.append(debut); lQuery.append(
	 * "', 103) ) OR ( RESERVATION_SALLE.RES_SALLE_DATE_FIN <= convert(datetime, '"
	 * ); lQuery.append(fin); lQuery.append(
	 * "', 103) AND RESERVATION_SALLE.RES_SALLE_DATE_FIN >= convert(datetime, '"
	 * ); lQuery.append(fin); lQuery.append(
	 * "', 103) ) OR ( RESERVATION_SALLE.RES_SALLE_DATE_DEBUT <= convert(datetime, '"
	 * ); lQuery.append(debut); lQuery.append(
	 * "', 103) AND RESERVATION_SALLE.RES_SALLE_DATE_FIN >= convert(datetime, '"
	 * ); lQuery.append(fin); lQuery.append("', 103)) ) ) AS COUNT_RESERVED ");
	 * 
	 * lQuery.append(" FROM ");
	 * lQuery.append(this.getTableInfo().getTableName()); String
	 * lFullTextSearchWhereClause =
	 * ORMLiteHelper.getFullTextSearchWhereClause(this
	 * .getDataClass().newInstance().getFullTextSearchFieldNames() ,
	 * pSearchText); lQuery.append(" WHERE ");
	 * lQuery.append(Utilisateur.IS_FORMATEUR_FIELD_NAME); lQuery.append(" = ");
	 * lQuery.append(" 1 "); if (null !=lFullTextSearchWhereClause){
	 * lQuery.append(" AND "); lQuery.append(lFullTextSearchWhereClause); }
	 * 
	 * List<String[]> test = this.queryRaw(lQuery.toString()).getResults();
	 * //utilisateur = new
	 * ArrayList<Utilisateur>(this.queryRaw(lQuery.toString(),
	 * this.getRawRowMapper()).getResults()); } catch (Exception exception) {
	 * throw new Exception(
	 * "Echec de chargement de la liste d'enregistrements depuis la base de données"
	 * ); }
	 * 
	 * 
	 * return utilisateur; }
	 */

	@Override
	protected boolean validerAvantSuppression(Integer pId) throws  ApplicationException {
		StringBuilder lQuery = new StringBuilder();
		lQuery.append("SELECT TOP 1 1 FROM ");
		lQuery.append(ModeleMetier.ABSENCE_TABLE_NAME);
		lQuery.append(" WHERE ");
		lQuery.append(Absence.AUTEUR_FIELD_NAME);
		lQuery.append("=");
		lQuery.append(pId);
		String[] instanceExist;
		try {
			instanceExist = this.queryRaw(lQuery.toString()).getFirstResult();
		
		if (null==instanceExist){
			lQuery = new StringBuilder();
			lQuery.append("SELECT TOP 1 1 FROM ");
			lQuery.append(ModeleMetier.AVIS_TABLE_NAME);
			lQuery.append(" WHERE ");
			lQuery.append(Avis.AUTEUR_FIELD_NAME);
			lQuery.append("=");
			lQuery.append(pId);
			instanceExist = this.queryRaw(lQuery.toString()).getFirstResult();
			if (null==instanceExist){
				lQuery = new StringBuilder();
				lQuery.append("SELECT TOP 1 1 FROM ");
				lQuery.append(ModeleMetier.ECHANGE_TABLE_NAME);
				lQuery.append(" WHERE ");
				lQuery.append(Echange.AUTEUR_FIELD_NAME);
				lQuery.append("=");
				lQuery.append(pId);
				instanceExist = this.queryRaw(lQuery.toString()).getFirstResult();
				if (null==instanceExist){
					lQuery = new StringBuilder();
					lQuery.append("SELECT TOP 1 1 FROM ");
					lQuery.append(ModeleMetier.EVALUATION_STAGIAIRE_TABLE_NAME);
					lQuery.append(" WHERE ");
					lQuery.append(Evaluation.AUTEUR_FIELD_NAME);
					lQuery.append("=");
					lQuery.append(pId);
					instanceExist = this.queryRaw(lQuery.toString()).getFirstResult();
					if (null==instanceExist){
						lQuery = new StringBuilder();
						lQuery.append("SELECT TOP 1 1 FROM ");
						lQuery.append(ModeleMetier.SESSION_VALIDATION_TABLE_NAME);
						lQuery.append(" WHERE ");
						lQuery.append(SessionValidation.AUTEUR_FIELD_NAME);
						lQuery.append("=");
						lQuery.append(pId);
						instanceExist = this.queryRaw(lQuery.toString()).getFirstResult();
						if (null==instanceExist){
							lQuery = new StringBuilder();
							lQuery.append("SELECT TOP 1 1 FROM ");
							lQuery.append(ModeleMetier.INSTANCE_COURS_TABLE_NAME);
							lQuery.append(" WHERE ");
							lQuery.append(InstanceCours.ANIMATEUR_FIELD_NAME);
							lQuery.append("=");
							lQuery.append(pId);
							instanceExist = this.queryRaw(lQuery.toString()).getFirstResult();
							if (null==instanceExist){
								lQuery = new StringBuilder();
								lQuery.append("SELECT TOP 1 1 FROM ");
								lQuery.append(ModeleMetier.INSTANCE_EVALUATION_TABLE_NAME);
								lQuery.append(" WHERE ");
								lQuery.append(InstanceEvaluation.SURVEILLANT_FIELD_NAME);
								lQuery.append("=");
								lQuery.append(pId);
								instanceExist = this.queryRaw(lQuery.toString()).getFirstResult();
								if (null==instanceExist){
									lQuery = new StringBuilder();
									lQuery.append("SELECT TOP 1 1 FROM ");
									lQuery.append(ModeleMetier.SUJET_EVALUATION_TABLE_NAME);
									lQuery.append(" WHERE ");
									lQuery.append(SujetEvaluation.AUTEUR_FIELD_NAME);
									lQuery.append("=");
									lQuery.append(pId);
									instanceExist = this.queryRaw(lQuery.toString()).getFirstResult();
									if (null!=instanceExist){
										throw new ApplicationException("Il existe au moins un sujet d'évaluation reférençant cet utilisateur.\n Vous ne pouvez pas supprimer cet utilisateur");
									}
								}else{
									throw new ApplicationException("Il existe au moins une instance d'évaluation reférençant cet utilisateur.\n Vous ne pouvez pas supprimer cet utilisateur");
								}
							}else{
								throw new ApplicationException("Il existe au moins une instance de cours reférençant cet utilisateur.\n Vous ne pouvez pas supprimer cet utilisateur");
							}
						}else{
							throw new ApplicationException("Il existe au moins une session de validation reférençant cet utilisateur.\n Vous ne pouvez pas supprimer cet utilisateur");
						}
					}else{
						throw new ApplicationException("Il existe au moins une évaluation reférençant cet utilisateur.\n Vous ne pouvez pas supprimer cet utilisateur");
					}
				}else{
					throw new ApplicationException("Il existe au moins un échange reférençant cet utilisateur.\n Vous ne pouvez pas supprimer cet utilisateur");
				}
			}else{
				throw new ApplicationException("Il existe au moins un avis reférençant cet utilisateur.\n Vous ne pouvez pas supprimer cet utilisateur");
			}
		}else {
			throw new ApplicationException("Il existe au moins une absence reférençant cet utilisateur.\n Vous ne pouvez pas supprimer cet utilisateur");
		}
		} catch (SQLException e) {
			throw new ApplicationException("Echec lors de la validation en base de données");
		}
		return (null==instanceExist);
	}
}
