package net.eni.gestion.pedagogie.resource.implementation;

import java.util.UUID;

import javax.ws.rs.Path;

import net.eni.gestion.pedagogie.modele.Cours;
import net.eni.gestion.pedagogie.resource.CoursResource;
import net.eni.gestion.pedagogie.service.CoursService;

import com.google.inject.Inject;

/**
 * @author jollivier
 * Classe d'implémentation pour le module de gestion des courss
 */
@Path("/courss")
public class CoursResourceImpl extends AResourceImpl<Cours, UUID, CoursService> implements CoursResource {

    /**
     * Constructeur
     * @param coursService
     */
    @Inject
    public CoursResourceImpl(CoursService coursService) {
    	super(coursService, Cours.class);
    }

}
