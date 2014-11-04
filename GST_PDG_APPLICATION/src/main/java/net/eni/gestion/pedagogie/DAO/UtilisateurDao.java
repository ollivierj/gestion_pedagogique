package net.eni.gestion.pedagogie.DAO;


import java.util.List;
import net.eni.gestion.pedagogie.commun.modele.Utilisateur;

/**
 * @author jollivier
 * Description des requêtes d'accès (écriture, lecture) en base de données 
 * pour la gestion des utilisateurs de l'application
 */
public interface UtilisateurDao extends ADao<Utilisateur, Integer> {

	public List<Utilisateur> getByProfil(Integer profilId) throws Exception;

	public String checkConnection(Utilisateur utilisateur, boolean loginOnly ) throws Exception;
}
