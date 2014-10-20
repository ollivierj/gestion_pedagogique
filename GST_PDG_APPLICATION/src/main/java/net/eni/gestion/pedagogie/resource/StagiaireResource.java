package net.eni.gestion.pedagogie.resource;

import java.util.ArrayList;

import net.eni.gestion.pedagogie.commun.composant.GenericException;
import net.eni.gestion.pedagogie.commun.composant.NamedObjectMap;
import net.eni.gestion.pedagogie.modele.Stagiaire;

/**
 * @author jollivier
 * Interface service pour le module de consultation des stagiaires
 */
public interface StagiaireResource extends AResource<Stagiaire, Integer> {
	public ArrayList<NamedObjectMap> chargerStagiaireOrPromotionAutocomplete(
			String pSearchText) throws GenericException;
	
	public ArrayList<NamedObjectMap> chargerStagiaireAutocomplete(
			String pSearchText) throws GenericException;
}
