package net.eni.gestion.pedagogie.commun.outil;

import java.util.ArrayList;

import net.eni.gestion.pedagogie.modele.generique.AModele;

import org.apache.commons.lang3.StringUtils;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.field.FieldType;

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
	public static <M extends AModele<ID>, ID> ArrayList<M> charger(BaseDaoImpl<M, ID> pABase, int page, int pageSize, String orderColumn, String orderDirection, String searchText) throws Exception {
		try {
			ArrayList<String> lFields = new ArrayList<String>();
			for (FieldType lField : pABase.getTableInfo().getFieldTypes()) {
				if (!lField.isForeignCollection()){
					lFields.add(lField.getColumnName());
				}	
			} 
			String lQuery = "SELECT "+StringUtils.join(lFields, ",")+
							" FROM ( "+
							" SELECT *, "+
							" ROW_NUMBER() OVER (ORDER BY "+orderColumn+" "+orderDirection+") AS RowNum "+
							" FROM Stagiaire ) AS sub "+
							" WHERE sub.RowNum BETWEEN "+String.valueOf((page-1)*pageSize)+
							" AND "+String.valueOf(page*pageSize-1);
			return new ArrayList<M>(pABase.queryRaw(lQuery, pABase.getRawRowMapper()).getResults());
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
