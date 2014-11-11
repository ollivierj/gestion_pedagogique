package net.eni.gestion.pedagogie.DAO.implementation;

import java.sql.SQLException;

import net.eni.gestion.pedagogie.DAO.UniteParFormationDao;
import net.eni.gestion.pedagogie.commun.composant.connexion.Connexion;
import net.eni.gestion.pedagogie.commun.modele.UniteParFormation;

import com.google.inject.Inject;
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
	@Inject
	public UniteParFormationDaoImpl(Connexion pConnexion) throws SQLException {
		super( UniteParFormation.class, pConnexion);
	}

}
