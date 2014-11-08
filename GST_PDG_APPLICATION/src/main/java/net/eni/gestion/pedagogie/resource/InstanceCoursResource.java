package net.eni.gestion.pedagogie.resource;

import java.util.List;

import net.eni.gestion.pedagogie.commun.composant.erreur.ApplicationException;
import net.eni.gestion.pedagogie.commun.modele.Cours;
import net.eni.gestion.pedagogie.commun.modele.InstanceCours;

/**
 * @author jollivier
 * Interface service pour le module de suivi des evaluations
 */
public interface InstanceCoursResource extends AResource<InstanceCours, Integer> {

	public List<InstanceCours> getInstances(Cours evaluation) throws ApplicationException;
	
}
