package net.eni.gestion.pedagogie.commun.composant.connexion;

import java.sql.SQLException;

import net.eni.gestion.pedagogie.commun.configuration.DatabaseConfiguration;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;

/**
 * @author jollivier
 * Gestion de la connexion à la base de données
 */
public class Connexion {
	/**
	 * Récupère une connexion auprès de la base de données
	 * @return ConnectionSource (ORMLite)
	 * @throws SQLException
	 */
	public static ConnectionSource getConnexion() throws SQLException {
		return new JdbcConnectionSource(DatabaseConfiguration.getDatabaseJdbcConnectionString());
	}
            
}
