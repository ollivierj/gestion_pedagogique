package net.eni.gestion.pedagogie.service.implementation;

import java.util.ArrayList;
import java.util.List;

import net.eni.gestion.pedagogie.DAO.PlanningDao;
import net.eni.gestion.pedagogie.commun.composant.GenericException;
import net.eni.gestion.pedagogie.modele.Planning;
import net.eni.gestion.pedagogie.service.PlanningService;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class PlanningServiceImpl extends AServiceImpl<Planning, Long, PlanningDao> implements PlanningService {


	
	@Inject
	public PlanningServiceImpl(PlanningDao planningDao) {
		super(planningDao);
	}

	@Override
	public List<Planning> getPlanningElement(String dateDebut, String dateFin) throws GenericException {
		List<Planning> planningElements = new ArrayList<Planning>();
		try {
			planningElements = dao.charger(dateDebut, dateFin);
		} catch (Exception e) {
			throw new GenericException("Erreur lors de la recherche des éléments du planning");
		}
		return planningElements;
	}
}