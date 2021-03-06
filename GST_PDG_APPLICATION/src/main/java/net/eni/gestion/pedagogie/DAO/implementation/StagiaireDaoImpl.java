package net.eni.gestion.pedagogie.DAO.implementation;

import java.sql.SQLException;
import java.util.List;

import net.eni.gestion.pedagogie.DAO.StagiaireDao;
import net.eni.gestion.pedagogie.commun.composant.erreur.ApplicationException;
import net.eni.gestion.pedagogie.commun.modele.Stagiaire;
import net.eni.gestion.pedagogie.commun.outil.CRUDHelper;
import net.eni.gestion.pedagogie.commun.outil.SQLHelper;

import com.google.inject.Singleton;
import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.stmt.QueryBuilder;

/**
 * @author jollivier Service métier "Stagiaire"
 */
@Singleton
public class StagiaireDaoImpl extends ADaoImpl<Stagiaire, Integer> implements
		StagiaireDao {

	/**
	 * Constructeur de la DAO StagiaireBase
	 * 
	 * @throws SQLException
	 */
	public StagiaireDaoImpl() throws SQLException {
		super(Stagiaire.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.eni.gestion.pedagogie.DAO.base.contrat.generique.CRUDBase#ajouter
	 * (net.eni.gestion.pedagogie.modele.AModele)
	 */
	public Stagiaire ajouter(Stagiaire pStagiaire) throws ApplicationException {
		// La colonne primaire CodeStagiaire n'a pas de propriété identité
		QueryBuilder<Stagiaire, Integer> lQueryBuilder = this.queryBuilder();
		lQueryBuilder.selectRaw("MAX(" + Stagiaire.ID_FIELD_NAME + ")");
		GenericRawResults<String[]> results;
		List<String[]> result=null;
		try {
			results = this.queryRaw(lQueryBuilder.prepareStatementString());
			result = results.getResults();
		} catch (SQLException e) {
			throw new ApplicationException(
					"Erreur lors de la récupération de l'identifiant du stagaire");
		}
		int maxId = Integer.parseInt(result.get(0)[0]);
		pStagiaire.setId(++maxId);
		return CRUDHelper.ajouter(this, pStagiaire);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.eni.gestion.pedagogie.DAO.implementation.ADaoImpl#supprimer(java.
	 * lang.Object)
	 */
	public Integer supprimer(Integer pId) throws ApplicationException {
		// Provisoire le mapping n'ayant pas été fait encore
		StringBuilder lDeleteQuery1 = new StringBuilder();
		lDeleteQuery1
				.append("DELETE FROM PlanningIndividuelDetail WHERE CodePlanning IN (SELECT CodePlanning FROM PlanningIndividuelFormation WHERE CodeStagiaire = ")
				.append(SQLHelper.rawSQLArgument(pId)).append(");");
		StringBuilder lDeleteQuery2 = new StringBuilder();
		lDeleteQuery2
				.append("DELETE FROM PlanningIndividuelFormation WHERE CodeStagiaire = ")
				.append(SQLHelper.rawSQLArgument(pId)).append(";");
		StringBuilder lDeleteQuery3 = new StringBuilder();
		lDeleteQuery3
				.append("DELETE FROM StagiaireParEntreprise WHERE CodeStagiaire = ")
				.append(SQLHelper.rawSQLArgument(pId)).append(";");
		
		try {
			updateRaw(lDeleteQuery1.toString());
			updateRaw(lDeleteQuery2.toString());
			updateRaw(lDeleteQuery3.toString());
		} catch (SQLException e) {
			throw new ApplicationException("Erreur lors de la sauvegarde du planning de réservation");
		}
		
		return CRUDHelper.supprimer(this, pId);
	}

	public String getPhoto(Integer pId) throws SQLException {
		return this.queryBuilder().selectColumns(Stagiaire.PHOTO_FIELD_NAME)
				.where().eq(Stagiaire.ID_FIELD_NAME, pId).queryForFirst()
				.getPhoto();
	}
}
