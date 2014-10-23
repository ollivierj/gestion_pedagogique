package net.eni.gestion.pedagogie.DAO.implementation;

import java.sql.SQLException;

import net.eni.gestion.pedagogie.DAO.DroitDao;
import net.eni.gestion.pedagogie.commun.composant.Connexion;
import net.eni.gestion.pedagogie.modele.Droit;

import com.google.inject.Singleton;

/**
 * @author jollivier
 * Service métier "Droit"
 */
@Singleton
public class DroitDaoImpl extends ADaoImpl<Droit, Integer> implements DroitDao{

	
	/**
	 * Constructeur de la DAO DroitBase
	 * @throws SQLException
	 */
	public DroitDaoImpl() throws SQLException {
		super(Connexion.getConnexion(), Droit.class);
	}

}
