package net.eni.gestion.pedagogie.resource;

import java.util.List;
import java.util.Map;

import net.eni.gestion.pedagogie.commun.composant.erreur.ApplicationException;
import net.eni.gestion.pedagogie.commun.modele.InstanceSessionValidation;
import net.eni.gestion.pedagogie.commun.modele.SessionValidationStagiaire;
import net.eni.gestion.pedagogie.commun.modele.StagiairePromotion;
import net.eni.gestion.pedagogie.commun.modele.SessionValidation;

/**
 * @author jollivier
 * Interface service pour le module de gestion des sessions de validation
 */
public interface SessionValidationResource extends AResource<SessionValidation, Integer> {
	
	public SessionValidation getInstance(Integer id) throws ApplicationException;
	
	public void saveInstance(
			Map<InstanceSessionValidation, List<StagiairePromotion>> instancesSessions, 
			List<SessionValidationStagiaire> stagiaireLibres) throws ApplicationException;

}
