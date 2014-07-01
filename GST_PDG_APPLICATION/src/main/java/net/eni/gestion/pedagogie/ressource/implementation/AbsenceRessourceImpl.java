package net.eni.gestion.pedagogie.ressource.implementation;

import javax.ws.rs.Path;

import net.eni.gestion.pedagogie.modele.Absence;
import net.eni.gestion.pedagogie.ressource.AbsenceRessource;
import net.eni.gestion.pedagogie.service.AbsenceService;

import com.google.inject.Inject;

/**
 * @author jollivier
 * Classe d'implémentation pour le module de gestion des absences
 */
@Path("/absences")
public class AbsenceRessourceImpl extends ARessourceImpl<Absence, Integer, AbsenceService> implements AbsenceRessource {

    /**
     * Constructeur
     * @param AbsenceService
     */
    @Inject
    public AbsenceRessourceImpl(AbsenceService AbsenceService) {
    	super(AbsenceService);
    }

}
