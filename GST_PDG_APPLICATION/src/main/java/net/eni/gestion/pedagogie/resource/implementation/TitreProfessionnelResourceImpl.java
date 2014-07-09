package net.eni.gestion.pedagogie.resource.implementation;

import javax.ws.rs.Path;

import net.eni.gestion.pedagogie.modele.TitreProfessionnel;
import net.eni.gestion.pedagogie.resource.TitreProfessionnelResource;
import net.eni.gestion.pedagogie.service.TitreProfessionnelService;

import com.google.inject.Inject;

/**
 * @author jollivier
 * Classe d'implémentation pour le module de gestion des titreProfessionnels
 */
@Path("/titreProfessionnels")
public class TitreProfessionnelResourceImpl extends AResourceImpl<TitreProfessionnel, Integer, TitreProfessionnelService> implements TitreProfessionnelResource {

    /**
     * Constructeur
     * @param titreProfessionnelService
     */
    @Inject
    public TitreProfessionnelResourceImpl(TitreProfessionnelService titreProfessionnelService) {
    	super(titreProfessionnelService, TitreProfessionnel.class);
    }

}
