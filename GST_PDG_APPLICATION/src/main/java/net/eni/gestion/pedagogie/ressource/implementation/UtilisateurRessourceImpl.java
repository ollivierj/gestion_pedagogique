package net.eni.gestion.pedagogie.ressource.implementation;

import javax.ws.rs.Path;

import net.eni.gestion.pedagogie.modele.Utilisateur;
import net.eni.gestion.pedagogie.ressource.UtilisateurRessource;
import net.eni.gestion.pedagogie.service.UtilisateurService;

import com.google.inject.Inject;

/**
 * @author jollivier
 * Classe d'implémentation pour le module de gestion des utilisateurs
 */
@Path("/utilisateurs")
public class UtilisateurRessourceImpl extends ARessourceImpl<Utilisateur, Integer, UtilisateurService> implements UtilisateurRessource {

    /**
     * Constructeur
     * @param utilisateurService
     */
    @Inject
    public UtilisateurRessourceImpl(UtilisateurService utilisateurService) {
    	super(utilisateurService);
    }

}
