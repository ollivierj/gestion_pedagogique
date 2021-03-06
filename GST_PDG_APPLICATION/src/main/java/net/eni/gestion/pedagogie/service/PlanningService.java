package net.eni.gestion.pedagogie.service;

import java.util.List;

import net.eni.gestion.pedagogie.commun.composant.erreur.ApplicationException;
import net.eni.gestion.pedagogie.commun.modele.Planning;

public interface PlanningService extends AService<Planning, Long> {

	public List<Planning> getPlanningElement(String dateDebut, String dateFin) throws ApplicationException;
}
