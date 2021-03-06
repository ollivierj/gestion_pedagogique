package net.eni.gestion.pedagogie.resource;

import java.util.ArrayList;

import net.eni.gestion.pedagogie.commun.composant.erreur.ApplicationException;
import net.eni.gestion.pedagogie.commun.modele.Absence;

/**
 * @author jollivier
 * Interface service pour le module de suivi des absences
 */
public interface AbsenceResource extends AResource<Absence, Integer> {
	
	public ArrayList<Absence> chargerAbsencesByDate(int year, int month, int day) throws ApplicationException;

}
