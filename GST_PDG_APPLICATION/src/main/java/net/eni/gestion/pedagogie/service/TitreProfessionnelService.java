package net.eni.gestion.pedagogie.service;

import java.util.HashMap;

import net.eni.gestion.pedagogie.commun.composant.GenericException;
import net.eni.gestion.pedagogie.modele.TitreProfessionnel;

/**
 * @author jollivier
 * Interface métier pour le module de gestion des titres professionnels
 */
public interface TitreProfessionnelService extends AService<TitreProfessionnel, Integer> {

	HashMap<String, String> getTitleMap() throws GenericException;

}
