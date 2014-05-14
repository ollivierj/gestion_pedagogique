/**
 * 
 */
package net.eni.gestion.pedagogie.DAO.base.generique;

import java.util.ArrayList;

import net.eni.gestion.pedagogie.modele.Stagiaire;
import net.eni.gestion.pedagogie.modele.generique.AModele;

/**
 * @author jollivier
 *Interface pour les actions de bases CRUD (Create, Retrieve, Update, Delete)
 */
public interface CRUDBase<M extends AModele<?>> {
	
		
		/**
		 * Méthode de chargement totale + règles de gestion spécifiques
		 * 
		 * @param pModel Modèle servant de critère de recherche
		 * @return ArrayList<GenericModel> Tableau contenant les résultats de la DAO requêtant l'ensemble des modèles
		 */
		public ArrayList<M> charger(M pModel) throws Exception;
		
		/**
		 * Méthode de chargement du détail d'un modèle + règles de gestion spécifiques
		 * @param pModel
		 * @return
		 * @throws Exception
		 */
		public Stagiaire chargerDetail(Stagiaire pModel) throws Exception;
		
		
		/**
		 * Méthode d'ajout d'un modèle + règles de gestion spécifiques
		 * 
		 * @param pModel
		 * @return GenericModel Modèle mis à jour avec son ID suite à l'appel de la DAO ajoutant le modèle en base
		 * @throws Exception 
		 */
		public M ajouter(M pModel) throws Exception;

		/**
		 * Méthode de mise à jour d'un modèle + règles de gestion spécifiques
		 * 
		 * @param pModel
		 * @return GenericModel Modèle mis à jour avec son ID suite à l'appel de la DAO mettant à jour le modèle en base
		 */
		public M mettreAJour(M pModel)throws Exception;

		/**
		 * Méthode de supression d'un modèle + règles de gestion spécifiques
		 * 
		 * @param pModel
		 * @return GenericModel Modèle supprimé suite à l'appel de la DAO supprimant le modèle en base
		 */
		public M supprimer(M pModel)throws Exception;

}
