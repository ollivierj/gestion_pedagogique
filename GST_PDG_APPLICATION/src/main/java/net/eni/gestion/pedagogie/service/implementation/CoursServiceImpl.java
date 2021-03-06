package net.eni.gestion.pedagogie.service.implementation;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import net.eni.gestion.pedagogie.DAO.CoursDao;
import net.eni.gestion.pedagogie.DAO.CoursStagiaireDao;
import net.eni.gestion.pedagogie.DAO.InstanceCoursDao;
import net.eni.gestion.pedagogie.DAO.ReservationSalleDao;
import net.eni.gestion.pedagogie.commun.composant.erreur.ApplicationException;
import net.eni.gestion.pedagogie.commun.composant.instancePlanning.InstancePlanning;
import net.eni.gestion.pedagogie.commun.composant.tuple.Pair;
import net.eni.gestion.pedagogie.commun.modele.Cours;
import net.eni.gestion.pedagogie.commun.modele.CoursStagiaire;
import net.eni.gestion.pedagogie.commun.modele.InstanceCours;
import net.eni.gestion.pedagogie.commun.modele.ReservationSalle;
import net.eni.gestion.pedagogie.service.CoursService;

import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * @author jollivier Classe d'implémentation pour le module de suivi des
 *         absences
 */
@Singleton
public class CoursServiceImpl extends AServiceImpl<Cours, UUID, CoursDao>
		implements CoursService {

	protected final CoursStagiaireDao coursStagiaireDao;
	protected final ReservationSalleDao reservationSalleDao;
	protected final InstanceCoursDao instanceCoursDao;

	/**
	 * Constructeur
	 * 
	 * @param DAO
	 *            absence
	 * @throws SQLException
	 */
	@Inject
	public CoursServiceImpl(CoursDao pCoursDao,
			CoursStagiaireDao coursStagiaireDao,
			ReservationSalleDao reservationSalleDao,
			InstanceCoursDao instanceCoursDao) throws SQLException {
		super(pCoursDao);
		this.coursStagiaireDao = coursStagiaireDao;
		this.reservationSalleDao = reservationSalleDao;
		this.instanceCoursDao = instanceCoursDao;
	}

	@Override
	public Cours getData(String pId) throws ApplicationException {
		Cours cours = super.chargerDetail(UUID.fromString(pId));
		cours.getCoursStagiaires();
		return cours;
	}

	@Override
	public Cours saveInstanceData(
			InstancePlanning<InstanceCours, CoursStagiaire> instances)
			throws ApplicationException {

		//Enregistrement des stagiaires liéss aux instances
		for (Pair<InstanceCours, List<CoursStagiaire>> instance : instances.getInstances()) {
			ReservationSalle reservationSalle = null;
			InstanceCours instanceCours = null;
			
			//Réservation de la salle
			reservationSalle = reservationSalleDao.addOrUpdate(instance.getFirst().getReservationSalle());
			
			instance.getFirst().setReservationSalle(reservationSalle);
			
			//Enregistrement de l'instance
			instanceCours = instanceCoursDao.addOrUpdate(instance.getFirst());
			
			//Enregistrement de stagiaires liés à l'instance

			for (CoursStagiaire instanceStagiaire : instance.getSecond()) {
				//Affectation de l'instance précédemment enregistrée pour récupérer son id.
				instanceStagiaire.setInstanceCours(instanceCours);
					coursStagiaireDao.addOrUpdate(instanceStagiaire);
			}
			
		}
		
		//Enregistrement des stagiaires non associés à une instance
		for (CoursStagiaire instanceStagiaire : instances.getInstancesStagiaires()) {
			coursStagiaireDao.addOrUpdate(instanceStagiaire);
		}
		
		//Suppression des instances
		for (InstanceCours instanceCours : instances.getInstancesToDelete()) {
			ReservationSalle reservationSalle = instanceCours.getReservationSalle();
			instanceCoursDao.supprimer(instanceCours.getId());
			reservationSalleDao.supprimer(reservationSalle.getId());
		}

		return null;

	}

}
