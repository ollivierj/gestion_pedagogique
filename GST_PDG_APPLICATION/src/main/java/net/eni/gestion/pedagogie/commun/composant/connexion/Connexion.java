package net.eni.gestion.pedagogie.commun.composant.connexion;

import java.sql.SQLException;

import net.eni.gestion.pedagogie.commun.configuration.DatabaseConfiguration;

import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import com.j256.ormlite.support.ConnectionSource;

@Singleton
public class Connexion implements Provider<ConnectionSource> {
	public ConnectionSource get() {
		try {
			return new JdbcPooledConnectionSource(DatabaseConfiguration.getDatabaseJdbcConnectionString());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
