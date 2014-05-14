package net.eni.gestion.pedagogie.commun.outil;

/**
 * @author jollivier
 * Manipulation de classes
 */
public class ObjectHelper {
    
    /**
     * M�thode de comparaison entre deux objets
     * @param pFirst Premier objet à comparer
     * @param pSecond Second objet à comparer
     * @return Indicateur d'identit� des deux objets
     */
    public static boolean equals(Object pFirst, Object pSecond) {
        return (null == pFirst) ? null == pSecond : pFirst.equals(pSecond);
    }
    
    /**
     * Calcule une clé de hashage
     * @param Objet
     * @return clé de hachage
     */
    public static int hashCode(Object pObject) {
        return (null != pObject) ? pObject.hashCode() : 0;
    }
    
    /**
     * Obtention de la repr�sentation d'un l'objet sous forme de chaîne de caractères
     * @param pObject Objet �tudi�
     * @param pNullIsEmpty Indicateur de r�pr�sentation sous forme de chaine vide si l'objet est null
     * @return repr�sentation de l'objet sous forme de cha�ne de caract�res
     */
    public static String toString(Object pObject, boolean pNullIsEmpty) {
        if (null == pObject) {
            return (pNullIsEmpty)
                ?    ""
                :    null;
        }
        else {
            return pObject.toString();
        }
    }
    
    /**
     * Obtention de la représentation d'un l'objet sous forme de chaîne de caractères
     * @param pObject Objet �tudi�
     * @return repr�sentation de l'objet sous forme de cha�ne de caractères
     */
    public static String toString(Object pObject) {
        return toString(pObject, false);
    }
}
