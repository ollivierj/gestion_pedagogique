package net.eni.gestion.pedagogie.DAO.implementation;

import java.sql.SQLException;

import net.eni.gestion.pedagogie.DAO.UtilisateurDao;
import net.eni.gestion.pedagogie.commun.composant.Connexion;
import net.eni.gestion.pedagogie.modele.Utilisateur;

import com.google.inject.Singleton;

/**
 * @author jollivier
 * Service métier "Utilisateur"
 */
@Singleton
public class UtilisateurDaoImpl extends ADaoImpl<Utilisateur, Integer> implements UtilisateurDao{
	
	/**
	 * Constructeur de la DAO UtilisateurBase
	 * @throws SQLException
	 */
	public UtilisateurDaoImpl() throws SQLException {
		super(Connexion.getConnexion(), Utilisateur.class);
	}

}
