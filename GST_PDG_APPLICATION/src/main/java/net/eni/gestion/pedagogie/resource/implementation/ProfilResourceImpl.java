package net.eni.gestion.pedagogie.resource.implementation;

import javax.ws.rs.Path;

import com.google.inject.Inject;

import net.eni.gestion.pedagogie.commun.modele.Profil;
import net.eni.gestion.pedagogie.resource.ProfilResource;
import net.eni.gestion.pedagogie.service.ProfilService;

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
    public ProfilResourceImpl(ProfilService profilService) {
    	super(profilService, Profil.class);
    }
    
}
