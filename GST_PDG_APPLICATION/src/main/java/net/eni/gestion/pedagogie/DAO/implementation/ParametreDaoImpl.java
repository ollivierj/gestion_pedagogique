package net.eni.gestion.pedagogie.DAO.implementation;

import java.sql.SQLException;

import net.eni.gestion.pedagogie.DAO.ParametreDao;
import net.eni.gestion.pedagogie.commun.composant.Connexion;
import net.eni.gestion.pedagogie.modele.Parametre;

import com.google.inject.Singleton;

/**
 * @author jollivier
 * Service métier "Parametre"
 */
@Singleton
public class ParametreDaoImpl extends ADaoImpl<Parametre, String> implements ParametreDao{
	
	/**
	 * Constructeur de la DAO ParametreBase
	 * @throws SQLException
	 */
	public ParametreDaoImpl() throws SQLException {
		super(Connexion.getConnexion(), Parametre.class);
	}

}
