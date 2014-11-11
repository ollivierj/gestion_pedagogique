package net.eni.gestion.pedagogie.resource.implementation;

import javax.ws.rs.Path;

import net.eni.gestion.pedagogie.commun.composant.connexion.Connexion;
import net.eni.gestion.pedagogie.commun.modele.Avis;
import net.eni.gestion.pedagogie.resource.AvisResource;
import net.eni.gestion.pedagogie.service.AvisService;

import com.google.inject.Inject;

/**
 * @author jollivier
 * Classe d'implémentation pour le module de gestion des aviss
 */
@Path("/avis")
public class AvisResourceImpl extends AResourceImpl<Avis, Integer, AvisService> implements AvisResource {

    /**
     * Constructeur
     * @param AvisService
     */
    @Inject
    public AvisResourceImpl(AvisService AvisService, Connexion pConnexion) {
    	super(AvisService, Avis.class, pConnexion);
    }

}
