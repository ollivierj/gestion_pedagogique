package net.eni.gestion.pedagogie.DAO;

import java.util.ArrayList;

import net.eni.gestion.pedagogie.commun.composant.erreur.ApplicationException;
import net.eni.gestion.pedagogie.commun.modele.Evaluation;
import net.eni.gestion.pedagogie.commun.modele.EvaluationStagiaire;

/**
 * @author jollivier
 * Description des requêtes d'accès (écriture, lecture) en base de données 
 * pour la gestion des instances d'évaluation (ECF, Epreuves finales) et des stagiaires associés
 */
public interface EvaluationStagiaireDao extends ADao<EvaluationStagiaire, Integer> {

	public ArrayList<EvaluationStagiaire> mettreAJourCollectionStagiaireForEvaluation(
			Evaluation pEvaluation,
			ArrayList<EvaluationStagiaire> pEvaluationStagiaires) throws ApplicationException;

}
