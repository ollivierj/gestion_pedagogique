package net.eni.gestion.pedagogie.DAO;

import java.util.HashMap;

import net.eni.gestion.pedagogie.modele.Module;

/**
 * @author jollivier
 * Description des requêtes d'accès (écriture, lecture) en base de données 
 * pour la gestion des modules
 */
public interface ModuleDao extends ADao<Module, Integer> {

	public HashMap<String, String> getTitleMap() throws Exception;

}
