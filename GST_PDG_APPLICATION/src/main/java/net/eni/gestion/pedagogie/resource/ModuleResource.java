package net.eni.gestion.pedagogie.resource;

import java.util.HashMap;

import net.eni.gestion.pedagogie.commun.composant.GenericException;
import net.eni.gestion.pedagogie.modele.Module;

/**
 * @author jollivier
 * Interface service pour le module de gestion des titres professionnels
 */
public interface ModuleResource extends AResource<Module, Integer> {

	HashMap<String, String> getTitleMap() throws GenericException;

}
