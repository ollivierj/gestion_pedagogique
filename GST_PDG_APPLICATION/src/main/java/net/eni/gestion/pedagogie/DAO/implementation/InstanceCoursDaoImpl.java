package net.eni.gestion.pedagogie.DAO.implementation;

import java.sql.SQLException;

import net.eni.gestion.pedagogie.DAO.InstanceCoursDao;
import net.eni.gestion.pedagogie.commun.composant.Connexion;
import net.eni.gestion.pedagogie.modele.InstanceCours;

import com.google.inject.Singleton;

/**
 * @author jollivier
 * Service métier "InstanceCours"
 */
@Singleton
public class InstanceCoursDaoImpl extends ADaoImpl<InstanceCours, Integer> implements InstanceCoursDao{
	
	/**
	 * Constructeur de la DAO InstanceCoursBase
	 * @throws SQLException
	 */
	public InstanceCoursDaoImpl() throws SQLException {
		super(Connexion.getConnexion(), InstanceCours.class);
	}

}
