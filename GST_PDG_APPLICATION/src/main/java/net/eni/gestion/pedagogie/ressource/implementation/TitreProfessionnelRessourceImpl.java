package net.eni.gestion.pedagogie.ressource.implementation;

import javax.ws.rs.Path;

import net.eni.gestion.pedagogie.modele.TitreProfessionnel;
import net.eni.gestion.pedagogie.ressource.TitreProfessionnelRessource;
import net.eni.gestion.pedagogie.service.TitreProfessionnelService;

import com.google.inject.Inject;

/**
 * @author jollivier
 * Classe d'implémentation pour le module de gestion des titreProfessionnels
 */
@Path("/titreProfessionnels")
public class TitreProfessionnelRessourceImpl extends ARessourceImpl<TitreProfessionnel, Integer, TitreProfessionnelService> implements TitreProfessionnelRessource {

    /**
     * Constructeur
     * @param titreProfessionnelService
     */
    @Inject
    public TitreProfessionnelRessourceImpl(TitreProfessionnelService titreProfessionnelService) {
    	super(titreProfessionnelService);
    }

}
