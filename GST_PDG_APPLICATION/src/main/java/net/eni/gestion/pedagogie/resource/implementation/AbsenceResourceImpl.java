package net.eni.gestion.pedagogie.resource.implementation;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.Callable;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import net.eni.gestion.pedagogie.commun.composant.authentification.annotation.CheckSession;
import net.eni.gestion.pedagogie.commun.composant.connexion.Connexion;
import net.eni.gestion.pedagogie.commun.composant.connexion.TransactionManager;
import net.eni.gestion.pedagogie.commun.composant.erreur.ApplicationException;
import net.eni.gestion.pedagogie.commun.modele.Absence;
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
    public AbsenceResourceImpl(AbsenceService AbsenceService, Connexion pConnexion) {
    	super(AbsenceService, Absence.class, pConnexion);
    }

	@GET
    @Path("/jour/{year}/{month}/{day}")
    @Produces(MediaType.APPLICATION_JSON)
	@CheckSession
	public ArrayList<Absence> chargerAbsencesByDate(@PathParam("year") final int year,
			@PathParam("month") final int month, 
			@PathParam("day") final int day)
			throws ApplicationException {
		try {
			return TransactionManager.callInTransaction(
					connexion.getConnection(),
					new Callable<ArrayList<Absence>>() {
						public ArrayList<Absence> call()
								throws ApplicationException {
					    	Calendar lCalendar = GregorianCalendar.getInstance();
					    	lCalendar.set(year,month,day, lCalendar.getMinimum(GregorianCalendar.HOUR_OF_DAY), lCalendar.getMinimum(GregorianCalendar.MINUTE), lCalendar.getMinimum(GregorianCalendar.SECOND));
					    	Date lMinDate = lCalendar.getTime();
					    	lCalendar.set(year,month,day, lCalendar.getMaximum(GregorianCalendar.HOUR_OF_DAY), lCalendar.getMaximum(GregorianCalendar.MINUTE), lCalendar.getMaximum(GregorianCalendar.SECOND));
							Date lMaxDate = lCalendar.getTime();
					    	return service.chargerAbsencesByDate(lMinDate, lMaxDate);
						}
					});
		} catch (SQLException e) {
			throw new ApplicationException(
					"Erreur lors de la lecture en base de données");
		}
	}

}
