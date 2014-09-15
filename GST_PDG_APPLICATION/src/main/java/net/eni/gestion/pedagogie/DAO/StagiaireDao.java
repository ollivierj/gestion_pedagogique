package net.eni.gestion.pedagogie.DAO;

import java.util.ArrayList;

import net.eni.gestion.pedagogie.DAO.implementation.AbsenceDaoImpl;
import net.eni.gestion.pedagogie.DAO.implementation.AvisDaoImpl;
import net.eni.gestion.pedagogie.DAO.implementation.EchangeDaoImpl;
import net.eni.gestion.pedagogie.commun.composant.Pager;
import net.eni.gestion.pedagogie.commun.composant.Pair;
import net.eni.gestion.pedagogie.modele.Absence;
import net.eni.gestion.pedagogie.modele.Avis;
import net.eni.gestion.pedagogie.modele.Echange;
import net.eni.gestion.pedagogie.modele.Stagiaire;

/**
 * @author jollivier
 * Description des requêtes d'accès (écriture, lecture) en base de données 
 * pour la gestion des stagiaires
 */
public interface StagiaireDao extends ADao<Stagiaire, Integer> {

	public Pair<ArrayList<Absence>, Long> chargerAbsences(AbsenceDaoImpl dao, Pager pPager) throws Exception;
	public Pair<ArrayList<Echange>, Long> chargerEchanges(EchangeDaoImpl dao, Pager pPager) throws Exception;
	public Pair<ArrayList<Avis>, Long> chargerAvis(AvisDaoImpl dao, Pager pPager) throws Exception;

}
