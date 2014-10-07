package net.eni.gestion.pedagogie.resource.implementation;

import java.util.ArrayList;
import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import net.eni.gestion.pedagogie.commun.composant.GenericException;
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

    @GET
    @Path("/jour/{jour}")
    @Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Absence> chargerAbsencesByDate(@PathParam("jour") Date pDate)
			throws GenericException {
		return service.chargerAbsencesByDate(pDate);
	}

}
