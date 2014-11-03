package net.eni.gestion.pedagogie.resource.implementation;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import net.eni.gestion.pedagogie.commun.composant.erreur.ApplicationException;
import net.eni.gestion.pedagogie.modele.Planning;
import net.eni.gestion.pedagogie.resource.PlanningResource;
import net.eni.gestion.pedagogie.service.PlanningService;

import com.google.inject.Inject;

@Path("/planning")
public class PlanningResourceImpl extends AResourceImpl<Planning, Long, PlanningService> implements PlanningResource {
	
	@Inject
	public PlanningResourceImpl(PlanningService planningService) {
		super(planningService, Planning.class);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Planning> getElements(@QueryParam("debut")String dateDebut, @QueryParam("fin")String dateFin) throws ApplicationException {		
		List<Planning> planningElements = service.getPlanningElement(dateDebut, dateFin);
		return planningElements;
	}

}
