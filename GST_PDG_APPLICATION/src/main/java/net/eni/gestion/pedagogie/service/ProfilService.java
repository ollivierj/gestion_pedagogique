package net.eni.gestion.pedagogie.service;

import java.util.HashMap;

import net.eni.gestion.pedagogie.commun.composant.erreur.ApplicationException;
import net.eni.gestion.pedagogie.modele.Profil;

/**
 * @author jollivier
 * Interface métier pour le module de gestions des profils et de leurs droits associés
 */
public interface ProfilService extends AService<Profil, Integer> {

	HashMap<String, String> getTitleMap() throws ApplicationException;

}
