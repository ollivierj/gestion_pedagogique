package net.eni.gestion.pedagogie.resource.implementation;

import javax.ws.rs.Path;

import net.eni.gestion.pedagogie.modele.Utilisateur;
import net.eni.gestion.pedagogie.resource.UtilisateurResource;
import net.eni.gestion.pedagogie.service.UtilisateurService;

import com.google.inject.Inject;

/**
 * @author jollivier
 * Classe d'implémentation pour le module de gestion des utilisateurs
 */
@Path("/utilisateurs")
public class UtilisateurResourceImpl extends AResourceImpl<Utilisateur, Integer, UtilisateurService> implements UtilisateurResource {

    /**
     * Constructeur
     * @param utilisateurService
     */
    @Inject
    public UtilisateurResourceImpl(UtilisateurService utilisateurService) {
    	super(utilisateurService, Utilisateur.class);
    }

}
