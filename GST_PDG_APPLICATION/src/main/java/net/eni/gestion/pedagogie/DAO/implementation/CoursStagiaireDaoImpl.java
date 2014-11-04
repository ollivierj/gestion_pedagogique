package net.eni.gestion.pedagogie.DAO.implementation;

import java.sql.SQLException;

import net.eni.gestion.pedagogie.DAO.CoursStagiaireDao;
import net.eni.gestion.pedagogie.commun.composant.connexion.Connexion;
import net.eni.gestion.pedagogie.commun.modele.CoursStagiaire;

import com.google.inject.Singleton;

/**
 * @author jollivier
 * Service métier "CoursStagiaire"
 */
@Singleton
public class CoursStagiaireDaoImpl extends ADaoImpl<CoursStagiaire, Integer> implements CoursStagiaireDao{
	
	
	/**
	 * Constructeur de la DAO CoursStagiaireBase
	 * @throws SQLException
	 */
	public CoursStagiaireDaoImpl() throws SQLException {
		super(Connexion.getConnexion(), CoursStagiaire.class);
	}

}
