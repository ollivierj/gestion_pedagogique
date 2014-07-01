package net.eni.gestion.pedagogie.DAO.implementation;

import java.sql.SQLException;

import net.eni.gestion.pedagogie.DAO.FormationDao;
import net.eni.gestion.pedagogie.commun.composant.Connexion;
import net.eni.gestion.pedagogie.modele.Formation;

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
	public FormationDaoImpl() throws SQLException {
		super(Connexion.getConnexion(), Formation.class);
	}

}
