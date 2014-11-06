package net.eni.gestion.pedagogie.resource;

import java.util.ArrayList;
import java.util.HashMap;

import net.eni.gestion.pedagogie.commun.composant.erreur.ApplicationException;
import net.eni.gestion.pedagogie.commun.composant.map.NamedObjectMap;
import net.eni.gestion.pedagogie.commun.composant.pagination.Pager;
import net.eni.gestion.pedagogie.commun.modele.generique.AModele;

/**
 * @author jollivier
 *
 * @param <M>
 */

/**
 * @author jollivier Interface service (web) de base pour les opération de
 *         Création, Récuération, Mise à jour, Suppression Create, Retrieve,
 *         Update, Delete
 * @param <M>
 */
public interface AResource<M extends AModele<ID>, ID> {
	
	/**
	 * @return
	 * @throws ApplicationException
	 */
	public String getJsonSchema() throws ApplicationException;

	/**
	 * @param pSearchText
	 * @return
	 * @throws ApplicationException
	 */
	public ArrayList<M> chargerForAutocompleteSearch(String pSearchText)
			throws ApplicationException;

	/**
	 * Charge une liste de modèles
	 * 
	 * @return Liste de modèles
	 * @throws ApplicationException
	 */
	public NamedObjectMap charger(Pager pPager) throws ApplicationException;

	/**
	 * Charge un modèle
	 * 
	 * @return Liste de modèles
	 * @throws ApplicationException
	 */
	public M chargerDetail(ID pId) throws ApplicationException;

	/**
	 * Ajoute un modèle à l'aide d'une modèle passé en paramètre
	 * 
	 * @param Modèle
	 *            à ajouter
	 * @return Modèle ajouté
	 * @throws ApplicationException
	 */
	public M ajouter(M pModel) throws ApplicationException;

	/**
	 * Met à jour un modèle à l'aide d'un modèle passé en paramètre
	 * 
	 * @param Modèle
	 *            à modifier
	 * @return Modèle modifié
	 * @throws ApplicationException
	 */
	public M mettreAJour(M pModel) throws ApplicationException;

	/**
	 * Supprimer un modèle à l'aide d'un modèle passé en paramètre
	 * 
	 * @param Modèle
	 *            à supprimer
	 * @return Modèle supprimé
	 * @throws ApplicationException
	 */
	public ID supprimer(ID pId) throws ApplicationException;

	/**
	 * Ajoute ou met à jour un modèle à l'aide d'un moèle passé en paramètre
	 * 
	 * @param pModel
	 *            Modèle à ajouter ou à mettre à jour
	 * @return Modèle ajouté ou mis à jour
	 * @throws ApplicationException
	 */
	public M addOrUpdate(M pModel) throws ApplicationException;

	public HashMap<String, String> getTitleMap() throws ApplicationException;

}
