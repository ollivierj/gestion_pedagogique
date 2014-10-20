package net.eni.gestion.pedagogie.commun.composant;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import net.eni.gestion.pedagogie.commun.outil.ObjectHelper;
import net.eni.gestion.pedagogie.commun.outil.StringHelper;

/**
 * @author jollivier
 * Gestiondu fichier de propriété
 */
public class PropertyStore {
    
    /** Fichier de lecture/sauvegarde des propriétés */
    private File mPropertyFile = null;
    
    /** Propriétés */
    private Properties mProperties = new Properties();
    
    /**
     * Setter pour le fichier de propriétés
     * @param Fichier de propriétés
     */
    public PropertyStore(File pPropertyFile) {
        setPropertyFile(pPropertyFile);
    }
    
    /**
     * Setter pour le fichier de propriétés
     * @param Nom du fichier de propriétés
     */
    public PropertyStore(String pFileName) {
        setPropertyFile(new File(StringHelper.nullIsEmpty(pFileName).replace("%20", " ")));
    }
    
    /**
     * Setter pour le fichier de propriétés
     * @param Flux du fichier de propriétés
     */
    public PropertyStore(InputStream pStream) {
        loadProperties(pStream);
    }
    
    /**
     * Getter pour la récupération du fichier de propriétés
     * @return Fichier de proprités
     */
    public File getPropertyFile() {
        return mPropertyFile;
    }
    
    /**
     * Setter de base pour le fichier de propriétés
     * @param Fichier de propriété
     */
    public void setPropertyFile(File pPropertyFile) {
        if (!ObjectHelper.equals(mPropertyFile, pPropertyFile)) {
            mPropertyFile = pPropertyFile;
            loadProperties();
        }
    }
    
    /**
     * Recherche d'une propriété parmi celles présentes dans le fichier de propriétés
     * @param Nom de la propriété
     * @return Propriété présente dans le fichier
     */
    public boolean containsProperty(String pPropertyName) {
        return  null != mProperties
            &&  mProperties.containsKey(pPropertyName);
    }
    
    /**
     * Récupère l'ensemble des propriétés présentes dans le fichier
     * @return Proprités du fichier
     */
    public Properties getProperties() {
    	return mProperties;
    }
    
    /**
     * Récupére une valeur d'une propriété
     * @param Nom de la propriété
     * @return Valeur de la propriété
     */
    public String getProperty(String pPropertyName) {
        return (null != mProperties)
            ?   mProperties.getProperty(pPropertyName)
            :   null;
    }
    
    /**
     * Récupére une valeur d'une propriété et applique une valeur par
     * défaut si celle-ci est absente
     * @param Nom de la proprité
     * @param Valeur de la propriété par défaut
     * @return Valeur de la propriété
     */
    public String getPropertyOrDefault(String pPropertyName, String pDefaultValue) {
        String lValue = getProperty(pPropertyName); 
        return (StringHelper.isSet(lValue))
            ?   lValue
            :   pDefaultValue;
    }
    
    /**
     * Crée ou modifie une propriété
     * @param Nom de la propriété
     * @param Valeur de la propriété
     * @return Proprité sauveagrdée
     */
    public boolean setProperty(String pName, String pValue) {
        if (    null != mPropertyFile
            &&  null != pName) {
            if (null != pValue) {
                if (    !mProperties.containsKey(pName)
                    ||  !ObjectHelper.equals(pValue, mProperties.get(pName))) {
                    mProperties.put(pName, pValue);
                    saveProperties();
                    return true;
                }
            }
            else {
                if (mProperties.containsKey(pName)) {
                    mProperties.remove(pName);
                    saveProperties();
                    return true;
                }              
            }
        }
        return false;
    }
    
    /**
     * Charge les propriétés depuis un fichier
     * @return Proprités chargées
     */
    private boolean loadProperties() {
        if (null != mPropertyFile) {
            try {
                return loadProperties(new FileInputStream(mPropertyFile));
            } catch (Exception pException) {
                // Absence du fichier de propriétés
            }
        }
        return false;
    }
    
    /**
     * Charge les propriétés depuis un flux de fichier
     * @return Proprités chargées
     */
    private boolean loadProperties(InputStream pStream) {
        if (null != pStream) {
            try {
                InputStreamReader lReader = new InputStreamReader(pStream);
                mProperties.load(lReader);
                lReader.close();
                return true;
            } catch (Exception pException) {
                // Absence du fichier de propriétés
            }
        }
        return false;
    }
    
    /**
     * Sauvegarde les propriétés dans le fichier
     * @return Proprités sauvegardées
     */
    private boolean saveProperties() {
        if (null != mPropertyFile) {
            try {
                FileWriter lWriter = new FileWriter(mPropertyFile);
                mProperties.store(lWriter, null);
                lWriter.close();
                return true;
            } catch (Exception pException) {
                // Absence du fichier de propriétés
            }
        }
        return false;        
    }
}
