package net.eni.gestion.pedagogie.resource.implementation;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Callable;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import net.eni.gestion.pedagogie.commun.composant.connexion.Connexion;
import net.eni.gestion.pedagogie.commun.composant.erreur.ApplicationException;
import net.eni.gestion.pedagogie.commun.modele.Planning;
import net.eni.gestion.pedagogie.resource.PlanningResource;
import net.eni.gestion.pedagogie.service.PlanningService;

import com.google.inject.Inject;
import net.eni.gestion.pedagogie.commun.composant.connexion.TransactionManager;

@Path("/planning")
public class PlanningResourceImpl extends AResourceImpl<Planning, Long, PlanningService> implements PlanningResource {
	
	@Inject
	public PlanningResourceImpl(PlanningService planningService, Connexion pConnexion) {
		super(planningService, Planning.class);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Planning> getElements(@QueryParam("debut") final String dateDebut, @QueryParam("fin") final String dateFin) throws ApplicationException {		
		try {
			return TransactionManager.callInTransaction(
					connection.get(),
					new Callable<List<Planning>>() {
						public List<Planning> call()
								throws ApplicationException {
							List<Planning> planningElements = service.getPlanningElement(dateDebut, dateFin);
							return planningElements;
						}
					});
		} catch (SQLException e) {
			throw new ApplicationException(
					"Erreur lors de la lecture en base de données");
		}
	}

}
