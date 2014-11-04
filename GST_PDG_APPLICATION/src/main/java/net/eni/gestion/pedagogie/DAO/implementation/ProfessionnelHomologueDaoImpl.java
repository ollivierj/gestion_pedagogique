package net.eni.gestion.pedagogie.DAO.implementation;

import java.sql.SQLException;

import net.eni.gestion.pedagogie.DAO.ProfessionnelHomologueDao;
import net.eni.gestion.pedagogie.commun.composant.connexion.Connexion;
import net.eni.gestion.pedagogie.commun.modele.ProfessionnelHomologue;

import com.google.inject.Singleton;

/**
 * @author jollivier
 * Service métier "ProfessionnelHomologue"
 */
@Singleton
public class ProfessionnelHomologueDaoImpl extends ADaoImpl<ProfessionnelHomologue, Integer> implements ProfessionnelHomologueDao{
	
	/**
	 * Constructeur de la DAO ProfessionnelHomologueBase
	 * @throws SQLException
	 */
	public ProfessionnelHomologueDaoImpl() throws SQLException {
		super(Connexion.getConnexion(), ProfessionnelHomologue.class);
	}

}
