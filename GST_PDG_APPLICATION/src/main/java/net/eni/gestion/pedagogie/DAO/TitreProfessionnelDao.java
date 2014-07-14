package net.eni.gestion.pedagogie.DAO;

import java.util.HashMap;

import net.eni.gestion.pedagogie.modele.TitreProfessionnel;

/**
 * @author jollivier
 * Description des requêtes d'accès (écriture, lecture) en base de données 
 * pour la gestion des titres profesionnels
 */
public interface TitreProfessionnelDao extends ADao<TitreProfessionnel, Integer> {

	public HashMap<Integer, String> getTitleMap() throws Exception;
}
