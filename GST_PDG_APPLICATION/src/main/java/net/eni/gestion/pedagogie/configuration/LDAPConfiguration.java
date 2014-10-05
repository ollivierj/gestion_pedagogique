/**
 * 
 */
package net.eni.gestion.pedagogie.configuration;


/**
 * @author fgirardeau
 * Gestion de la configuration au serveur LDAP
 */
public class LDAPConfiguration {
	
	/** Nom du domaine du serveur ldap */
	public final static String LDAP_DOMAINE = "eni.local";
	
	/** IP du serveur ldap */
	public final static String LDAP_IP = "192.168.0.48";

	/** Port de connection au serveur ldap */
	public final static String LDAP_PORT = "389";

	
	public static String getAdresseLDAP(){
		return LDAP_IP+":"+LDAP_PORT;
	}
	
}
