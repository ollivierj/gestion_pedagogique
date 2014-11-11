package net.eni.gestion.pedagogie.DAO.implementation;

import java.sql.SQLException;

import net.eni.gestion.pedagogie.DAO.FormationDao;
import net.eni.gestion.pedagogie.commun.composant.connexion.Connexion;
import net.eni.gestion.pedagogie.commun.modele.Formation;

import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * @author jollivier
 * Service métier "Formation"
 */
@Singleton
public class FormationDaoImpl extends ADaoImpl<Formation, String> implements FormationDao{
	
	/**
	 * Constructeur de la DAO FormationBase
	 * @throws SQLException
	 */
	@Inject
	public FormationDaoImpl(Connexion pConnexion) throws SQLException {
		super( Formation.class, pConnexion);
	}

}
