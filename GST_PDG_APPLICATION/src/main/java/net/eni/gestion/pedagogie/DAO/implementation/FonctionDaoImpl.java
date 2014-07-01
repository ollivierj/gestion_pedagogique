package net.eni.gestion.pedagogie.DAO.implementation;

import java.sql.SQLException;

import net.eni.gestion.pedagogie.DAO.FonctionDao;
import net.eni.gestion.pedagogie.commun.composant.Connexion;
import net.eni.gestion.pedagogie.modele.Fonction;

import com.google.inject.Singleton;

/**
 * @author jollivier
 * Service métier "Fonction"
 */
@Singleton
public class FonctionDaoImpl extends ADaoImpl<Fonction, String> implements FonctionDao{
	
	/**
	 * Constructeur de la DAO FonctionBase
	 * @throws SQLException
	 */
	public FonctionDaoImpl() throws SQLException {
		super(Connexion.getConnexion(), Fonction.class);
	}
	
}
