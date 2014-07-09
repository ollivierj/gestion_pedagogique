package net.eni.gestion.pedagogie.commun.outil;

/**
 * @author jollivier
 * Classe outils sur les instances de "String"
 */
public class StringHelper {
    
    /**
     * Retourne une chaine vide si le paramètre est nul, sinon retourne le paramètre au formart chaîne de caractères
     * @param pObject Chaine à évaluer
     * @return Chaine adaptée
     */
    public static String nullIsEmpty(Object pObject) {
    	String lString = toString(pObject);
        return (null != lString)
            ?   lString
            :   "";           
    }
    
    public static int countWordsInPhrase(String pPhrase){
    	String[] lArrayWords = getWordsArray(pPhrase);
		return (null!=lArrayWords) ? lArrayWords.length : 0;	
    }
    
    public static String[] getWordsArray(String pPhrase){
    	String lTrimmedSearchText = pPhrase.trim();
		return lTrimmedSearchText.split("\\s+");	
    }
    
    /**
     * Retourne null si le paramètre est une chaine vide, sinon retourne le paramètre au format chaîne de caractères
     * @param pString Chaine à évaluer
     * @return Chaine adaptée
     */
    public static String emptyIsNull(Object pObject) {
    	String lString = toString(pObject);
        return (null != lString
            &&	lString.isEmpty())
	        ?   null
	        :   lString;
    }
    
    /**
     * Evaluation de valorisation d'une la chaîne de caractères (i.e. non-nulle et non-vide)
     * @param pString Chaine de caractère à évaluer
     * @return Indicateur de valorisation de la chaîne de caractères
     */
    public static boolean isSet(String pString) {
        return (null != pString
            &&  !pString.isEmpty());
    }
    
    /**
     * Evaluation de valorisation d'une la chaîne de caractères (i.e. non-nulle et non-vide)
     * @param pString Chaine de caractère à évaluer
     * @param pAllowNull Indicateur d'acceptation de la chaine vide
     * @return Indicateur de valorisation de la chaîne de caractères
     */
    public static boolean isSet(String pString, boolean pAllowNull) {
        return (    null != pString
                &&  (   pAllowNull
                     || !pString.isEmpty()));
    }

    /**
     * Evaluation de valorisation d'un tableau de chaînes de caractères (i.e. non-nulles et non-vides)
     * @param pString Tableau de chaînes de caractères à évaluer
     * @param pAllowNull Indicateur d'acceptation de la chaine vide
     * @return Indicateur de valorisation des chaînes de caractères
     */
    public static boolean isSet(String[] pStringArray, boolean pAllowNull) {
        if (null != pStringArray) {
            for (String lString : pStringArray) {
                if (!isSet(lString, pAllowNull)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    
    /**
     * Renvoie la réprésentation de l'objet sous forme de chaine de caractère ou null si l'objjet est null
     * @param pObject Objet à représenter sous forme de chaine de caractères
     * @return Chaine de caractère représentative de l'objet passé en paramètre
     */
    public static String toString(Object pObject) {
        return (null != pObject)
            ?   pObject.toString()
            :   null;
    }
    
    /**
     * Méthode de concaténation de deux chaînes de caractères
     * @param pLeft Chaîne de caractère initiale (gauche de la concaténation)
     * @param pRight Chaîne de caractère à concaténer (droite de la concaténation)
     * @return Chaîne de caractères cancaténée
     */
    public static String append(String pLeft, String pRight) {
        return append(pLeft, pRight, null);
    }
    
    /**
     * Méthode de concaténation de deux chaînes de caractères avec séparateur
     * @param pLeft Chaîne de caractère initiale (gauche de la concaténation)
     * @param pRight Chaîne de caractère à concaténer (droite de la concaténation)
     * @param pSeparator Séparateur des chaînes à concaténer
     * @return Chaîne de caractères concaténée
     */
    public static String append(String pLeft, String pRight, String pSeparator) {
        String lString = pLeft;
        if (StringHelper.isSet(pLeft)) {
            if (StringHelper.isSet(pRight)) {
                if (StringHelper.isSet(pSeparator)) {
                    lString += pSeparator;
                }
                lString += pRight;
            }
        }
        else {
            lString = pRight;
        }
        return lString;
    }

    /**
     * Méthode d'obtention d'une chaine de caractères tronquée
     * @param pValue Chaine de caractères à traiter
     * @param pSize Nombre maximum de caractères attendus
     * @return Chaine de caractères tronquée
     */
    public static String left(String pValue, int pSize) {
        return (    null != pValue
                &&  0 <= pSize)
            ?   pValue.substring(0, Math.min(pValue.length(), pSize))
            :   null;
    }
}
