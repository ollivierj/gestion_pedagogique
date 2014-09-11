package net.eni.gestion.pedagogie.resource;

import java.util.HashMap;

import net.eni.gestion.pedagogie.commun.composant.GenericException;
import net.eni.gestion.pedagogie.modele.Profil;

/**
 * @author jollivier
 * Interface service pour le module de gestion des profils et des droits associés
 */
public interface ProfilResource extends AResource<Profil, Integer> {

	HashMap<String, String> getTitleMap() throws GenericException;
	
}
