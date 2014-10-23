package net.eni.gestion.pedagogie.resource;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.HashMap;

import javax.ws.rs.NameBinding;

import net.eni.gestion.pedagogie.commun.composant.GenericException;
import net.eni.gestion.pedagogie.commun.composant.NamedObjectMap;
import net.eni.gestion.pedagogie.commun.composant.Pager;
import net.eni.gestion.pedagogie.modele.generique.AModele;

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

	@NameBinding
	@Retention(value = RetentionPolicy.RUNTIME)
	@Target({ ElementType.METHOD })
	public @interface AuthenticationNotRequired {
	}

	/**
	 * @return
	 * @throws GenericException
	 */
	public String getJsonSchema() throws GenericException;

	/**
	 * @param pSearchText
	 * @return
	 * @throws GenericException
	 */
	public ArrayList<M> chargerForAutocompleteSearch(String pSearchText)
			throws GenericException;

	/**
	 * Charge une liste de modèles
	 * 
	 * @return Liste de modèles
	 * @throws GenericException
	 */
	public NamedObjectMap charger(Pager pPager) throws GenericException;

	/**
	 * Charge un modèle
	 * 
	 * @return Liste de modèles
	 * @throws GenericException
	 */
	public M chargerDetail(ID pId) throws GenericException;

	/**
	 * Ajoute un modèle à l'aide d'une modèle passé en paramètre
	 * 
	 * @param Modèle
	 *            à ajouter
	 * @return Modèle ajouté
	 * @throws GenericException
	 */
	public M ajouter(M pModel) throws GenericException;

	/**
	 * Met à jour un modèle à l'aide d'un modèle passé en paramètre
	 * 
	 * @param Modèle
	 *            à modifier
	 * @return Modèle modifié
	 * @throws GenericException
	 */
	public M mettreAJour(M pModel) throws GenericException;

	/**
	 * Supprimer un modèle à l'aide d'un modèle passé en paramètre
	 * 
	 * @param Modèle
	 *            à supprimer
	 * @return Modèle supprimé
	 * @throws GenericException
	 */
	public ID supprimer(ID pId) throws GenericException;

	/**
	 * Ajoute ou met à jour un modèle à l'aide d'un moèle passé en paramètre
	 * 
	 * @param pModel
	 *            Modèle à ajouter ou à mettre à jour
	 * @return Modèle ajouté ou mis à jour
	 * @throws GenericException
	 */
	public M addOrUpdate(M pModel) throws GenericException;

	public HashMap<String, String> getTitleMap() throws GenericException;

}
