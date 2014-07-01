package net.eni.gestion.pedagogie.ressource.implementation;

import javax.ws.rs.Path;

import net.eni.gestion.pedagogie.modele.Stagiaire;
import net.eni.gestion.pedagogie.ressource.StagiaireRessource;
import net.eni.gestion.pedagogie.service.StagiaireService;

import com.google.inject.Inject;

/**
 * @author jollivier
 * Classe d'implémentation pour le module de gestion des stagiaires
 */
@Path("/stagiaires")
public class StagiaireRessourceImpl extends ARessourceImpl<Stagiaire, Integer, StagiaireService> implements StagiaireRessource {

    /**
     * Constructeur
     * @param stagiaireService
     */
    @Inject
    public StagiaireRessourceImpl(StagiaireService stagiaireService) {
    	super(stagiaireService);
    }

}
