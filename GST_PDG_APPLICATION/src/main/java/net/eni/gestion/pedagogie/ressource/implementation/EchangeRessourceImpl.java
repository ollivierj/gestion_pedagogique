package net.eni.gestion.pedagogie.ressource.implementation;

import javax.ws.rs.Path;

import net.eni.gestion.pedagogie.modele.Echange;
import net.eni.gestion.pedagogie.ressource.EchangeRessource;
import net.eni.gestion.pedagogie.service.EchangeService;

import com.google.inject.Inject;

/**
 * @author jollivier
 * Classe d'implémentation pour le module de gestion des echanges
 */
@Path("/echanges")
public class EchangeRessourceImpl extends ARessourceImpl<Echange, Integer, EchangeService> implements EchangeRessource {

    /**
     * Constructeur
     * @param echangeService
     */
    @Inject
    public EchangeRessourceImpl(EchangeService echangeService) {
    	super(echangeService);
    }

}
