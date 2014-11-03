/**
 * 
 */
package net.eni.gestion.pedagogie.configuration;

import net.eni.gestion.pedagogie.commun.composant.propriete.PropertyFileLoader;


/**
 * @author fgirardeau
 * Gestion de la configuration au serveur LDAP
 */
public class LDAPConfiguration {
	
	public final static PropertyFileLoader propertyFileLoader = PropertyFileLoader
			.getInstance("configuration");
	
	/** Nom du domaine du serveur ldap */
	public final static String LDAP_DOMAINE_DEFAULT = "eni.local";
	
	
	/** IP du serveur ldap */
	public final static String LDAP_IP_DEFAULT = "192.168.0.48";

	/** Port de connection au serveur ldap */
	public final static String LDAP_PORT_DEFAULT = "389";

	/** Lors de la création d'un utilisateur depuis LDAP numero du profil par default a lui affecter */
	public final static Integer LDAP_CREATE_USER_DEFAULT_PROFIL = 1;
	
	
	
	public static String getLdapDomaine(){
		String value = propertyFileLoader.getValue("ldap.domaine");
		if(value == null || value.trim().equals("")){
			return LDAP_DOMAINE_DEFAULT;
		}
		return value;
	}
	
	public static String getLdapIp(){
		String value = propertyFileLoader.getValue("ldap.ip");
		if(value == null || value.trim().equals("")){
			return LDAP_IP_DEFAULT;
		}
		return value;
	}
	
	public static String getLdapPort(){
		String value = propertyFileLoader.getValue("ldap.port");
		if(value == null || value.trim().equals("")){
			return LDAP_PORT_DEFAULT;
		}
		return value;
	}
	
	public static Integer getDefaultUserProfil(){
		String value = propertyFileLoader.getValue("ldap.user.default.profil");
		if(value == null || value.trim().equals("")){
			return LDAP_CREATE_USER_DEFAULT_PROFIL;
		}
		return Integer.parseInt(value);
	}
	
	public static String getAdresseLDAP(){
		return getLdapIp()+":"+getLdapPort();
	}
	
}
