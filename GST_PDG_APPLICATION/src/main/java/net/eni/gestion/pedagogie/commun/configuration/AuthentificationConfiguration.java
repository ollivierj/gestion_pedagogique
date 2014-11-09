/**
 * 
 */
package net.eni.gestion.pedagogie.commun.configuration;

import net.eni.gestion.pedagogie.commun.composant.propriete.PropertyFileLoader;

/**
 * @author jollivier
 * Gestion de la configuration de l abase de données
 */
public class AuthentificationConfiguration {
	
	/** Fichier de configuration */
	public final static PropertyFileLoader propertyFileLoader = PropertyFileLoader
			.getInstance("configuration");

	public final static String DEFAULT_EXPIRATION_TIME = "3";
	public final static String DEFAULT_USER = "1";
	public final static String DEFAULT_APPLICATION_MODE = "PROD";
	
	public static Integer getExpirationTime() {
		String value = propertyFileLoader.getValue("authentification.time");
		if(value == null || value.trim().equals("")){
			return Integer.parseInt(DEFAULT_EXPIRATION_TIME);
		}
		return Integer.parseInt(value);
	}
	
	public static String getApplicationMode() {
		String value = propertyFileLoader.getValue("application.mode");
		if(value == null || value.trim().equals("")){
			return DEFAULT_APPLICATION_MODE;
		}
		return value;
	}
	
	public static Integer getDefaultUser() {
		String value = propertyFileLoader.getValue("authentication.default.user.id");
		if(value == null || value.trim().equals("")){
			return Integer.parseInt(DEFAULT_USER);
		}
		return Integer.parseInt(value);
	}
	
}
