package net.eni.gestion.pedagogie.resource.implementation;

import javax.ws.rs.Path;

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
    
}
