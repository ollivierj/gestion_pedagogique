package net.eni.gestion.pedagogie.resource.implementation;

import java.util.HashMap;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import net.eni.gestion.pedagogie.commun.composant.GenericException;
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
    
    /* (non-Javadoc)
     * @see net.eni.gestion.pedagogie.service.contrat.generique.CRUDService#charger()
     */
    @GET
    @Path("/titlemap")
    @Produces(MediaType.APPLICATION_JSON)
    public HashMap<Integer, String> getTitleMap() throws GenericException {
        return service.getTitleMap();
    }
    
    

}
