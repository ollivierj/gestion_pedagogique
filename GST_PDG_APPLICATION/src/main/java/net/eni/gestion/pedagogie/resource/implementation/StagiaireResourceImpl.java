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
import net.eni.gestion.pedagogie.commun.composant.map.NamedObjectMap;
import net.eni.gestion.pedagogie.commun.modele.Promotion;
import net.eni.gestion.pedagogie.commun.modele.Stagiaire;
import net.eni.gestion.pedagogie.resource.StagiaireResource;
import net.eni.gestion.pedagogie.service.StagiaireService;

import com.google.inject.Inject;
import net.eni.gestion.pedagogie.commun.composant.connexion.TransactionManager;

/**
 * @author jollivier
 * Classe d'implémentation pour le module de gestion des stagiaires
 */
@Path("/stagiaires")
public class StagiaireResourceImpl extends AResourceImpl<Stagiaire, Integer, StagiaireService> implements StagiaireResource {

    /**
     * Constructeur
     * @param stagiaireService
     */
    @Inject
    public StagiaireResourceImpl(StagiaireService stagiaireService, Connexion pConnexion) {
    	super(stagiaireService, Stagiaire.class, pConnexion);
    }
    
    @GET
    @Path("/stagiaireOrPromotionAutocomplete/{search}")
    @Produces(MediaType.APPLICATION_JSON)
    @CheckSession
	public ArrayList<NamedObjectMap> chargerStagiaireOrPromotionAutocomplete(@PathParam("search") final String pSearchText) throws ApplicationException {
    	try {
			return TransactionManager.callInTransaction(
					connexion.getConnection(),
					new Callable<ArrayList<NamedObjectMap>>() {
						public ArrayList<NamedObjectMap> call()
								throws ApplicationException {
							ArrayList<NamedObjectMap> lResultList = new ArrayList<NamedObjectMap>();
					    	ArrayList<Stagiaire> lStagiaireList = service.chargerForAutocompleteSearch(pSearchText);
					    	ArrayList<Promotion> lPromotionList = service.chargerPromotionForAutocompleteSearch(pSearchText);
					    	for (Promotion lPromotion : lPromotionList) {
								NamedObjectMap lResult = new NamedObjectMap();
								lResult.put("type", "Promotion");
								lResult.put("id", lPromotion.getId());
								lResult.put("libelle", lPromotion.getId());
								lResultList.add(lResult);
					    	}
					    	for (Stagiaire lStagiaire : lStagiaireList) {
								NamedObjectMap lResult = new NamedObjectMap();
								lResult.put("type", "Stagiaire");
								lResult.put("id", lStagiaire.getId());
								StringBuilder lStrBuilder = new StringBuilder();
								lStrBuilder.append(lStagiaire.getPrenom());
								lStrBuilder.append(" ");
								lStrBuilder.append(lStagiaire.getNom());
								lResult.put("libelle", lStrBuilder.toString());
								lResultList.add(lResult);
					    	}
					    	return lResultList;
						}
					});
		} catch (SQLException e) {
			throw new ApplicationException(
					"Erreur lors de la lecture en base de données");
		}
	}
    
    @GET
    @Path("/stagiaireAutocomplete/{search}")
    @Produces(MediaType.APPLICATION_JSON)
    @CheckSession
	public ArrayList<NamedObjectMap> chargerStagiaireAutocomplete(@PathParam("search") final String pSearchText) throws ApplicationException {
    	try {
			return TransactionManager.callInTransaction(
					connexion.getConnection(),
					new Callable<ArrayList<NamedObjectMap>>() {
						public ArrayList<NamedObjectMap> call()
								throws ApplicationException {
							ArrayList<NamedObjectMap> lResultList = new ArrayList<NamedObjectMap>();
					    	ArrayList<Stagiaire> lStagiaireList = service.chargerForAutocompleteSearch(pSearchText);
					    	for (Stagiaire lStagiaire : lStagiaireList) {
								NamedObjectMap lResult = new NamedObjectMap();
								lResult.put("type", "Stagiaire");
								lResult.put("id", lStagiaire.getId());
								StringBuilder lStrBuilder = new StringBuilder();
								lStrBuilder.append(lStagiaire.getPrenom());
								lStrBuilder.append(" ");
								lStrBuilder.append(lStagiaire.getNom());
								lResult.put("libelle", lStrBuilder.toString());
								lResultList.add(lResult);
					    	}
					    	return lResultList;
						}
					});
		} catch (SQLException e) {
			throw new ApplicationException(
					"Erreur lors de la lecture en base de données");
		}
	}
}
