package net.eni.gestion.pedagogie.service;

import java.util.ArrayList;
import java.util.Date;

import net.eni.gestion.pedagogie.commun.composant.GenericException;
import net.eni.gestion.pedagogie.modele.Absence;

/**
 * @author jollivier
 * Interface métier pour le module de suivi des absences
 */
public interface AbsenceService extends AService<Absence,Integer> {

	public ArrayList<Absence> chargerAbsencesByDate(Date pDate)  throws GenericException;

	

}
