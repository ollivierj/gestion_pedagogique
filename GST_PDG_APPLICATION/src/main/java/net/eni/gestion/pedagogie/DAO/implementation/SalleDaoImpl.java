package net.eni.gestion.pedagogie.DAO.implementation;

import java.sql.SQLException;

import net.eni.gestion.pedagogie.DAO.SalleDao;
import net.eni.gestion.pedagogie.commun.composant.Connexion;
import net.eni.gestion.pedagogie.modele.Salle;

import com.google.inject.Singleton;

/**
 * @author jollivier
 * Service métier "Salle"
 */
@Singleton
public class SalleDaoImpl extends ADaoImpl<Salle, String> implements SalleDao{
	
	/**
	 * Constructeur de la DAO SalleBase
	 * @throws SQLException
	 */
	public SalleDaoImpl() throws SQLException {
		super(Connexion.getConnexion(), Salle.class);
	}

}
