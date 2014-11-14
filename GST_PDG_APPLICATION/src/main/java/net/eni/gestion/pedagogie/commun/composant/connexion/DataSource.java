package net.eni.gestion.pedagogie.commun.composant.connexion;

import java.sql.SQLException;

import net.eni.gestion.pedagogie.commun.configuration.DatabaseConfiguration;

import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;

public final class DataSource {
	 
    // L'utilisation du mot clé volatile, en Java version 5 et supérieur, 
    // permet d'éviter le cas où "DataSource.instance" est non-nul,
    // mais pas encore "réellement" instancié.
    // De Java version 1.2 à 1.4, il est possible d'utiliser la classe ThreadLocal.
    private static volatile DataSource instance = null;

    // D'autres attributs, classiques et non "static".
    private JdbcPooledConnectionSource connectionSource;

    /**
     * Constructeur de l'objet.
     * @throws SQLException 
     */
    private DataSource() throws SQLException {
    	connectionSource = new JdbcPooledConnectionSource(DatabaseConfiguration.getDatabaseJdbcConnectionString());
    	connectionSource.setMaxConnectionAgeMillis(5 * 60 * 1000);
    }

    /**
     * Méthode permettant de renvoyer une instance de la classe DataSource
     * @return Retourne l'instance du singleton.
     * @throws SQLException 
     */
    public final static DataSource getInstance() throws SQLException {
        //Le "Double-Checked DataSource"/"DataSource doublement vérifié" permet 
        //d'éviter un appel coûteux à synchronized, 
        //une fois que l'instanciation est faite.
        if (DataSource.instance == null) {
           // Le mot-clé synchronized sur ce bloc empêche toute instanciation
           // multiple même par différents "threads".
           // Il est TRES important.
           synchronized(DataSource.class) {
             if (DataSource.instance == null) {
               DataSource.instance = new DataSource();
             }
           }
        }
        return DataSource.instance;
    }

    public JdbcPooledConnectionSource getConnectionSource() {
		return connectionSource;
	}
	public void setConnectionSource(JdbcPooledConnectionSource connectionSource) {
		this.connectionSource = connectionSource;
	}

}
