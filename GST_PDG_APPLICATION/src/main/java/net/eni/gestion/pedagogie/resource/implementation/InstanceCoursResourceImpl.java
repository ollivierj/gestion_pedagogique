package net.eni.gestion.pedagogie.resource.implementation;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import net.eni.gestion.pedagogie.commun.composant.authentification.annotation.CheckSession;
import net.eni.gestion.pedagogie.commun.composant.erreur.ApplicationException;
import net.eni.gestion.pedagogie.commun.modele.Cours;
import net.eni.gestion.pedagogie.commun.modele.InstanceCours;
import net.eni.gestion.pedagogie.resource.InstanceCoursResource;
import net.eni.gestion.pedagogie.service.InstanceCoursService;

import com.google.inject.Inject;

/**
 * @author jollivier
 * Classe d'implémentation pour le module de gestion des evaluations
 */
@Path("/instanceCours")
public class InstanceCoursResourceImpl extends AResourceImpl<InstanceCours, Integer, InstanceCoursService> implements InstanceCoursResource {

    /**
     * Constructeur
     * @param evaluationService
     */
    @Inject
    public InstanceCoursResourceImpl(InstanceCoursService pInstanceCoursService) {
    	super(pInstanceCoursService, InstanceCours.class);
    }
    
    @Override
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
    @CheckSession
	public List<InstanceCours> getInstances(Cours cours) throws ApplicationException {
		return service.getInstancesByCours(cours);
	}

}
