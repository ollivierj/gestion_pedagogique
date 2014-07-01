package net.eni.gestion.pedagogie.ressource.implementation;

import javax.ws.rs.Path;

import net.eni.gestion.pedagogie.modele.Avis;
import net.eni.gestion.pedagogie.ressource.AvisRessource;
import net.eni.gestion.pedagogie.service.AvisService;

import com.google.inject.Inject;

/**
 * @author jollivier
 * Classe d'implémentation pour le module de gestion des aviss
 */
@Path("/aviss")
public class AvisRessourceImpl extends ARessourceImpl<Avis, Integer, AvisService> implements AvisRessource {

    /**
     * Constructeur
     * @param AvisService
     */
    @Inject
    public AvisRessourceImpl(AvisService AvisService) {
    	super(AvisService);
    }

}
