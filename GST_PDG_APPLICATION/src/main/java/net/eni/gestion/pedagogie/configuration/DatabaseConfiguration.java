/**
 * 
 */
package net.eni.gestion.pedagogie.configuration;

import net.eni.gestion.pedagogie.commun.composant.Environment;
import net.eni.gestion.pedagogie.commun.composant.PropertyFileLoader;
import net.eni.gestion.pedagogie.commun.composant.PropertyStore;

/**
 * @author jollivier
 * Gestion de la configuration de l abase de données
 */
public class DatabaseConfiguration {
	
	/** Fichier de configuration */
	public final static PropertyFileLoader propertyFileLoader = PropertyFileLoader
			.getInstance("configuration");

	/** Nom de la propriété hébergeur de la base de données */
	public final static String DEFAULT_DATABASE_NAME = "eni_ecole";
	
	/** Nom de la propriété login par default de la base de données */
	public final static String DEFAULT_DATABASE_LOGIN = "al3b";
	
	/** Nom de la propriété password par default de la base de données */
	public final static String DEFAULT_DATABASE_PASSWORD = "al3b";

	/** Hébergeur de la base de données par défaut */
	public final static String DEFAULT_DATABASE_HOST = "localhost";
	
	/** Port de connection a la base de données par défaut */
	public final static String DEFAULT_DATABASE_PORT = "1433";
	
	/**
	 * Obtention de l'hébergeur de la base de données
	 * @return Hébergeur de la base de données
	 */
	public static String getDatabaseHost() {
		String value = propertyFileLoader.getValue("db.host");
		if(value == null || value.trim().equals("")){
			return DEFAULT_DATABASE_HOST;
		}
		return value;
	}
	
	/**
	 * Obtention du login de connection à la base de données
	 * @return Login de connection à la base de donnée 
	 */
	public static String getDatabaseLogin(){
		String value = propertyFileLoader.getValue("db.login");
		if(value == null || value.trim().equals("")){
			return DEFAULT_DATABASE_LOGIN;
		}
		return value;
	}
	
	/**
	 * Obtention du mot de passe de connection à la base de données
	 * @return Mot de passe de connection à la base de donnée 
	 */
	public static String getDatabasePassword() {
		String value = propertyFileLoader.getValue("db.passwd");
		if(value == null || value.trim().equals("")){
			return DEFAULT_DATABASE_PASSWORD;
		}
		return value;
	}
	
	/**
	 * Obtention du nom de la base de données
	 * @return Nom la base de donnée 
	 */
	public static String getDatabaseName() {
		String value = propertyFileLoader.getValue("db.name");
		if(value == null || value.trim().equals("")){
			return DEFAULT_DATABASE_NAME;
		}
		return value;
	}
	
	/**
	 * Obtention du port de connection à la base de données
	 * @return Port de connection à la base de donnée 
	 */
	public static String getDatabasePort() {
		String value = propertyFileLoader.getValue("db.port");
		if(value == null || value.trim().equals("")){
			return DEFAULT_DATABASE_PORT;
		}
		return value;
	}
	
	/**
	 * Obtention de la chaîne de connection JDBC à la base de données hors authentification
	 * @return Chaîne de connection JDBC à la base de données hors authentification
	 */
	public static String getDatabaseJdbcConnectionString() {
		return "jdbc:jtds:sqlserver://"+getDatabaseHost()+":"+getDatabasePort()+"/"+getDatabaseName()+";instance=SQLEXPRESS;user="+getDatabaseLogin()+";password="+getDatabasePassword();
	}
	
}
