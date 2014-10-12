package net.eni.gestion.pedagogie.service;

import java.util.ArrayList;

import net.eni.gestion.pedagogie.commun.composant.GenericException;
import net.eni.gestion.pedagogie.modele.StagiairePromotion;

public interface StagiairePromotionService extends AService<StagiairePromotion, Integer> {
	ArrayList<StagiairePromotion> chargerByStagiaireId(Integer id) throws GenericException;
   
	ArrayList<StagiairePromotion> chargerByPromotionId(String id) throws GenericException;
   
}
