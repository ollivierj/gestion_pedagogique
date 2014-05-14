package net.eni.gestion.pedagogie.DAO.base.implementation;

import java.sql.SQLException;
import java.util.ArrayList;

import net.eni.gestion.pedagogie.DAO.base.StagiaireBase;
import net.eni.gestion.pedagogie.commun.composant.Connexion;
import net.eni.gestion.pedagogie.commun.outil.CRUDHelper;
import net.eni.gestion.pedagogie.commun.outil.SQLHelper;
import net.eni.gestion.pedagogie.modele.Stagiaire;

import com.google.inject.Singleton;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.stmt.QueryBuilder;

/**
 * @author jollivier
 * Service métier "Stagiaire"
 */
@Singleton
public class StagiaireBaseImpl extends BaseDaoImpl<Stagiaire, Integer> implements StagiaireBase{
	
	
	/**
	 * Constructeur de la DAO StagiaireBase
	 * @throws SQLException
	 */
	public StagiaireBaseImpl() throws SQLException {
		super(Connexion.getConnexion(), Stagiaire.class);
	}
	
	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.contrat.generique.CRUDBase#charger(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public ArrayList<Stagiaire> charger(Stagiaire pStagiaire) throws Exception {
		return CRUDHelper.charger(this, pStagiaire);
	}
	
	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.generique.CRUDBase#chargerDetail(net.eni.gestion.pedagogie.modele.Stagiaire)
	 */
	public Stagiaire chargerDetail(Stagiaire pStagiaire) throws Exception {
		return CRUDHelper.chargerDetail(this, pStagiaire);
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.contrat.generique.CRUDBase#ajouter(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public Stagiaire ajouter(Stagiaire pStagiaire) throws Exception {
		// La colonne primaire CodeStagiaire n'a pas de propriété identité
		QueryBuilder<Stagiaire, Integer> lQueryBuilder = this.queryBuilder();
		lQueryBuilder.selectRaw("MAX("+Stagiaire.ID_FIELD_NAME+")");
		GenericRawResults<String[]> results = this.queryRaw(lQueryBuilder.prepareStatementString());
		String[] result = results.getFirstResult();
		int maxId = Integer.parseInt(result[0]);
		pStagiaire.setId(++maxId);
		return CRUDHelper.ajouter(this, pStagiaire);
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.contrat.generique.CRUDBase#mettreAJour(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public Stagiaire mettreAJour(Stagiaire pStagiaire) throws Exception {
		return CRUDHelper.mettreAJour(this, pStagiaire);
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.contrat.generique.CRUDBase#supprimer(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public Stagiaire supprimer(Stagiaire pStagiaire) throws Exception {
		// Provisoire le mapping n'ayant pas été fait encore
		StringBuilder lDeleteQuery1 = new StringBuilder();
			lDeleteQuery1.append("DELETE FROM PlanningIndividuelDetail WHERE CodePlanning IN (SELECT CodePlanning FROM PlanningIndividuelFormation WHERE CodeStagiaire = ").append(SQLHelper.rawSQLArgument(pStagiaire.getId())).append(");");
		StringBuilder lDeleteQuery2 = new StringBuilder();
			lDeleteQuery2.append("DELETE FROM PlanningIndividuelFormation WHERE CodeStagiaire = ").append(SQLHelper.rawSQLArgument(pStagiaire.getId())).append(";");
		StringBuilder lDeleteQuery3 = new StringBuilder();
			lDeleteQuery3.append("DELETE FROM StagiaireParEntreprise WHERE CodeStagiaire = ").append(SQLHelper.rawSQLArgument(pStagiaire.getId())).append(";");
		updateRaw(lDeleteQuery1.toString());
		updateRaw(lDeleteQuery2.toString());
		updateRaw(lDeleteQuery3.toString());
		return CRUDHelper.supprimer(this, pStagiaire);
	}


}
