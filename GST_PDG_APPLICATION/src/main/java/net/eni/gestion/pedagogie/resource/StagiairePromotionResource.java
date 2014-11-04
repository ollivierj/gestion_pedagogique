package net.eni.gestion.pedagogie.resource;

import java.util.ArrayList;

import net.eni.gestion.pedagogie.commun.composant.erreur.ApplicationException;
import net.eni.gestion.pedagogie.commun.modele.StagiairePromotion;

public interface StagiairePromotionResource extends
		AResource<StagiairePromotion, Integer> {

	public ArrayList<StagiairePromotion> chargerStagiaireOrPromotion(
			String type, String id) throws ApplicationException;
}
