package net.eni.gestion.pedagogie.resource.implementation;

import javax.ws.rs.Path;

import com.google.inject.Inject;

import net.eni.gestion.pedagogie.commun.modele.Salle;
import net.eni.gestion.pedagogie.resource.SalleResource;
import net.eni.gestion.pedagogie.service.SalleService;

/**
 * @author jollivier
 * Classe d'implémentation pour le module de gestion des aviss
 */
@Path("/salles")
public class SalleResourceImpl extends AResourceImpl<Salle, Integer, SalleService> implements SalleResource {

    /**
     * Constructeur
     * @param SalleService
     */
	@Inject
    public SalleResourceImpl(SalleService SalleService) {
    	super(SalleService, Salle.class);
    }

}
