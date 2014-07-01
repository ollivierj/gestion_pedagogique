package net.eni.gestion.pedagogie.DAO.implementation;

import java.sql.SQLException;

import net.eni.gestion.pedagogie.DAO.HomologationDao;
import net.eni.gestion.pedagogie.commun.composant.Connexion;
import net.eni.gestion.pedagogie.modele.Homologation;

import com.google.inject.Singleton;

/**
 * @author jollivier
 * Service métier "Homologation"
 */
@Singleton
public class HomologationDaoImpl extends ADaoImpl<Homologation, Integer> implements HomologationDao{
	
	/**
	 * Constructeur de la DAO HomologationBase
	 * @throws SQLException
	 */
	public HomologationDaoImpl() throws SQLException {
		super(Connexion.getConnexion(), Homologation.class);
	}

}
