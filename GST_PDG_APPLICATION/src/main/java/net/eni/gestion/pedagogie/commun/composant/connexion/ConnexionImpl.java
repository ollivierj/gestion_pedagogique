package net.eni.gestion.pedagogie.commun.composant.connexion;

import java.sql.SQLException;

import net.eni.gestion.pedagogie.commun.configuration.DatabaseConfiguration;

import com.google.inject.Singleton;
import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import com.j256.ormlite.support.ConnectionSource;

/**	
	
 * @author jollivier
 * Gestion de la connexion à la base de données
 */
@Singleton
public class ConnexionImpl implements Connexion{
	
	ConnectionSource connexion;
 
	public ConnexionImpl() throws SQLException {
		super();
		this.connexion = new JdbcPooledConnectionSource(DatabaseConfiguration.getDatabaseJdbcConnectionString());
	}

	@Override
	public ConnectionSource getConnection() {
		return this.connexion;
	}

	
            
}
