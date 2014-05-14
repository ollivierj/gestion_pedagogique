package net.eni.gestion.pedagogie.commun.outil;

import java.util.ArrayList;

import net.eni.gestion.pedagogie.modele.generique.AModele;

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
	public static <M extends AModele<ID>, ID> ArrayList<M> charger(BaseDaoImpl<M, ID> pABase,M pModel) throws Exception {
		try {
			return new ArrayList<M>(pABase.queryForAll());
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
	public static <M extends AModele<ID>, ID> M chargerDetail(BaseDaoImpl<M, ID> pABase, M pModel) throws Exception {
		try {
			return pABase.queryForSameId(pModel);
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
	public static <M extends AModele<ID>, ID> M supprimer(BaseDaoImpl<M, ID> pABase, M pModel) throws Exception {
		try {
			if (1 == pABase.delete(pModel)) {
				return pModel;
			} else {
				throw new Exception("Echec de la suppression de l'enregistrement en base de données");
			}
		} catch (Exception exception) {
			throw new Exception("Echec de la suppression de l'enregistrement en base de données");
		}
	}
	
}
