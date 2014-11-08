package net.eni.gestion.pedagogie.resource.implementation;

import java.util.UUID;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import net.eni.gestion.pedagogie.commun.composant.authentification.annotation.CheckSession;
import net.eni.gestion.pedagogie.commun.composant.erreur.ApplicationException;
import net.eni.gestion.pedagogie.commun.composant.instancePlanning.InstancePlanning;
import net.eni.gestion.pedagogie.commun.modele.Cours;
import net.eni.gestion.pedagogie.commun.modele.CoursStagiaire;
import net.eni.gestion.pedagogie.commun.modele.InstanceCours;
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
    
    @Override
	@POST
	@Path("/instance")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
    @CheckSession
	public void saveInstance(InstancePlanning<InstanceCours, CoursStagiaire> instances) throws ApplicationException {
		service.saveInstanceData(instances);
	}
    
    @Override
    @GET
    @Path("/data/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @CheckSession
	public Cours getData(@PathParam("id") String pId) throws ApplicationException {
		return service.getData(pId);

	}

}
