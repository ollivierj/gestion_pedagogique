package net.eni.gestion.pedagogie.service.implementation;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import net.eni.gestion.pedagogie.DAO.AbsenceDao;
import net.eni.gestion.pedagogie.commun.composant.GenericException;
import net.eni.gestion.pedagogie.modele.Absence;
import net.eni.gestion.pedagogie.service.AbsenceService;

import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * @author jollivier
 * Classe d'implémentation pour le module de suivi des absences
 */
@Singleton
public class AbsenceServiceImpl extends AServiceImpl<Absence, Integer, AbsenceDao> implements AbsenceService {

       /**
     * Constructeur
     * @param DAO absence
     * @throws SQLException
     */
    @Inject
    public AbsenceServiceImpl(AbsenceDao pAbsenceDao) throws SQLException {
        super(pAbsenceDao);
    }

	public ArrayList<Absence> chargerRetardatairesByDate(Date pDate)
			throws GenericException {
		try {
			return dao.chargerAbsencesByDate(pDate);
		} catch (Exception e) {
			throw new GenericException("Echec lors du chargement depuis la base de données.");
		}
	}

	public ArrayList<Absence> chargerAbsencesByDate(Date pDate) throws GenericException {
		try {
			return dao.chargerAbsencesByDate(pDate);
		} catch (Exception e) {
			throw new GenericException("Echec lors du chargement depuis la base de données.");
		}
	}

}
