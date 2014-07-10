package net.eni.gestion.pedagogie.resource.implementation;

import javax.ws.rs.Path;

import net.eni.gestion.pedagogie.modele.Stagiaire;
import net.eni.gestion.pedagogie.resource.StagiaireResource;
import net.eni.gestion.pedagogie.service.StagiaireService;

import com.google.inject.Inject;

/**
 * @author jollivier
 * Classe d'implémentation pour le module de gestion des stagiaires
 */
@Path("/stagiaires")
public class StagiaireResourceImpl extends AResourceImpl<Stagiaire, Integer, StagiaireService> implements StagiaireResource {

    /**
     * Constructeur
     * @param stagiaireService
     */
    @Inject
    public StagiaireResourceImpl(StagiaireService stagiaireService) {
    	super(stagiaireService, Stagiaire.class);
    }

}