package net.eni.gestion.pedagogie.resource;

import java.util.List;

import net.eni.gestion.pedagogie.commun.composant.erreur.ApplicationException;
import net.eni.gestion.pedagogie.commun.modele.InstanceSessionValidation;
import net.eni.gestion.pedagogie.commun.modele.SessionValidation;

/**
 * @author jollivier
 * Interface service pour le module de suivi des evaluations
 */
public interface InstanceSessionValidationResource extends AResource<InstanceSessionValidation, Integer> {

	public List<InstanceSessionValidation> getInstances(SessionValidation sessionValidation) throws ApplicationException;
	
}
