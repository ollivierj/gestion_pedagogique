package net.eni.gestion.pedagogie.resource.implementation;

import java.util.HashMap;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import net.eni.gestion.pedagogie.commun.composant.GenericException;
import net.eni.gestion.pedagogie.modele.Fonction;
import net.eni.gestion.pedagogie.resource.FonctionResource;
import net.eni.gestion.pedagogie.service.FonctionService;

import com.google.inject.Inject;

/**
 * @author jollivier
 * Classe d'implémentation pour le module de gestion des titreProfessionnels
 */
@Path("/fonctions")
public class FonctionResourceImpl extends AResourceImpl<Fonction, String, FonctionService> implements FonctionResource {

    /**
     * Constructeur
     * @param titreProfessionnelService
     */
    @Inject
    public FonctionResourceImpl(FonctionService titreProfessionnelService) {
    	super(titreProfessionnelService, Fonction.class);
    }
    
    /* (non-Javadoc)
     * @see net.eni.gestion.pedagogie.service.contrat.generique.CRUDService#charger()
     */
    @GET
    @Path("/titlemap")
    @Produces(MediaType.APPLICATION_JSON)
    public HashMap<String, String> getTitleMap() throws GenericException {
        return service.getTitleMap();
    }
    
    

}
