package net.eni.gestion.pedagogie.service;

import java.util.HashMap;

import net.eni.gestion.pedagogie.commun.composant.erreur.ApplicationException;
import net.eni.gestion.pedagogie.commun.modele.TypeSession;

/**
 * @author jollivier
 * Interface métier pour le module de gestion des titres professionnels
 */
public interface TypeSessionService extends AService<TypeSession, Integer> {

	HashMap<String, String> getTitleMap() throws ApplicationException;

}
