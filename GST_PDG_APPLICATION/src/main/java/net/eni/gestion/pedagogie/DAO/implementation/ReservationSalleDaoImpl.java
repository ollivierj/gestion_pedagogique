package net.eni.gestion.pedagogie.DAO.implementation;

import java.sql.SQLException;

import net.eni.gestion.pedagogie.DAO.ReservationSalleDao;
import net.eni.gestion.pedagogie.commun.composant.Connexion;
import net.eni.gestion.pedagogie.modele.ReservationSalle;

import com.google.inject.Singleton;

/**
 * @author jollivier
 * Service métier "ReservationSalle"
 */
@Singleton
public class ReservationSalleDaoImpl extends ADaoImpl<ReservationSalle, Integer> implements ReservationSalleDao{
	
	/**
	 * Constructeur de la DAO ReservationSalleBase
	 * @throws SQLException
	 */
	public ReservationSalleDaoImpl() throws SQLException {
		super(Connexion.getConnexion(), ReservationSalle.class);
	}

}
