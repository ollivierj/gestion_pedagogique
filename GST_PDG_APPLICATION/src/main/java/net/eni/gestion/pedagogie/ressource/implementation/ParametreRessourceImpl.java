package net.eni.gestion.pedagogie.ressource.implementation;

import javax.ws.rs.Path;

import net.eni.gestion.pedagogie.modele.Parametre;
import net.eni.gestion.pedagogie.ressource.ParametreRessource;
import net.eni.gestion.pedagogie.service.ParametreService;

import com.google.inject.Inject;

/**
 * @author jollivier
 * Classe d'implémentation pour le module de gestion des parametres
 */
@Path("/parametres")
public class ParametreRessourceImpl extends ARessourceImpl<Parametre, String, ParametreService> implements ParametreRessource {

    /**
     * Constructeur
     * @param parametreService
     */
    @Inject
    public ParametreRessourceImpl(ParametreService parametreService) {
    	super(parametreService);
    }

}
