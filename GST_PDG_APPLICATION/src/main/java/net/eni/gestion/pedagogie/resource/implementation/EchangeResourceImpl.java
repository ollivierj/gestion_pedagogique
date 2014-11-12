package net.eni.gestion.pedagogie.resource.implementation;

import javax.ws.rs.Path;

import com.google.inject.Inject;

import net.eni.gestion.pedagogie.commun.modele.Echange;
import net.eni.gestion.pedagogie.resource.EchangeResource;
import net.eni.gestion.pedagogie.service.EchangeService;

/**
 * @author jollivier
 * Classe d'implémentation pour le module de gestion des echanges
 */
@Path("/echanges")
public class EchangeResourceImpl extends AResourceImpl<Echange, Integer, EchangeService> implements EchangeResource {

    /**
     * Constructeur
     * @param echangeService
     */
	@Inject
    public EchangeResourceImpl(EchangeService echangeService) {
    	super(echangeService, Echange.class);
    }

}
