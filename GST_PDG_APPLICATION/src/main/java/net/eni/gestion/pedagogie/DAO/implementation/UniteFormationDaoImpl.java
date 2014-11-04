package net.eni.gestion.pedagogie.DAO.implementation;

import java.sql.SQLException;

import net.eni.gestion.pedagogie.DAO.UniteFormationDao;
import net.eni.gestion.pedagogie.commun.composant.connexion.Connexion;
import net.eni.gestion.pedagogie.commun.modele.UniteFormation;

import com.google.inject.Singleton;

/**
 * @author jollivier
 * Service métier "UniteFormation"
 */
@Singleton
public class UniteFormationDaoImpl extends ADaoImpl<UniteFormation, Integer> implements UniteFormationDao{
	
	/**
	 * Constructeur de la DAO UniteFormationBase
	 * @throws SQLException
	 */
	public UniteFormationDaoImpl() throws SQLException {
		super(Connexion.getConnexion(), UniteFormation.class);
	}

}
