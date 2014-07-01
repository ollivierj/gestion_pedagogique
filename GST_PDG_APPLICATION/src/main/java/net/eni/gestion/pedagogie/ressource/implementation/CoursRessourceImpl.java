package net.eni.gestion.pedagogie.ressource.implementation;

import java.util.UUID;

import javax.ws.rs.Path;

import net.eni.gestion.pedagogie.modele.Cours;
import net.eni.gestion.pedagogie.ressource.CoursRessource;
import net.eni.gestion.pedagogie.service.CoursService;

import com.google.inject.Inject;

/**
 * @author jollivier
 * Classe d'implémentation pour le module de gestion des courss
 */
@Path("/courss")
public class CoursRessourceImpl extends ARessourceImpl<Cours, UUID, CoursService> implements CoursRessource {

    /**
     * Constructeur
     * @param coursService
     */
    @Inject
    public CoursRessourceImpl(CoursService coursService) {
    	super(coursService);
    }

}
