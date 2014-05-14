package net.eni.gestion.pedagogie.ressource.generique;

import java.util.List;

import net.eni.gestion.pedagogie.commun.composant.GenericException;
import net.eni.gestion.pedagogie.modele.generique.AModele;

/**
 * @author jollivier
 *
 * @param <M>
 */

/**
 * @author jollivier
 * Interface service (web) de base pour les opération de Création, Récuération, Mise à jour, Suppression
 * Create, Retrieve, Update, Delete
 * @param <M>
 */
public interface CRUDRessource<M extends AModele<?>> {

	/**
	 * Charge une liste de modèles
	 * @return Liste de modèles
	 * @throws GenericException
	 */
	public List<M> charger() throws GenericException;
	
	 /**
	 * Charge un modèle
	 * @return Liste de modèles
	 * @throws GenericException
	 */
	public M chargerDetail(int pId) throws GenericException;

	/**
	 * Ajoute un modèle à l'aide d'une modèle passé en paramètre
	 * @param Modèle à ajouter
	 * @return Modèle ajouté
	 * @throws GenericException
	 */
	public M ajouter(M pModel)  throws GenericException;

	/**
	 * Met à jour un modèle à l'aide d'un modèle passé en paramètre
	 * @param Modèle à modifier
	 * @return Modèle modifié
	 * @throws GenericException
	 */
	public M mettreAJour(M pModel)  throws GenericException;

	/**
	 * Supprimer un modèle à l'aide d'un modèle passé en paramètre
	 * @param Modèle à supprimer
	 * @return Modèle supprimé
	 * @throws GenericException
	 */
	public M supprimer(int pId)  throws GenericException;
}
