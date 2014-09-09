package net.eni.gestion.pedagogie.resource.implementation;

import java.util.HashMap;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import net.eni.gestion.pedagogie.commun.composant.GenericException;
import net.eni.gestion.pedagogie.modele.Profil;
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
    public ProfilResourceImpl(ProfilService profilService) {
    	super(profilService, Profil.class);
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
