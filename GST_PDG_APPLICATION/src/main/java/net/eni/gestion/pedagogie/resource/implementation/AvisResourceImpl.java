package net.eni.gestion.pedagogie.resource.implementation;

import javax.ws.rs.Path;

import net.eni.gestion.pedagogie.modele.Avis;
import net.eni.gestion.pedagogie.resource.AvisResource;
import net.eni.gestion.pedagogie.service.AvisService;

import com.google.inject.Inject;

/**
 * @author jollivier
 * Classe d'implémentation pour le module de gestion des aviss
 */
@Path("/aviss")
public class AvisResourceImpl extends AResourceImpl<Avis, Integer, AvisService> implements AvisResource {

    /**
     * Constructeur
     * @param AvisService
     */
    @Inject
    public AvisResourceImpl(AvisService AvisService) {
    	super(AvisService, Avis.class);
    }

}
