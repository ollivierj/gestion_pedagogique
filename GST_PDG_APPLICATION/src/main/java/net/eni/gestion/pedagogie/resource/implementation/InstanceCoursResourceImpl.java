package net.eni.gestion.pedagogie.resource.implementation;

import javax.ws.rs.Path;

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

}
