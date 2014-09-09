package net.eni.gestion.pedagogie.DAO;

import java.util.HashMap;

import net.eni.gestion.pedagogie.modele.Fonction;

/**
 * @author jollivier
 * Description des requêtes d'accès (écriture, lecture) en base de données 
 * pour la gestion des fonctions
 */
public interface FonctionDao extends ADao<Fonction, String> {

	public HashMap<String, String> getTitleMap() throws Exception;

}
