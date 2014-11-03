package net.eni.gestion.pedagogie.service;

import java.util.HashMap;

import net.eni.gestion.pedagogie.commun.composant.erreur.ApplicationException;
import net.eni.gestion.pedagogie.commun.modele.TitreProfessionnel;

/**
 * @author jollivier
 * Interface métier pour le module de gestion des titres professionnels
 */
public interface TitreProfessionnelService extends AService<TitreProfessionnel, Integer> {

	HashMap<String, String> getTitleMap() throws ApplicationException;

}
