package net.eni.gestion.pedagogie.service;

import java.util.ArrayList;
import java.util.HashMap;

import net.eni.gestion.pedagogie.commun.composant.Pager;
import net.eni.gestion.pedagogie.commun.composant.Pair;
import net.eni.gestion.pedagogie.errorhandling.ApplicationException;
import net.eni.gestion.pedagogie.modele.generique.AModele;


/**
 * @author jollivier
 * Interface métier de base pour les opération de Création, Récuération, Mise à jour, Suppression
 * Create, Retrieve, Update, Delete
 * @param <M>
 */
public interface AService <M extends AModele<ID>, ID> {

	/**
	 * Charge une liste de modèles à l'aide d'une modèle type passé en paramètre
	 * @param pModel
	 * @return
	 * @throws ApplicationException
	 */
	public Pair<ArrayList<M>, Long> charger(Pager pPager) throws ApplicationException;
	
	/**
	 * Chargement d'une liste de modèle pour les contrôles autocomplete
	 * @param pSearchText
	 * @return
	 * @throws ApplicationException
	 */
	public ArrayList<M> chargerForAutocompleteSearch(String pSearchText) throws ApplicationException;
	
	
	/**
	 * @param pModel
	 * @return
	 * @throws ApplicationException
	 */
	public M chargerDetail(ID pId) throws ApplicationException;

	/**
	 * Ajoute un modèle à l'aide d'une modèle passé en paramètre
	 * @param Modèle à ajouter
	 * @return Modèle ajouté
	 * @throws ApplicationException
	 */
	public M ajouter(M pModel)  throws ApplicationException;

	/**
	 * Met à jour un modèle à l'aide d'un modèle passé en paramètre
	 * @param Modèle à modifier
	 * @return Modèle modifié
	 * @throws ApplicationException
	 */
	public M mettreAJour(M pModel)  throws ApplicationException;

	/**
	 * Supprimer un modèle à l'aide d'un modèle passé en paramètre
	 * @param pModel
	 * @return
	 * @throws ApplicationException
	 */
	public ID supprimer(ID pId)  throws ApplicationException;
	
	/**
	 * Ajoute ou supprime un modèle à l'aide d'un modèle passé en paramètre
	 * @param pModel
	 * @return
	 * @throws ApplicationException
	 */
	public M addOrUpdate(M pModel) throws ApplicationException;

	public HashMap<String, String> getTitleMap() throws ApplicationException;

}
