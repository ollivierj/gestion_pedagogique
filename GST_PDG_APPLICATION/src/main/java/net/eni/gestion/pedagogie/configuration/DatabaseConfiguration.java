/**
 * 
 */
package net.eni.gestion.pedagogie.configuration;

import net.eni.gestion.pedagogie.commun.composant.Environment;
import net.eni.gestion.pedagogie.commun.composant.PropertyStore;

/**
 * @author jollivier
 * Gestion de la configuration de l abase de données
 */
public class DatabaseConfiguration {
	
	/** Nom du fichier de configuration */
	public final static String CONFIG_FILE_NAME = "config.properties";

	/** Nom de la propriété hébergeur de la base de données */
	public final static String DATABASE_HOST_PROPERTY_NAME = "dbhost";

	/** Hébergeur de la base de données par défaut */
	public final static String DEFAULT_DATABASE_HOST = "localhost";
	
	
	/** Fournisseur des valeurs du fichier de propriétés */
	private static PropertyStore propertyStore = null;
	
	/** Garant du singleton en environnement multithread */
	private static ThreadLocal<Object> threadLocal = new ThreadLocal<Object>();
	
	/**
	 * Obtention du chemin complet et nom du fichier de configuration
	 * @return Chemin complet et nom du fichier de configuration
	 */
	public static String getConfigurationFileLocation() {
		return Environment.getWebInfLocation() + CONFIG_FILE_NAME;
	}
	
	/**
	 * Obtention de l'hébergeur de la base de données
	 * @return Hébergeur de la base de données
	 */
	public static String getDatabaseHost() {
		return getPropertyStore().getPropertyOrDefault(DATABASE_HOST_PROPERTY_NAME, DEFAULT_DATABASE_HOST);
	}
	
	/**
	 * Obtention de la chaîne de connection JDBC à la base de données hors authentification
	 * @return Chaîne de connection JDBC à la base de données hors authentification
	 */
	public static String getDatabaseJdbcConnectionString() {
		return "jdbc:jtds:sqlserver://"+getDatabaseHost()+":1433/eni_ecole;instance=SQLEXPRESS;user="+getDatabaseLogin()+";password="+getDatabasePassword();
	}
	
	/**
	 * Obtention du login de connection à la base de données
	 * @return Login de connection à la base de donnée 
	 */
	public static String getDatabaseLogin() {
		return "al3b";
	}
	
	/**
	 * Obtention du mot de passe de connection à la base de données
	 * @return Mot de passe de connection à la base de donnée 
	 */
	public static String getDatabasePassword() {
		return "al3b";
	}
	
	/**
	 * Obtention (après création au besoin dans un environnement multithread) de l'entrepôt des propriétés de configuration
	 * @return Entrepôt des propriétés de la configuration
	 */
	private final static PropertyStore getPropertyStore() {
		if (null == threadLocal.get()) {
			synchronized(DatabaseConfiguration.class) {
	        	if (null == propertyStore) {
	        		propertyStore = new PropertyStore(getConfigurationFileLocation());
	        	}
	        	threadLocal.set(Boolean.TRUE);
	      	}
	    }
		return propertyStore;
	}
}
