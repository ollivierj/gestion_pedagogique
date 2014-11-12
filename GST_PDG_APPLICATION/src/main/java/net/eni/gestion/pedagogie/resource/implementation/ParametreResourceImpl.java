package net.eni.gestion.pedagogie.resource.implementation;

import javax.ws.rs.Path;

import com.google.inject.Inject;

import net.eni.gestion.pedagogie.commun.modele.Parametre;
import net.eni.gestion.pedagogie.resource.ParametreResource;
import net.eni.gestion.pedagogie.service.ParametreService;

/**
 * @author jollivier
 * Classe d'implémentation pour le module de gestion des parametres
 */
@Path("/parametres")
public class ParametreResourceImpl extends AResourceImpl<Parametre, String, ParametreService> implements ParametreResource {

    /**
     * Constructeur
     * @param parametreService
     */
	@Inject
    public ParametreResourceImpl(ParametreService parametreService) {
    	super(parametreService, Parametre.class);
    }

}
