package net.eni.gestion.pedagogie.service.generique;

import java.util.ArrayList;

import net.eni.gestion.pedagogie.commun.composant.GenericException;
import net.eni.gestion.pedagogie.modele.Stagiaire;
import net.eni.gestion.pedagogie.modele.generique.AModele;

/**
 * @author jollivier
 * Interface métier de base pour les opération de Création, Récuération, Mise à jour, Suppression
 * Create, Retrieve, Update, Delete
 * @param <M>
 */
public interface CRUDService<M extends AModele<?>> {

	/**
	 * Charge une liste de modèles à l'aide d'une modèle type passé en paramètre
	 * @param pModel
	 * @return
	 * @throws GenericException
	 */
	public ArrayList<M> charger(M pModel) throws GenericException;
	
	/**
	 * @param pModel
	 * @return
	 * @throws GenericException
	 */
	public Stagiaire chargerDetail(M pModel) throws GenericException;

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
	 * @param pModel
	 * @return
	 * @throws GenericException
	 */
	public M supprimer(M pModel)  throws GenericException;
}
