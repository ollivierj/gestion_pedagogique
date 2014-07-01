package net.eni.gestion.pedagogie.DAO.implementation;

import java.sql.SQLException;

import net.eni.gestion.pedagogie.DAO.DroitProfilDao;
import net.eni.gestion.pedagogie.commun.composant.Connexion;
import net.eni.gestion.pedagogie.modele.DroitProfil;

import com.google.inject.Singleton;

/**
 * @author jollivier
 * Service métier "DroitProfil"
 */
@Singleton
public class DroitProfilDaoImpl extends ADaoImpl<DroitProfil, Integer> implements DroitProfilDao{
	
	/**
	 * Constructeur de la DAO DroitProfilBase
	 * @throws SQLException
	 */
	public DroitProfilDaoImpl() throws SQLException {
		super(Connexion.getConnexion(), DroitProfil.class);
	}

}
