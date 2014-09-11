package net.eni.gestion.pedagogie.DAO;

import java.util.HashMap;

import net.eni.gestion.pedagogie.modele.Profil;

/**
 * @author jollivier
 * Description des requêtes d'accès (écriture, lecture) en base de données 
 * pour la gestion des profils d'utilisateur pour l'application
 */
public interface ProfilDao extends ADao<Profil, Integer> {

	public HashMap<String, String> getTitleMap() throws Exception;

}
