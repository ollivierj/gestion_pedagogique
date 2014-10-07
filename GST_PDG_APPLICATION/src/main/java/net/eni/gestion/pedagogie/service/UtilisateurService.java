package net.eni.gestion.pedagogie.service;

import net.eni.gestion.pedagogie.commun.composant.GenericException;
import net.eni.gestion.pedagogie.modele.Utilisateur;

/**
 * @author jollivier
 * Interface métier pour le module de gestion des comptes utilisateurs
 */
public interface UtilisateurService extends AService<Utilisateur, Integer> {

	Utilisateur checkLogin(Utilisateur utilisateur) throws GenericException;


}
