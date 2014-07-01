package net.eni.gestion.pedagogie.DAO.implementation;

import java.sql.SQLException;

import net.eni.gestion.pedagogie.DAO.InstanceCoursStagiaireDao;
import net.eni.gestion.pedagogie.commun.composant.Connexion;
import net.eni.gestion.pedagogie.modele.InstanceCoursStagiaire;

import com.google.inject.Singleton;

/**
 * @author jollivier
 * Service métier "InstanceCoursStagiaire"
 */
@Singleton
public class InstanceCoursStagiaireDaoImpl extends ADaoImpl<InstanceCoursStagiaire, Integer> implements InstanceCoursStagiaireDao{
	
	
	/**
	 * Constructeur de la DAO InstanceCoursStagiaireBase
	 * @throws SQLException
	 */
	public InstanceCoursStagiaireDaoImpl() throws SQLException {
		super(Connexion.getConnexion(), InstanceCoursStagiaire.class);
	}

}
