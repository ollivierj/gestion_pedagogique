package net.eni.gestion.pedagogie.resource.implementation;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Callable;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import net.eni.gestion.pedagogie.commun.composant.authentification.annotation.CheckSession;
import net.eni.gestion.pedagogie.commun.composant.connexion.Connexion;
import net.eni.gestion.pedagogie.commun.composant.erreur.ApplicationException;
import net.eni.gestion.pedagogie.commun.modele.Cours;
import net.eni.gestion.pedagogie.commun.modele.InstanceCours;
import net.eni.gestion.pedagogie.resource.InstanceCoursResource;
import net.eni.gestion.pedagogie.service.InstanceCoursService;

import com.google.inject.Inject;
import net.eni.gestion.pedagogie.commun.composant.connexion.TransactionManager;

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
    public InstanceCoursResourceImpl(InstanceCoursService pInstanceCoursService, Connexion pConnexion) {
    	super(pInstanceCoursService, InstanceCours.class, pConnexion);
    }
    
    @Override
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
    @CheckSession
	public List<InstanceCours> getInstances(final Cours cours) throws ApplicationException {
    	try {
			return TransactionManager.callInTransaction(
					connexion.getConnection(),
					new Callable<List<InstanceCours>>() {
						public List<InstanceCours> call()
								throws ApplicationException {
							return service.getInstancesByCours(cours);
						}
					});
		} catch (SQLException e) {
			throw new ApplicationException(
					"Erreur lors de la lecture en base de données");
		}
	}

}
