/**
 * 
 */
package net.eni.gestion.pedagogie.DAO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.eni.gestion.pedagogie.commun.composant.erreur.ApplicationException;
import net.eni.gestion.pedagogie.commun.composant.pagination.Pager;
import net.eni.gestion.pedagogie.commun.composant.tuple.Pair;
import net.eni.gestion.pedagogie.commun.modele.generique.AModele;

/**
 * @author jollivier
 *Interface pour les actions de bases CRUD (Create, Retrieve, Update, Delete)
 */
public interface ADao<M extends AModele<ID>, ID> {
	
		/**
		 * Méthode de chargement totale + règles de gestion spécifiques
		 * 
		 * @param pModel Modèle servant de critère de recherche
		 * @return ArrayList<GenericModel> Tableau contenant les résultats de la DAO requêtant l'ensemble des modèles
		 */
		public Pair<ArrayList<M>, Long> charger(Pager pPager) throws ApplicationException;
		
		/**
		 * Méthode de chargement pour les contrôles autocomplete 
		 * @param pSearchText
		 * @param pDate 
		 * @return
		 * @throws ApplicationException
		 */
		public ArrayList<M> chargerForAutocompleteSearch(String pSearchText) throws ApplicationException;
		
		/**
		 * Méthode de chargement du détail d'un modèle + règles de gestion spécifiques
		 * @param pModel
		 * @return
		 * @throws ApplicationException
		 */
		public M chargerDetail(ID pId) throws ApplicationException;
		
		
		/**
		 * Méthode d'ajout d'un modèle + règles de gestion spécifiques
		 * 
		 * @param pModel
		 * @return GenericModel Modèle mis à jour avec son ID suite à l'appel de la DAO ajoutant le modèle en base
		 * @throws ApplicationException 
		 */
		public M ajouter(M pModel) throws ApplicationException;

		/**
		 * Méthode de mise à jour d'un modèle + règles de gestion spécifiques
		 * 
		 * @param pModel
		 * @return GenericModel Modèle mis à jour avec son ID suite à l'appel de la DAO mettant à jour le modèle en base
		 */
		public M mettreAJour(M pModel)throws ApplicationException;

		/**
		 * Méthode de supression d'un modèle + règles de gestion spécifiques
		 * 
		 * @param pModel
		 * @return GenericModel Modèle supprimé suite à l'appel de la DAO supprimant le modèle en base
		 */
		public ID supprimer(ID pId)throws ApplicationException;

		public HashMap<String, String> getTitleMap() throws ApplicationException;
		
		/**
		 * Récupère tous les modèles
		 * @return modèles
		 * @throws ApplicationException
		 */
		public List<M> chargerTous() throws ApplicationException;
		
		/**
		 * Ajoute ou supprime un modèle à l'aide d'un modèle passé en paramètre
		 * @param pModel
		 * @return
		 * @throws ApplicationException
		 */
		public M addOrUpdate(M pModel) throws ApplicationException;
}
