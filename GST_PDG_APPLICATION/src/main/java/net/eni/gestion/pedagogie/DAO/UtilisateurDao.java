package net.eni.gestion.pedagogie.DAO;


import java.util.List;

import net.eni.gestion.pedagogie.commun.composant.erreur.ApplicationException;
import net.eni.gestion.pedagogie.commun.modele.Utilisateur;

/**
 * @author jollivier
 * Description des requêtes d'accès (écriture, lecture) en base de données 
 * pour la gestion des utilisateurs de l'application
 */
public interface UtilisateurDao extends ADao<Utilisateur, Integer> {

	public List<Utilisateur> getFormateurs(String pSearchText) throws ApplicationException;
	public Integer checkConnection(String pLogin, String pMotdePasse, boolean loginOnly) throws ApplicationException;
	public boolean checkToken(String token) throws ApplicationException;
	public Utilisateur loginwithtoken(String token) throws ApplicationException;
	
}
