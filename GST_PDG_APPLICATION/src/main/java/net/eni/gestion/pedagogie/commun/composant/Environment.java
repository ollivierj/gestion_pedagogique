package net.eni.gestion.pedagogie.commun.composant;

import java.io.File;
import net.TopLevelDomain;

/**
 * @author jollivier
 * Gestion de la configuration applicative
 */
public class Environment {
	
	/**
	 * Obtention du chemin d'accès de l'archive Java (racine des codes sources)
	 * @return Chemin d'accès de l'archive Java (racine des codes sources)
	 */
	public static String getWebInfLocation() {
		return TopLevelDomain.class.getResource("").getPath()
			+ ".." + File.separatorChar + ".." + File.separatorChar;
	}
}
