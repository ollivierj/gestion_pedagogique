package net.eni.gestion.pedagogie.ressource.implementation;

import javax.ws.rs.Path;

import net.eni.gestion.pedagogie.modele.ProfessionnelHomologue;
import net.eni.gestion.pedagogie.ressource.ProfessionnelHomologueRessource;
import net.eni.gestion.pedagogie.service.ProfessionnelHomologueService;

import com.google.inject.Inject;

/**
 * @author jollivier
 * Classe d'implémentation pour le module de gestion des professionnelHomologues
 */
@Path("/professionnelHomologues")
public class ProfessionnelHomologueRessourceImpl extends ARessourceImpl<ProfessionnelHomologue, Integer, ProfessionnelHomologueService> implements ProfessionnelHomologueRessource {

    /**
     * Constructeur
     * @param professionnelHomologueService
     */
    @Inject
    public ProfessionnelHomologueRessourceImpl(ProfessionnelHomologueService professionnelHomologueService) {
    	super(professionnelHomologueService);
    }

}
