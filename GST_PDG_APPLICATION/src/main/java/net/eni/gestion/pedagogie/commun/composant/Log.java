package net.eni.gestion.pedagogie.commun.composant;

import java.util.Properties;

import net.eni.gestion.pedagogie.configuration.BaseSetup;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * @author jollivier
 * Gestion des logs
 */
public class Log {
	
	/** Nom du fichier de configuration */
	public final static String LOGGER_FILE_NAME = "logger.properties";
	
	/** Fournisseur des valeurs du fichier de propriétés */
	private static PropertyStore propertyStore = null;
	
	/** Logger */
	private static Logger logger = null;
	
	/** Garant du singleton en environnement multithread */
	private static ThreadLocal<Object> threadLocal = new ThreadLocal<Object>();

	/** Log un debug */
	public static void debug(Object pMessage) {
		try {
			getLogger().debug(pMessage);
		}
		catch (Exception pException) {}
	}
	
	/** Log une information */
	public static void info(Object pMessage) {
		try {
			getLogger().info(pMessage);
		}
		catch (Exception pException) {}
	}
	
	/** Log un avertissement */
	public static void warn(Object pMessage) {
		try {
			getLogger().warn(pMessage);
		}
		catch (Exception pException) {}
	}
	
	/** Log une erreur */
	public static void error(Object pMessage) {
		try {
			getLogger().error(pMessage);
		}
		catch (Exception pException) {}
	}
	
	/** Log une erreur fatale */
	public static void fatal(Object pMessage) {
		try {
			getLogger().fatal(pMessage);
		}
		catch (Exception pException) {}
	}
	
	/**
	 * Obtention du chemin complet et nom du fichier de configuration
	 * @return Chemin complet et nom du fichier de configuration
	 */
	public static String getLoggerFileLocation() {
		return Environment.getWebInfLocation() + LOGGER_FILE_NAME;
	}
	
	/**
	 * Obtention (après création au besoin dans un environnement multithread) de l'entrepôt des propriétés de configuration
	 * @return Entrepôt des propriétés de la configuration
	 */
	public final static Logger getLogger() {
		if (null == threadLocal.get()) {
			synchronized(BaseSetup.class) {
	        	if (null == propertyStore) {
	        		logger = Logger.getLogger(Log.class);
	        		logger.setLevel(Level.DEBUG);
	        		PropertyConfigurator.configure(getProperties());
	        	}
	        	threadLocal.set(Boolean.TRUE);
	      	}
	    }
		return logger;
	}
	
	/**
	 * Lecture des propriéts du logger depuis le fichier de configuration
	 * @return Les propriétés de configuration du logger
	 */
	private final static Properties getProperties() {
		PropertyStore lPropertyStore = new PropertyStore(getLoggerFileLocation());
		Properties lProperties = lPropertyStore.getProperties();
		if (lProperties.isEmpty()) {
			// Propriétés par défaut
			lProperties.put("log4j.rootLogger", "info, R");						
			lProperties.put("log4j.appender.R", "org.apache.log4j.RollingFileAppender");
			lProperties.put("log4j.appender.R.File", "${catalina.home}/logs/dispeo_server.log");						
			lProperties.put("log4j.appender.R.MaxFileSize", "50KB");
			lProperties.put("log4j.appender.R.MaxBackupIndex", "2");						
			lProperties.put("log4j.appender.R.layout", "org.apache.log4j.PatternLayout");
			lProperties.put("log4j.appender.R.layout.ConversionPattern", "%d{dd/MM/yyyy HH:mm:ss}\t%p\t: %m%n");			
		}
		return lProperties;
	}
}
