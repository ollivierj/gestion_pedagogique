package net.eni.gestion.pedagogie.service.implementation;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import net.eni.gestion.pedagogie.DAO.AbsenceDao;
import net.eni.gestion.pedagogie.commun.composant.erreur.ApplicationException;
import net.eni.gestion.pedagogie.commun.modele.Absence;
import net.eni.gestion.pedagogie.service.AbsenceService;

import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * @author jollivier Classe d'implémentation pour le module de suivi des
 *         absences
 */
@Singleton
public class AbsenceServiceImpl extends
		AServiceImpl<Absence, Integer, AbsenceDao> implements AbsenceService {

	/**
	 * Constructeur
	 * 
	 * @param DAO
	 *            absence
	 * @throws SQLException
	 */
	@Inject
	public AbsenceServiceImpl(AbsenceDao pAbsenceDao) throws SQLException {
		super(pAbsenceDao);
	}

	@Override
	public ArrayList<Absence> chargerAbsencesByDate(Date pMinDate, Date pMaxDate)
			throws ApplicationException {
		return dao.chargerAbsencesByDate(pMinDate, pMaxDate);
	}

}
