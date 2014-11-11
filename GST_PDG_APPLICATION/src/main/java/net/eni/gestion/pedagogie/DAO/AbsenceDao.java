package net.eni.gestion.pedagogie.DAO;

import java.util.ArrayList;
import java.util.Date;

import net.eni.gestion.pedagogie.commun.composant.erreur.ApplicationException;
import net.eni.gestion.pedagogie.commun.modele.Absence;

/**
 * @author jollivier
 * Description des requêtes d'accès (écriture, lecture) en base de données 
 * pour la gestion des absences
 */
public interface AbsenceDao extends ADao<Absence,Integer> {

	ArrayList<Absence> chargerAbsencesByDate(Date pDate, Date pMaxDate) throws ApplicationException;

	

}
