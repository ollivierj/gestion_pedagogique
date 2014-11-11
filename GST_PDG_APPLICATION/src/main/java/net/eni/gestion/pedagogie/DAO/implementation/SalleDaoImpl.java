package net.eni.gestion.pedagogie.DAO.implementation;

import java.sql.SQLException;

import net.eni.gestion.pedagogie.DAO.SalleDao;
import net.eni.gestion.pedagogie.commun.composant.connexion.Connexion;
import net.eni.gestion.pedagogie.commun.modele.Salle;

import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * @author jollivier
 * Service métier "Salle"
 */
@Singleton
public class SalleDaoImpl extends ADaoImpl<Salle, Integer> implements SalleDao{
	
	/**
	 * Constructeur de la DAO SalleBase
	 * @throws SQLException
	 */
	@Inject
	public SalleDaoImpl(Connexion pConnexion) throws SQLException {
		super( Salle.class, pConnexion);
	}
	
}
