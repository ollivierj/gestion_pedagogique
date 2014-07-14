package net.eni.gestion.pedagogie.resource;

import java.util.HashMap;

import net.eni.gestion.pedagogie.commun.composant.GenericException;
import net.eni.gestion.pedagogie.modele.TitreProfessionnel;

/**
 * @author jollivier
 * Interface service pour le module de gestion des titres professionnels
 */
public interface TitreProfessionnelResource extends AResource<TitreProfessionnel, Integer> {

	HashMap<Integer, String> getTitleMap() throws GenericException;

}
