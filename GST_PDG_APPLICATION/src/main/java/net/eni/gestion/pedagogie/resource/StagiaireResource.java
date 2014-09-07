package net.eni.gestion.pedagogie.resource;

import net.eni.gestion.pedagogie.commun.composant.GenericException;
import net.eni.gestion.pedagogie.modele.Stagiaire;

/**
 * @author jollivier
 * Interface service pour le module de consultation des stagiaires
 */
public interface StagiaireResource extends AResource<Stagiaire, Integer> {
	
	public String getJsonSchemaEchanges() throws GenericException;
	public String getJsonSchemaAvis() throws GenericException;
}
