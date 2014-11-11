package net.eni.gestion.pedagogie.resource.implementation;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.Callable;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import net.eni.gestion.pedagogie.commun.composant.authentification.annotation.CheckSession;
import net.eni.gestion.pedagogie.commun.composant.connexion.Connexion;
import net.eni.gestion.pedagogie.commun.composant.erreur.ApplicationException;
import net.eni.gestion.pedagogie.commun.modele.StagiairePromotion;
import net.eni.gestion.pedagogie.resource.StagiairePromotionResource;
import net.eni.gestion.pedagogie.service.StagiairePromotionService;

import com.google.inject.Inject;
import net.eni.gestion.pedagogie.commun.composant.connexion.TransactionManager;

@Path("/stagiairesPromotions")
public class StagiairePromotionResourceImpl extends AResourceImpl<StagiairePromotion, Integer, StagiairePromotionService> implements StagiairePromotionResource {

	/**
	 * Constructeur de la ressource stagiaire promotion
	 * @param stagiairePromotionService
	 */
	@Inject
	public StagiairePromotionResourceImpl(StagiairePromotionService stagiairePromotionService) {
		super(stagiairePromotionService, StagiairePromotion.class);
	}

    @GET
    @Path("/stagiaires/{type}/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @CheckSession
	public ArrayList<StagiairePromotion> chargerStagiaireOrPromotion(
			@PathParam("type") final String type, @PathParam("id") final String id) throws ApplicationException {
    	try {
			return TransactionManager.callInTransaction(
					Connexion.getInstance().getConnexion(),
					new Callable<ArrayList<StagiairePromotion>>() {
						public ArrayList<StagiairePromotion> call()
								throws ApplicationException {
							switch (type) {
							case "Promotion":
								return service.chargerByPromotionId(id);
							case "Stagiaire":
								return service.chargerByStagiaireId(Integer.parseInt(id));
							}
							return null;
						}
					});
		} catch (SQLException e) {
			throw new ApplicationException(
					"Erreur lors de la lecture en base de données");
		}
	}


}
