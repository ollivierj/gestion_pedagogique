package net.eni.gestion.pedagogie.resource.implementation;

import javax.ws.rs.Path;

import net.eni.gestion.pedagogie.commun.composant.connexion.Connexion;
import net.eni.gestion.pedagogie.commun.modele.SujetEvaluation;
import net.eni.gestion.pedagogie.resource.SujetEvaluationResource;
import net.eni.gestion.pedagogie.service.SujetEvaluationService;

import com.google.inject.Inject;

/**
 * @author jollivier
 * Classe d'implémentation pour le module de gestion des absences
 */
@Path("/sujetEvaluations")
public class SujetEvaluationResourceImpl extends AResourceImpl<SujetEvaluation, Integer, SujetEvaluationService> implements SujetEvaluationResource {

    /**
     * Constructeur
     * @param AbsenceService
     */
    @Inject
    public SujetEvaluationResourceImpl(SujetEvaluationService SujetEvaluationService, Connexion pConnexion) {
    	super(SujetEvaluationService, SujetEvaluation.class, pConnexion);
    }

}
