package net.eni.gestion.pedagogie.DAO.implementation;

import java.sql.SQLException;

import net.eni.gestion.pedagogie.DAO.ReservationSalleDao;
import net.eni.gestion.pedagogie.commun.composant.connexion.Connexion;
import net.eni.gestion.pedagogie.commun.modele.ReservationSalle;

import com.google.inject.Inject;
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
	@Inject
	public ReservationSalleDaoImpl(Connexion pConnexion) throws SQLException {
		super( ReservationSalle.class, pConnexion);
	}

}
