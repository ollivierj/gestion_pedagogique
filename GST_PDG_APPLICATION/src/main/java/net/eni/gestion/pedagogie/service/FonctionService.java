package net.eni.gestion.pedagogie.service;

import java.util.HashMap;

import net.eni.gestion.pedagogie.commun.composant.GenericException;
import net.eni.gestion.pedagogie.modele.Fonction;

/**
 * @author jollivier
 * Interface métier pour le module de gestion des titres professionnels
 */
public interface FonctionService extends AService<Fonction, String> {

	HashMap<String, String> getTitleMap() throws GenericException;

}
