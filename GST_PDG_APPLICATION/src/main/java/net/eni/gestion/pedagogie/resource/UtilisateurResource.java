package net.eni.gestion.pedagogie.resource;


import java.util.List;

import net.eni.gestion.pedagogie.commun.composant.erreur.ApplicationException;
import net.eni.gestion.pedagogie.commun.modele.Utilisateur;


/**
 * @author jollivier
 * Interface service pour le module de gestion des comptes utilisateurs
 */
public interface UtilisateurResource extends AResource<Utilisateur, Integer> {

	public List<Utilisateur> getFormateurs(String pSearchText) throws ApplicationException;
}
