package net.eni.gestion.pedagogie.DAO;

import java.util.List;

import net.eni.gestion.pedagogie.modele.Droit;
import net.eni.gestion.pedagogie.modele.Utilisateur;

/**
 * @author jollivier
 * Description des requêtes d'accès (écriture, lecture) en base de données 
 * pour la gestion des droits
 */
public interface DroitDao extends ADao<Droit, Integer> {

	
	public List<String[]> chargerParIdProfil(Integer id) throws Exception;
}
