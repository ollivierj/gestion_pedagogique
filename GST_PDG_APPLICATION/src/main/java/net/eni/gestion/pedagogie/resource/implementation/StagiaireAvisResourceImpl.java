package net.eni.gestion.pedagogie.resource.implementation;

import javax.ws.rs.Path;

import net.eni.gestion.pedagogie.commun.composant.connexion.Connexion;
import net.eni.gestion.pedagogie.commun.modele.StagiaireAvis;
import net.eni.gestion.pedagogie.resource.StagiaireAvisResource;
import net.eni.gestion.pedagogie.service.StagiaireAvisService;

import com.google.inject.Inject;

/**
 * @author jollivier
 * Classe d'implémentation pour le module de gestion des aviss
 */
@Path("/stagiaireavis")
public class StagiaireAvisResourceImpl extends AResourceImpl<StagiaireAvis, Integer, StagiaireAvisService> implements StagiaireAvisResource {

    /**
     * Constructeur
     * @param AvisService
     */
    @Inject
    public StagiaireAvisResourceImpl(StagiaireAvisService pStagiaireAvisService, Connexion pConnexion) {
    	super(pStagiaireAvisService, StagiaireAvis.class);
    }

}
