package net.eni.gestion.pedagogie.resource.implementation;

import javax.ws.rs.Path;

import com.google.inject.Inject;

import net.eni.gestion.pedagogie.commun.modele.Fonction;
import net.eni.gestion.pedagogie.resource.FonctionResource;
import net.eni.gestion.pedagogie.service.FonctionService;

/**
 * @author jollivier
 * Classe d'implémentation pour le module de gestion des titreProfessionnels
 */
@Path("/fonctions")
public class FonctionResourceImpl extends AResourceImpl<Fonction, String, FonctionService> implements FonctionResource {

    /**
     * Constructeur
     * @param titreProfessionnelService
     */
	@Inject
    public FonctionResourceImpl(FonctionService titreProfessionnelService) {
    	super(titreProfessionnelService, Fonction.class);
    }
    
}
