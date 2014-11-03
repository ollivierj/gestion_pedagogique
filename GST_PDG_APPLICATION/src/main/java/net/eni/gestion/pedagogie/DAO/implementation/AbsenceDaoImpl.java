package net.eni.gestion.pedagogie.DAO.implementation;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import net.eni.gestion.pedagogie.DAO.AbsenceDao;
import net.eni.gestion.pedagogie.commun.composant.connexion.Connexion;
import net.eni.gestion.pedagogie.modele.Absence;

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
	public AbsenceDaoImpl() throws SQLException {
		super(Connexion.getConnexion(), Absence.class);
	}

	@Override
	public ArrayList<Absence> chargerAbsencesByDate(Date pMinDate, Date pMaxDate)
			throws Exception {
		return new ArrayList<Absence>(this.queryBuilder().where().between(Absence.DATE_FIELD_NAME, pMinDate, pMaxDate).query());
	}

}
