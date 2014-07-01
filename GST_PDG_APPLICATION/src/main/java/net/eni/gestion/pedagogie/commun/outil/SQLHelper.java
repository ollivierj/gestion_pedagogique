package net.eni.gestion.pedagogie.commun.outil;

import java.util.Calendar;
import java.util.Date;

import com.j256.ormlite.stmt.SelectArg;

/**
 * @author jollivier
 * Outil pour la gestion des réquêtes d'accès en base de données
 */
public class SQLHelper {
	
	/**
	 * Récupère un argument de requête ORMLite depuis un objet
	 * @param Objet
	 * @return SelectArg
	 */
	public static SelectArg SQLArgument(Object pArgument) {
		return new SelectArg(pArgument);
	}
	
	/**
	 * Protège un argument de requête depuis une châine
	 * @param chaîne
	 * @return chaîne protégée
	 */
	public static String rawSQLArgument(String pArgument) {
		return (null != pArgument)
			?	"'" + pArgument.replace("\\", "\\\\").replace("'", "\\'") + "'"
			:	"NULL";
	}

	/**
	 * Récupère un argument de requête depuis un long 
	 * @param Long
	 * @return chaîne
	 */
	public static String rawSQLArgument(Long pArgument) {
		return (null != pArgument)
			?	pArgument.toString()
			:	"NULL";
	}

	/**
	 * Récupère un argument de requête depuis un entier
	 * @param Eniter
	 * @return chaîne
	 */
	public static String rawSQLArgument(Integer pArgument) {
		return (null != pArgument)
			?	pArgument.toString()
			:	"NULL";
	}

	/**
	 * Récupère un argument de requête depuis un booleen 
	 * @param Booleen
	 * @return chaîne
	 */
	public static String rawSQLArgument(Boolean pArgument) {
		return (null != pArgument)
			?	((Boolean.TRUE.equals(pArgument)) ? "TRUE" : "FALSE")
			:	"NULL";
	}

	/**
	 * Récupère un argument de requête depuis un date 
	 * @param Date
	 * @return chaîne
	 */
	public static String rawSQLArgument(Date pArgument) {
		if (null != pArgument) {
			Calendar lCalendar = Calendar.getInstance();
			lCalendar.setTime(pArgument);
			return "'" + lCalendar.get(Calendar.YEAR) + "-" +
				(lCalendar.get(Calendar.MONTH) + 1) + "-" +
				lCalendar.get(Calendar.DAY_OF_MONTH) + "'";
		}
		return "NULL";
	}
	
	/**
	 * Transforme une date en argument pour une requête en base
	 * @param Date
	 * @return chaîne
	 */
	public static Date toDate(String pValue) {
		try {
			return (null != pValue)
				?	new Date(java.sql.Date.valueOf(pValue.substring(0, 10)).getTime())
				:	null;
		}
		catch(Exception pException) {
			return null;
		}
	}
	
	/**
	 * Transforme une châine (représentant un boolean) en booleen
	 * @param Date
	 * @return Booleen
	 */
	public static Boolean toBoolean(String pValue) {
		try {
			if (null != pValue) {
				return (0 == Integer.valueOf(pValue))
					?	Boolean.FALSE
					:	Boolean.TRUE;
			}
		}
		catch(Exception pException) {}
		return null;
	}
	
	/**
	 * Transforme une châine (représentant un long) en long
	 * @param Date
	 * @return Long
	 */
	public static Long toLong(String pValue) {
		try {
			return (null != pValue)
				?	Long.valueOf(pValue)
				:	null;
		}
		catch(Exception pException) {
			return null;
		}
	}
	
	/**
	 * Transforme une châine (représentant un entier) en entier
	 * @param Date
	 * @return Entier court
	 */
	public static Integer toInteger(String pValue) {
		try {
			return (null != pValue)
				?	Integer.valueOf(pValue)
				:	null;
		}
		catch(Exception pException) {
			return null;
		}
	}
	
	/**
	 * Transforme une châine (représentant un entier court) en entier court
	 * @param Date
	 * @return Entier court
	 */
	public static Short toShort(String pValue) {
		try {
			return (null != pValue)
				?	Short.valueOf(pValue)
				:	null;
		}
		catch(Exception pException) {
			return null;
		}
	}
	
	/**
	 * Transforme une date en une date de type SQL
	 * @param Date (type de base)
	 * @return Date de type SQL
	 */
	public static java.sql.Date sqlDate(java.util.Date pDate) {
		return (null != pDate)
			?	new java.sql.Date(pDate.getTime())
			:	null;
	}
}
