package net.eni.gestion.pedagogie.service;

import net.eni.gestion.pedagogie.commun.composant.erreur.ApplicationException;
import net.eni.gestion.pedagogie.commun.modele.Utilisateur;

/**
 * @author jollivier
 * Interface métier pour le module de gestion des comptes utilisateurs
 */
public interface UtilisateurService extends AService<Utilisateur, Integer> {
	public Utilisateur authentifier(String pLogin, String pMotDePasse) throws ApplicationException;
	public  boolean checkConnection(String pLogin, String pMotDePasse, boolean loginOnly) throws ApplicationException;
	public boolean checkToken(String pToken) throws ApplicationException;
	public Utilisateur authentifierAvecToken(String token)throws ApplicationException;
}
