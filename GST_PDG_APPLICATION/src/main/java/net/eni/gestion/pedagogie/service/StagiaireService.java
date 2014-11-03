package net.eni.gestion.pedagogie.service;

import java.util.ArrayList;

import net.eni.gestion.pedagogie.errorhandling.ApplicationException;
import net.eni.gestion.pedagogie.modele.Promotion;
import net.eni.gestion.pedagogie.modele.Stagiaire;

/**
 * @author jollivier
 * Interface métier pour le module de consultation des stagiaires
 */
public interface StagiaireService extends AService<Stagiaire, Integer> {
	/**
	 * Chargement d'une liste de modèle pour les contrôles autocomplete
	 * @param pSearchText
	 * @return
	 * @throws ApplicationException
	 */
	public ArrayList<Promotion> chargerPromotionForAutocompleteSearch(String pSearchText) throws ApplicationException;
}
