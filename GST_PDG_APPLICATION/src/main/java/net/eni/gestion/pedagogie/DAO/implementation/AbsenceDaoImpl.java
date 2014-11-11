package net.eni.gestion.pedagogie.DAO.implementation;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import net.eni.gestion.pedagogie.DAO.AbsenceDao;
import net.eni.gestion.pedagogie.commun.composant.connexion.Connexion;
import net.eni.gestion.pedagogie.commun.composant.erreur.ApplicationException;
import net.eni.gestion.pedagogie.commun.modele.Absence;

import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * @author jollivier
 * Service métier "Absence"
 */
@Singleton
public class AbsenceDaoImpl extends ADaoImpl<Absence, Integer> implements AbsenceDao{
	
	/**
	 * Constructeur de la DAO AbsenceBase
	 * @throws SQLException
	 */
	@Inject
	public AbsenceDaoImpl(Connexion pConnexion) throws SQLException {
		super(Absence.class, pConnexion);
	}

	@Override
	public ArrayList<Absence> chargerAbsencesByDate(Date pMinDate, Date pMaxDate)
			throws ApplicationException {
		try {
			return new ArrayList<Absence>(this.queryBuilder().where().between(Absence.DATE_FIELD_NAME, pMinDate, pMaxDate).query());
		} catch (SQLException e) {
			throw new ApplicationException("Erreur lors du chargement des absences");
		}
	}

}
