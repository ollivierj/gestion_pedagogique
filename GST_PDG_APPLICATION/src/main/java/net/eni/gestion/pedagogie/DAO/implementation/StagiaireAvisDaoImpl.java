package net.eni.gestion.pedagogie.DAO.implementation;

import java.sql.SQLException;
import java.util.ArrayList;

import net.eni.gestion.pedagogie.DAO.StagiaireAvisDao;
import net.eni.gestion.pedagogie.commun.composant.connexion.Connexion;
import net.eni.gestion.pedagogie.commun.composant.pagination.Pager;
import net.eni.gestion.pedagogie.commun.composant.tuple.Pair;
import net.eni.gestion.pedagogie.commun.configuration.ModeleMetier;
import net.eni.gestion.pedagogie.commun.modele.Stagiaire;
import net.eni.gestion.pedagogie.commun.modele.StagiaireAvis;
import net.eni.gestion.pedagogie.commun.outil.ORMLiteHelper;

import org.apache.commons.lang3.StringUtils;

import com.google.inject.Singleton;

/**
 * @author jollivier
 * Service métier "Avis"
 */
@Singleton
public class StagiaireAvisDaoImpl extends ADaoImpl<StagiaireAvis, Integer> implements StagiaireAvisDao{
	
	/**
	 * Constructeur de la DAO AvisBase
	 * @throws SQLException
	 */
	public StagiaireAvisDaoImpl() throws SQLException {
		super(Connexion.getConnexion(), StagiaireAvis.class);
	}

	@Override
	public Pair<ArrayList<StagiaireAvis>, Long> charger(Pager pPager)
			throws Exception {
		try {
			if (null == pPager.getId()){
				throw new Exception("Une instance de cours doit être sélectionnée.");
			}
			StringBuilder lQuery = new StringBuilder();
			lQuery.append("SELECT ");
			lQuery.append(StringUtils.join(ORMLiteHelper.getProjectionFields(this.getTableInfo()), ","));
			lQuery.append(" FROM ( ");
			lQuery.append(" SELECT *, ");
			lQuery.append(" ROW_NUMBER() OVER (");
			lQuery.append(ORMLiteHelper.getOrderByClauseFromSortOptions(this.getTableInfo(), pPager.getSortOptions()));
			lQuery.append(") AS RowNum ");
			lQuery.append(" FROM ");
			lQuery.append(this.getTableInfo().getTableName());
			lQuery.append(" INNER JOIN ");
			lQuery.append(ModeleMetier.STAGIAIRE_TABLE_NAME);
			lQuery.append(" ON ");
			lQuery.append(ModeleMetier.STAGIAIRE_TABLE_NAME);
			lQuery.append(".");
			lQuery.append(Stagiaire.ID_FIELD_NAME);
			lQuery.append("=");
			lQuery.append(ModeleMetier.STAGIAIRE_AVIS_TABLE_NAME);
			lQuery.append(".");
			lQuery.append(StagiaireAvis.ID_FIELD_NAME);
			lQuery.append(" WHERE 1=1 ");
			String lFullTextSearchWhereClause = ORMLiteHelper.getFullTextSearchWhereClause(new Stagiaire().getFullTextSearchFieldNames() , pPager.getFilterOptions().getFilterText());
			if (null !=lFullTextSearchWhereClause){
				lQuery.append(" AND ");
				lQuery.append(lFullTextSearchWhereClause);
			}
			lQuery.append(" AND ");
			lQuery.append(StagiaireAvis.AVIS_INSTANCE_COURS_FIELD_NAME);
			lQuery.append(" = ");
			lQuery.append(pPager.getId());
			lQuery.append(") AS sub ");
			if (null!=pPager.getPagingOptions()){
				lQuery.append(" WHERE sub.RowNum BETWEEN ");
				lQuery.append(String.valueOf(((pPager.getPagingOptions().getCurrentPage()-1)*pPager.getPagingOptions().getPageSize()) + 1));
				lQuery.append(" AND ");
				lQuery.append(String.valueOf(pPager.getPagingOptions().getCurrentPage()*pPager.getPagingOptions().getPageSize()));
			}
			return new Pair<ArrayList<StagiaireAvis>, Long>(new ArrayList<StagiaireAvis>(this.queryRaw(lQuery.toString(), this.getRawRowMapper()).getResults()), this.countOf());
		} catch (Exception exception) {
			throw new Exception("Echec de chargement de la liste d'enregistrements depuis la base de données");
		}
	}
}
