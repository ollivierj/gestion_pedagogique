package net.eni.gestion.pedagogie.commun.composant.connexion;

import java.sql.SQLException;

import net.eni.gestion.pedagogie.commun.composant.erreur.ApplicationException;
import net.eni.gestion.pedagogie.commun.configuration.DatabaseConfiguration;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;

/**	
	
 * @author jollivier
 * Gestion de la connexion à la base de données
 */
public class Connexion {
	
	private ConnectionSource connexion;
	/** Constructeur privé 
	 * @throws ApplicationException */	
	private Connexion() {
		try {
			this.connexion = new JdbcConnectionSource(DatabaseConfiguration.getDatabaseJdbcConnectionString());
		} catch (SQLException e) {}
	}
 
	/** Holder */
	private static class ConnexionHolder
	{		
		/** Instance unique non préinitialisée */
		private final static Connexion instance = new Connexion();
	}
 
	/** Point d'accès pour l'instance unique du singleton */
	public static Connexion getInstance()
	{
		return ConnexionHolder.instance;
	}

	public ConnectionSource getConnexion() {
		return connexion;
	}
            
}
