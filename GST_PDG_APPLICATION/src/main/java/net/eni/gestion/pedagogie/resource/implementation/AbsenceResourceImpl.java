package net.eni.gestion.pedagogie.resource.implementation;

import javax.ws.rs.Path;

import net.eni.gestion.pedagogie.modele.Absence;
import net.eni.gestion.pedagogie.resource.AbsenceResource;
import net.eni.gestion.pedagogie.service.AbsenceService;

import com.google.inject.Inject;

/**
 * @author jollivier
 * Classe d'implémentation pour le module de gestion des absences
 */
@Path("/absences")
public class AbsenceResourceImpl extends AResourceImpl<Absence, Integer, AbsenceService> implements AbsenceResource {

    /**
     * Constructeur
     * @param AbsenceService
     */
    @Inject
    public AbsenceResourceImpl(AbsenceService AbsenceService) {
    	super(AbsenceService, Absence.class);
    }

}
