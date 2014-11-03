package net.eni.gestion.pedagogie.DAO.implementation;

import java.sql.SQLException;

import net.eni.gestion.pedagogie.DAO.UniteParFormationDao;
import net.eni.gestion.pedagogie.commun.composant.connexion.Connexion;
import net.eni.gestion.pedagogie.modele.UniteParFormation;

import com.google.inject.Singleton;

/**
 * @author jollivier
 * Service métier "UniteParFormation"
 */
@Singleton
public class UniteParFormationDaoImpl extends ADaoImpl<UniteParFormation, Integer> implements UniteParFormationDao{
	
	/**
	 * Constructeur de la DAO UniteParFormationBase
	 * @throws SQLException
	 */
	public UniteParFormationDaoImpl() throws SQLException {
		super(Connexion.getConnexion(), UniteParFormation.class);
	}

}
