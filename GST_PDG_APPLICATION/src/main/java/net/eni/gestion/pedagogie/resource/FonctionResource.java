package net.eni.gestion.pedagogie.resource;

import java.util.HashMap;

import net.eni.gestion.pedagogie.commun.composant.GenericException;
import net.eni.gestion.pedagogie.modele.Fonction;

/**
 * @author jollivier
 * Interface service pour le module de gestion des titres professionnels
 */
public interface FonctionResource extends AResource<Fonction, String> {

	HashMap<String, String> getTitleMap() throws GenericException;

}
