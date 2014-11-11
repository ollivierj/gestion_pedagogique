package net.eni.gestion.pedagogie.resource.implementation;

import javax.ws.rs.Path;

import net.eni.gestion.pedagogie.commun.composant.connexion.Connexion;
import net.eni.gestion.pedagogie.commun.modele.Parametre;
import net.eni.gestion.pedagogie.resource.ParametreResource;
import net.eni.gestion.pedagogie.service.ParametreService;

import com.google.inject.Inject;

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
    public ParametreResourceImpl(ParametreService parametreService, Connexion pConnexion) {
    	super(parametreService, Parametre.class, pConnexion);
    }

}
