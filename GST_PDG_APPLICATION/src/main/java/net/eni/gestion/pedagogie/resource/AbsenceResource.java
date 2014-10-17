package net.eni.gestion.pedagogie.resource;

import java.util.ArrayList;

import net.eni.gestion.pedagogie.commun.composant.GenericException;
import net.eni.gestion.pedagogie.modele.Absence;

/**
 * @author jollivier
 * Interface service pour le module de suivi des absences
 */
public interface AbsenceResource extends AResource<Absence, Integer> {
	
	public ArrayList<Absence> chargerAbsencesByDate(int year, int month, int day) throws GenericException;

}
