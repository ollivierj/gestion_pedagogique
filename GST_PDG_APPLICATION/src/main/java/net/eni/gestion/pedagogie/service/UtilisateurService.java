package net.eni.gestion.pedagogie.service;


import java.util.List;

import net.eni.gestion.pedagogie.commun.composant.erreur.ApplicationException;
import net.eni.gestion.pedagogie.commun.modele.Utilisateur;

/**
 * @author jollivier
 * Interface métier pour le module de gestion des comptes utilisateurs
 */
public interface UtilisateurService extends AService<Utilisateur, Integer> {

	public List<Utilisateur> getProfil(Integer profilId) throws ApplicationException;

	public Utilisateur authentifier(Utilisateur utilisateur) throws ApplicationException;
	public boolean checkConnection(Utilisateur utilisateur, boolean loginOnly ) throws ApplicationException;
}
