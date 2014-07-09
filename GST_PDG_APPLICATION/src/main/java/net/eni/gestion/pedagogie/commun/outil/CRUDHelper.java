package net.eni.gestion.pedagogie.commun.outil;

import java.util.ArrayList;

import net.eni.gestion.pedagogie.commun.composant.Pager;
import net.eni.gestion.pedagogie.commun.composant.Pair;
import net.eni.gestion.pedagogie.modele.generique.AModele;

import org.apache.commons.lang3.StringUtils;

import com.j256.ormlite.dao.BaseDaoImpl;

/**
 * @author jollivier
 * @param AModele
 * @param DAO
 */
public class CRUDHelper {
	
	/**
	 * Template pour le chargement totale de modèles
	 * @param pABase
	 * @param pModel
	 * @return Tableau de modèles
	 * @throws Exception 
	 */
	public static <M extends AModele<ID>, ID> Pair<ArrayList<M>, Long> charger(BaseDaoImpl<M, ID> pABase, Pager pPager) throws Exception {
		try {
			StringBuilder lQuery = new StringBuilder();
			lQuery.append("SELECT ");
			lQuery.append(StringUtils.join(ORMLiteHelper.getProjectionFields(pABase.getTableInfo()), ","));
			lQuery.append(" FROM ( ");
			lQuery.append(" SELECT *, ");
			lQuery.append(" ROW_NUMBER() OVER (ORDER BY ");
			lQuery.append(ORMLiteHelper.getOrderByClauseFromSortOptions(pABase.getTableInfo(), pPager.getSortOptions()));
			lQuery.append(") AS RowNum ");
			lQuery.append(" FROM ");
			lQuery.append(pABase.getTableInfo().getTableName());
			String lFullTextSearchWhereClause = ORMLiteHelper.getFullTextSearchWhereClause(pABase.getDataClass().newInstance().getFullTextSearchFieldNames() , pPager.getFilterOptions().getFilterText());
			if (null !=lFullTextSearchWhereClause){
				lQuery.append(" WHERE ");
				lQuery.append(lFullTextSearchWhereClause);
			}
			lQuery.append(") AS sub ");
			lQuery.append(" WHERE sub.RowNum BETWEEN ");
			lQuery.append(String.valueOf((pPager.getPagingOptions().getCurrentPage()-1)*pPager.getPagingOptions().getPageSize()));
			lQuery.append(" AND ");
			lQuery.append(String.valueOf(pPager.getPagingOptions().getCurrentPage()*pPager.getPagingOptions().getPageSize()-1));
			return new Pair<ArrayList<M>, Long>(new ArrayList<M>(pABase.queryRaw(lQuery.toString(), pABase.getRawRowMapper()).getResults()), pABase.countOf());
		} catch (Exception exception) {
			throw new Exception("Echec de chargement de la liste d'enregistrements depuis la base de données");
		}
	}
		
	/**
	 * Template pour le chargement totale de modèles
	 * @param pABase
	 * @param pModel
	 * @return Tableau de modèles
	 * @throws Exception 
	 */
	public static <M extends AModele<ID>, ID> M chargerDetail(BaseDaoImpl<M, ID> pABase, ID pId) throws Exception {
		try {
			return pABase.queryForId(pId);
		} catch (Exception exception) {
			throw new Exception("Echec de chargement de la liste d'enregistrements depuis la base de données");
		}
	}


	/**
	 * Template pour l'ajout d'un modèle en base
	 * @param pABase
	 * @param pModel
	 * @return Modèle insérée en base avec son nouvel ID
	 * @throws Exception 
	 */
	public static <M extends AModele<ID>, ID> M ajouter(BaseDaoImpl<M, ID> pABase, M pModel) throws Exception {
		try {
			if (1 == pABase.create(pModel)) {
				return pModel;
			} else {
				throw new Exception("Echec de l'ajout de l'enregistrement en base de données");
			}
			
		} catch (Exception exception) {
			throw new Exception("Echec de l'ajout de l'enregistrement en base de données");
		}
	}
	
	/**
	 * Template pour la mise à jour d'un modèle en base
	 * @param pABase
	 * @param pModel
	 * @return Modèle mise à jour 
	 * @throws Exception 
	 */
	public static <M extends AModele<ID>, ID> M mettreAJour(BaseDaoImpl<M, ID> pABase, M pModel) throws Exception {
		try {
			if (1 == pABase.update(pModel)) {
				return pModel;
			} else {
				throw new Exception("Echec de la modification de l'enregistrement en base de données");
			}
		} catch (Exception exception) {
			throw new Exception("Echec la modification de l'enregistrement en base de données");
		}
	}

	/**
	 * Template pour la suppression d'un modèle en base
	 * @param pABase
	 * @param pModel
	 * @return Modèle mise à jour 
	 * @throws Exception 
	 */
	public static <M extends AModele<ID>, ID> ID supprimer(BaseDaoImpl<M, ID> pABase, ID pId) throws Exception {
		try {
			if (1 == pABase.deleteById(pId)) {
				return pId;
			} else {
				throw new Exception("Echec de la suppression de l'enregistrement en base de données");
			}
		} catch (Exception exception) {
			throw new Exception("Echec de la suppression de l'enregistrement en base de données");
		}
	}
	
}
