package net.eni.gestion.pedagogie.resource.implementation;

import javax.ws.rs.Path;

import net.eni.gestion.pedagogie.commun.composant.connexion.Connexion;
import net.eni.gestion.pedagogie.commun.modele.Profil;
import net.eni.gestion.pedagogie.resource.ProfilResource;
import net.eni.gestion.pedagogie.service.ProfilService;

import com.google.inject.Inject;

/**
 * @author jollivier
 * Classe d'implémentation pour le module de gestion des profils
 */
@Path("/profils")
public class ProfilResourceImpl extends AResourceImpl<Profil, Integer, ProfilService> implements ProfilResource {

    /**
     * Constructeur
     * @param profilService
     */
    @Inject
    public ProfilResourceImpl(ProfilService profilService, Connexion pConnexion) {
    	super(profilService, Profil.class);
    }
    
}
