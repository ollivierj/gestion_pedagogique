package net.eni.gestion.pedagogie.DAO.implementation;

import java.sql.SQLException;

import net.eni.gestion.pedagogie.DAO.JuryDao;
import net.eni.gestion.pedagogie.commun.composant.connexion.Connexion;
import net.eni.gestion.pedagogie.modele.Jury;

import com.google.inject.Singleton;

/**
 * @author jollivier
 * Service métier "Jury"
 */
@Singleton
public class JuryDaoImpl extends ADaoImpl<Jury, Integer> implements JuryDao{
	
	/**
	 * Constructeur de la DAO JuryBase
	 * @throws SQLException
	 */
	public JuryDaoImpl() throws SQLException {
		super(Connexion.getConnexion(), Jury.class);
	}

}
