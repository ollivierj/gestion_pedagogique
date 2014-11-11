package net.eni.gestion.pedagogie.DAO.implementation;

import java.sql.SQLException;

import net.eni.gestion.pedagogie.DAO.DroitDao;
import net.eni.gestion.pedagogie.commun.composant.connexion.Connexion;
import net.eni.gestion.pedagogie.commun.modele.Droit;

import com.google.inject.Inject;
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
	@Inject
	public DroitDaoImpl(Connexion pConnexion) throws SQLException {
		super( Droit.class, pConnexion);
	}

}
