package net.eni.gestion.pedagogie.resource;

import java.util.List;
import java.util.Map;

import net.eni.gestion.pedagogie.commun.composant.GenericException;
import net.eni.gestion.pedagogie.modele.InstanceSessionValidation;
import net.eni.gestion.pedagogie.modele.SessionValidation;
import net.eni.gestion.pedagogie.modele.SessionValidationStagiaire;
import net.eni.gestion.pedagogie.modele.StagiairePromotion;

/**
 * @author jollivier
 * Interface service pour le module de gestion des sessions de validation
 */
public interface SessionValidationResource extends AResource<SessionValidation, Integer> {
	
	public SessionValidation getInstance(Integer id) throws GenericException;
	
	public void saveInstance(
			Map<InstanceSessionValidation, List<StagiairePromotion>> instancesSessions, 
			List<SessionValidationStagiaire> stagiaireLibres) throws GenericException;

}
