package net.eni.gestion.pedagogie.ressource.implementation;

import javax.ws.rs.Path;

import net.eni.gestion.pedagogie.modele.Profil;
import net.eni.gestion.pedagogie.ressource.ProfilRessource;
import net.eni.gestion.pedagogie.service.ProfilService;

import com.google.inject.Inject;

/**
 * @author jollivier
 * Classe d'implémentation pour le module de gestion des profils
 */
@Path("/profils")
public class ProfilRessourceImpl extends ARessourceImpl<Profil, Integer, ProfilService> implements ProfilRessource {

    /**
     * Constructeur
     * @param profilService
     */
    @Inject
    public ProfilRessourceImpl(ProfilService profilService) {
    	super(profilService);
    }

}
