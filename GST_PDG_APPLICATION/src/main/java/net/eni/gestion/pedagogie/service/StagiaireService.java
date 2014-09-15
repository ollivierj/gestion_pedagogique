package net.eni.gestion.pedagogie.service;

import java.util.ArrayList;

import net.eni.gestion.pedagogie.commun.composant.GenericException;
import net.eni.gestion.pedagogie.commun.composant.Pager;
import net.eni.gestion.pedagogie.commun.composant.Pair;
import net.eni.gestion.pedagogie.modele.Absence;
import net.eni.gestion.pedagogie.modele.Avis;
import net.eni.gestion.pedagogie.modele.Echange;
import net.eni.gestion.pedagogie.modele.Stagiaire;

/**
 * @author jollivier
 * Interface métier pour le module de consultation des stagiaires
 */
public interface StagiaireService extends AService<Stagiaire, Integer> {
	
	public Pair<ArrayList<Absence>, Long> chargerAbsences(Pager pPager) throws GenericException;
	
	public Pair<ArrayList<Echange>, Long> chargerEchanges(Pager pPager) throws GenericException;
	
	public Pair<ArrayList<Avis>, Long> chargerAvis(Pager pPager) throws GenericException;	
}
