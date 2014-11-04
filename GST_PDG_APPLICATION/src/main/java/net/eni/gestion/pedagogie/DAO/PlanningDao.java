package net.eni.gestion.pedagogie.DAO;

import java.util.List;

import net.eni.gestion.pedagogie.commun.modele.Planning;

public interface PlanningDao extends ADao<Planning, Long> {

	public List<Planning> charger(String dateDebut, String dateFin) throws Exception;
	
}
