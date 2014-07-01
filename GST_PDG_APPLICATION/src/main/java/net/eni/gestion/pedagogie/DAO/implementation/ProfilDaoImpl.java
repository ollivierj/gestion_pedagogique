package net.eni.gestion.pedagogie.DAO.implementation;

import java.sql.SQLException;

import net.eni.gestion.pedagogie.DAO.ProfilDao;
import net.eni.gestion.pedagogie.commun.composant.Connexion;
import net.eni.gestion.pedagogie.modele.Profil;

import com.google.inject.Singleton;

/**
 * @author jollivier
 * Service métier "Profil"
 */
@Singleton
public class ProfilDaoImpl extends ADaoImpl<Profil, Integer> implements ProfilDao{
	
	/**
	 * Constructeur de la DAO ProfilBase
	 * @throws SQLException
	 */
	public ProfilDaoImpl() throws SQLException {
		super(Connexion.getConnexion(), Profil.class);
	}
	
}
