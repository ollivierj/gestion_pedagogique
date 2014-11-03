package net.eni.gestion.pedagogie.resource;

import java.util.List;

import net.eni.gestion.pedagogie.commun.composant.GenericException;
import net.eni.gestion.pedagogie.modele.Utilisateur;

/**
 * @author jollivier
 * Interface service pour le module de gestion des comptes utilisateurs
 */
public interface UtilisateurResource extends AResource<Utilisateur, Integer> {

	public List<Utilisateur> getProfil(Integer profilId) throws GenericException;
}
