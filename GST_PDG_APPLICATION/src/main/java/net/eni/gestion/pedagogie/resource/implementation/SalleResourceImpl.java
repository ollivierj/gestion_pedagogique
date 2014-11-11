package net.eni.gestion.pedagogie.resource.implementation;

import javax.ws.rs.Path;

import net.eni.gestion.pedagogie.commun.composant.connexion.Connexion;
import net.eni.gestion.pedagogie.commun.modele.Salle;
import net.eni.gestion.pedagogie.resource.SalleResource;
import net.eni.gestion.pedagogie.service.SalleService;

import com.google.inject.Inject;

/**
 * @author jollivier
 * Classe d'implémentation pour le module de gestion des aviss
 */
@Path("/salles")
public class SalleResourceImpl extends AResourceImpl<Salle, Integer, SalleService> implements SalleResource {

    /**
     * Constructeur
     * @param SalleService
     */
    @Inject
    public SalleResourceImpl(SalleService SalleService, Connexion pConnexion) {
    	super(SalleService, Salle.class);
    }

}
