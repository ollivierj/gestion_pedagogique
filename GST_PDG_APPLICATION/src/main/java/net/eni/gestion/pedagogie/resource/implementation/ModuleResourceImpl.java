package net.eni.gestion.pedagogie.resource.implementation;

import java.util.HashMap;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import net.eni.gestion.pedagogie.commun.composant.GenericException;
import net.eni.gestion.pedagogie.modele.Module;
import net.eni.gestion.pedagogie.resource.ModuleResource;
import net.eni.gestion.pedagogie.service.ModuleService;

import com.google.inject.Inject;

/**
 * @author jollivier
 * Classe d'implémentation pour le module de gestion des modules
 */
@Path("/modules")
public class ModuleResourceImpl extends AResourceImpl<Module, Integer, ModuleService> implements ModuleResource {

    /**
     * Constructeur
     * @param titreProfessionnelService
     */
    @Inject
    public ModuleResourceImpl(ModuleService titreProfessionnelService) {
    	super(titreProfessionnelService, Module.class);
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
