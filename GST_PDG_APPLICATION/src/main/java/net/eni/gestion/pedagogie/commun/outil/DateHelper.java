package net.eni.gestion.pedagogie.commun.outil;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;

import net.eni.gestion.pedagogie.commun.composant.tuple.Pair;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

/**
 * @author jollivier
 * Outils de gestion des dates
 */
public class DateHelper {
	
	public final static int JANVIER		= 0;
	public final static int FEVRIER		= 1;
	public final static int MARS		= 2;
	public final static int AVRIL		= 3;
	public final static int MAI			= 4;
	public final static int JUIN		= 5;
	public final static int JUILLET		= 6;
	public final static int AOUT		= 7;
	public final static int SEPTEMBRE	= 8;
	public final static int OCTOBRE		= 9;
	public final static int NOVEMBRE	= 10;
	public final static int DECEMBRE	= 11;

	/**
	 * Obtention d'une nouvelle date (sans horodatage)
	 * @param pJour Jour du mois
	 * @param pMois Mois de l'année (0=Janvier/11=Décembre)
	 * @param pAnnee Année
	 * @return Nouvelle Date (sans horodatage)
	 */
	public static Date newDate(int pJour, int pMois, int pAnnee) {
		return new GregorianCalendar(pAnnee, pMois, pJour).getTime();
	}

	/**
	 * Obtention d'une nouvelle date (sans horodatage)
	 * @param pDate Date à traiter
	 * @return Nouvelle Date (sans horodatage)
	 */
	public static Date newDate(Date pDate) {
		if (null != pDate) {
			GregorianCalendar lCalendar = new GregorianCalendar();
			lCalendar.setTime(pDate);
			lCalendar.set(GregorianCalendar.HOUR_OF_DAY, 0);
			lCalendar.set(GregorianCalendar.MINUTE, 0);
			lCalendar.set(GregorianCalendar.SECOND, 0);
			lCalendar.set(GregorianCalendar.MILLISECOND, 0);
			return lCalendar.getTime();
		}
		return null;
	}

	/**
	 * Obtention de la date courante (sans horodatage)
	 * @return Date courante (sans horodatage)
	 */
	public static Date currentDate() {
		return newDate(new GregorianCalendar().getTime());
	}
	
	/**
	 * Obtention d'une nouvelle date
	 * @param pTimeInMillis Date attendue exprimée en millisecondes
	 * @return Nouvelle date
	 */
	public static Date newDate(long pTimeInMillis) {
		/*
		GregorianCalendar lCalendar = new GregorianCalendar();
		lCalendar.setTimeInMillis(pTimeInMillis);
		return lCalendar.getTime();
		*/
		return new Date(pTimeInMillis);
	}
	
	/** Obtention de la date du jour suivant à l'aide d'une date
	 * @param Date
	 * @return Date + 1J
	 */
	public static Date jourSuivant(Date pDate) {
		if (null != pDate) {
			GregorianCalendar lCalendar = new GregorianCalendar();
			lCalendar.setTime(pDate);
			lCalendar.add(Calendar.DAY_OF_YEAR, 1);
			return lCalendar.getTime();
		}
		return null;
	}
	
	/** Obtention de la date du jour suivant en millisecondes à l'aide d'une date
	 * @param Date (milliseondes)
	 * @return Date + 1J (milliseondes)
	 */
	public static long jourSuivant(long pDateInMillis) {
		GregorianCalendar lCalendar = new GregorianCalendar();
		lCalendar.setTimeInMillis(pDateInMillis);
		lCalendar.add(Calendar.DAY_OF_YEAR, 1);
		return lCalendar.getTimeInMillis();
	}
	
	/** Obtention de la date du jour précédent à l'aide d'une date
	 * @param Date
	 * @return Date - 1J
	 */
	public static Date jourPrecedent(Date pDate) {
		if (null != pDate) {
			GregorianCalendar lCalendar = new GregorianCalendar();
			lCalendar.setTime(pDate);
			lCalendar.add(Calendar.DAY_OF_YEAR, -1);
			return lCalendar.getTime();
		}
		return null;
	}
	
	/** Obtention de la date du jour précédent en millisecondes à l'aide d'une date
	 * @param Date (milliseondes)
	 * @return Date - 1J (milliseondes)
	 */
	public static long jourPrecedent(long pDateInMillis) {
		GregorianCalendar lCalendar = new GregorianCalendar();
		lCalendar.setTimeInMillis(pDateInMillis);
		lCalendar.add(Calendar.DAY_OF_YEAR, -1);
		return lCalendar.getTimeInMillis();
	}
	
	/**
	 * Recherche les dates min et max dans une collection de dates
	 * @param Collection de dates
	 * @return Date min et date max
	 */
	public static Pair<Date, Date> getDatesLimites(Collection<Date> pDates) {
		boolean lDateTrouvee = false;
		long lDateMin = Long.MAX_VALUE;
		long lDateMax = 0;
		if (	null != pDates
			&&	0 < pDates.size()) {
			for (Date lDate:pDates) {
				if (null != lDate) {
					lDateTrouvee = true;
					lDateMin = Math.min(lDateMin, lDate.getTime());
					lDateMax = Math.max(lDateMax, lDate.getTime());
				}
			}
		}
		return (lDateTrouvee)
			?	new Pair<Date, Date>(newDate(lDateMin), newDate(lDateMax))
			:	null;
	}
	
	public static String stringifyDate(Date pDate, String pFormat){
		return (null != pDate && null != pFormat)? DateFormatUtils.format(pDate, pFormat) : null;
	}
	
	public static Date datifyString(String pDate, String pFormat){
		try {
			return (null != pDate) ? DateUtils.parseDate(pDate, pFormat) : null;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
