package net.eni.gestion.pedagogie.service;

import java.util.HashMap;

import net.eni.gestion.pedagogie.commun.composant.erreur.ApplicationException;
import net.eni.gestion.pedagogie.commun.modele.Fonction;

/**
 * @author jollivier
 * Interface métier pour le module de gestion des titres professionnels
 */
public interface FonctionService extends AService<Fonction, String> {

	HashMap<String, String> getTitleMap() throws ApplicationException;

}
