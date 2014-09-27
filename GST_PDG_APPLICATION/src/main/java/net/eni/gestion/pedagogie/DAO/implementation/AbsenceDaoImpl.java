package net.eni.gestion.pedagogie.DAO.implementation;

import java.sql.SQLException;

import net.eni.gestion.pedagogie.DAO.AbsenceDao;
import net.eni.gestion.pedagogie.commun.composant.Connexion;
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

	/*public ArrayList<Absence> chargerAbsencesByDate(Date pDate)
			throws Exception {
		return new ArrayList<Absence>(this.queryForEq(Absence.DATE_SAISIE_FIELD_NAME, pDate));
	}*/

}
