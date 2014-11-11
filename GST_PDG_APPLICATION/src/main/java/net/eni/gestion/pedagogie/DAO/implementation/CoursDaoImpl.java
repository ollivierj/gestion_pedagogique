package net.eni.gestion.pedagogie.DAO.implementation;

import java.sql.SQLException;
import java.util.UUID;

import net.eni.gestion.pedagogie.DAO.CoursDao;
import net.eni.gestion.pedagogie.commun.composant.connexion.Connexion;
import net.eni.gestion.pedagogie.commun.modele.Cours;

import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * @author jollivier
 * Service métier "Cours"
 */
@Singleton
public class CoursDaoImpl extends ADaoImpl<Cours, UUID> implements CoursDao{
	
	/**
	 * Constructeur de la DAO CoursBase
	 * @throws SQLException
	 */
	@Inject
	public CoursDaoImpl(Connexion pConnexion) throws SQLException {
		super( Cours.class, pConnexion);
	}

}
