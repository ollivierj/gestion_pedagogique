package net.eni.gestion.pedagogie.resource.implementation;

import java.sql.SQLException;
import java.util.UUID;
import java.util.concurrent.Callable;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import net.eni.gestion.pedagogie.commun.composant.authentification.annotation.CheckSession;
import net.eni.gestion.pedagogie.commun.composant.connexion.Connexion;
import net.eni.gestion.pedagogie.commun.composant.erreur.ApplicationException;
import net.eni.gestion.pedagogie.commun.composant.instancePlanning.InstancePlanning;
import net.eni.gestion.pedagogie.commun.modele.Cours;
import net.eni.gestion.pedagogie.commun.modele.CoursStagiaire;
import net.eni.gestion.pedagogie.commun.modele.InstanceCours;
import net.eni.gestion.pedagogie.resource.CoursResource;
import net.eni.gestion.pedagogie.service.CoursService;

import com.google.inject.Inject;
import net.eni.gestion.pedagogie.commun.composant.connexion.TransactionManager;

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
    public CoursResourceImpl(CoursService coursService, Connexion pConnexion) {
    	super(coursService, Cours.class);
    }
    
    @Override
	@POST
	@Path("/instance")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
    @CheckSession
	public void saveInstance(final InstancePlanning<InstanceCours, CoursStagiaire> instances) throws ApplicationException {
    	try {
			TransactionManager.callInTransaction(
					connection.get(),
					new Callable<Void>() {
						public Void call()
								throws ApplicationException {
							service.saveInstanceData(instances);
							return null;
						}
					});
		} catch (SQLException e) {
			throw new ApplicationException(
					"Erreur lors de la lecture en base de données");
		}
	}
    
    @Override
    @GET
    @Path("/data/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @CheckSession
	public Cours getData(@PathParam("id") final String pId) throws ApplicationException {
    	try {
			return TransactionManager.callInTransaction(
					connection.get(),
					new Callable<Cours>() {
						public Cours call()
								throws ApplicationException {
							return service.getData(pId);
						}
					});
		} catch (SQLException e) {
			throw new ApplicationException(
					"Erreur lors de la lecture en base de données");
		}
	}

}
