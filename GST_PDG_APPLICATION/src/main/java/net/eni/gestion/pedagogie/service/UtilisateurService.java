package net.eni.gestion.pedagogie.service;

import net.eni.gestion.pedagogie.errorhandling.ApplicationException;
import net.eni.gestion.pedagogie.modele.Utilisateur;

/**
 * @author jollivier
 * Interface métier pour le module de gestion des comptes utilisateurs
 */
public interface UtilisateurService extends AService<Utilisateur, Integer> {
	public Utilisateur authentifier(Utilisateur utilisateur) throws ApplicationException;
	public boolean checkConnection(Utilisateur utilisateur, boolean loginOnly ) throws ApplicationException;
}
