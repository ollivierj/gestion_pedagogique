package net.eni.gestion.pedagogie.resource;

import java.util.ArrayList;

import net.eni.gestion.pedagogie.commun.composant.erreur.ApplicationException;
import net.eni.gestion.pedagogie.commun.composant.map.NamedObjectMap;
import net.eni.gestion.pedagogie.commun.modele.Stagiaire;

/**
 * @author jollivier
 * Interface service pour le module de consultation des stagiaires
 */
public interface StagiaireResource extends AResource<Stagiaire, Integer> {
	public ArrayList<NamedObjectMap> chargerStagiaireOrPromotionAutocomplete(
			String pSearchText) throws ApplicationException;
	
	public ArrayList<NamedObjectMap> chargerStagiaireAutocomplete(String pSearchText) throws ApplicationException;
}
