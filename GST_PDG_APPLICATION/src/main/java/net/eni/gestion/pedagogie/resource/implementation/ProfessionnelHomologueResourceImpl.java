package net.eni.gestion.pedagogie.resource.implementation;

import javax.ws.rs.Path;

import com.google.inject.Inject;

import net.eni.gestion.pedagogie.commun.modele.ProfessionnelHomologue;
import net.eni.gestion.pedagogie.resource.ProfessionnelHomologueResource;
import net.eni.gestion.pedagogie.service.ProfessionnelHomologueService;

/**
 * @author jollivier
 * Classe d'implémentation pour le module de gestion des professionnelHomologues
 */
@Path("/professionnelHomologues")
public class ProfessionnelHomologueResourceImpl extends AResourceImpl<ProfessionnelHomologue, Integer, ProfessionnelHomologueService> implements ProfessionnelHomologueResource {

    /**
     * Constructeur
     * @param professionnelHomologueService
     */
	@Inject
    public ProfessionnelHomologueResourceImpl(ProfessionnelHomologueService professionnelHomologueService) {
    	super(professionnelHomologueService, ProfessionnelHomologue.class);
    }
    
    


}
