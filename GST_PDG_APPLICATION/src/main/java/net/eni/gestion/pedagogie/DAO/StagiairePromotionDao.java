package net.eni.gestion.pedagogie.DAO;

import java.util.ArrayList;

import net.eni.gestion.pedagogie.commun.composant.erreur.ApplicationException;
import net.eni.gestion.pedagogie.commun.modele.StagiairePromotion;

public interface StagiairePromotionDao extends ADao<StagiairePromotion, Integer>{
	ArrayList<StagiairePromotion> chargerByStagiaireId(Integer id) throws ApplicationException;
	
	
	ArrayList<StagiairePromotion> chargerByPromotionId(String id) throws ApplicationException;
	
}
