package net.eni.gestion.pedagogie.configuration;

import net.eni.gestion.pedagogie.DAO.AbsenceDao;
import net.eni.gestion.pedagogie.DAO.AvisDao;
import net.eni.gestion.pedagogie.DAO.CoursDao;
import net.eni.gestion.pedagogie.DAO.DroitDao;
import net.eni.gestion.pedagogie.DAO.DroitProfilDao;
import net.eni.gestion.pedagogie.DAO.EchangeDao;
import net.eni.gestion.pedagogie.DAO.EvaluationDao;
import net.eni.gestion.pedagogie.DAO.FonctionDao;
import net.eni.gestion.pedagogie.DAO.FormationDao;
import net.eni.gestion.pedagogie.DAO.HomologationDao;
import net.eni.gestion.pedagogie.DAO.InstanceCoursDao;
import net.eni.gestion.pedagogie.DAO.InstanceCoursStagiaireDao;
import net.eni.gestion.pedagogie.DAO.InstanceEvaluationDao;
import net.eni.gestion.pedagogie.DAO.InstanceEvaluationStagiaireDao;
import net.eni.gestion.pedagogie.DAO.InstanceSessionValidationDao;
import net.eni.gestion.pedagogie.DAO.InstanceSessionValidationStagiaireDao;
import net.eni.gestion.pedagogie.DAO.JuryDao;
import net.eni.gestion.pedagogie.DAO.ModuleDao;
import net.eni.gestion.pedagogie.DAO.ModuleParUniteDao;
import net.eni.gestion.pedagogie.DAO.ParametreDao;
import net.eni.gestion.pedagogie.DAO.PlanningIndividuelDetailDao;
import net.eni.gestion.pedagogie.DAO.PlanningIndividuelFormationDao;
import net.eni.gestion.pedagogie.DAO.ProfessionnelHomologueDao;
import net.eni.gestion.pedagogie.DAO.ProfilDao;
import net.eni.gestion.pedagogie.DAO.PromotionDao;
import net.eni.gestion.pedagogie.DAO.ReservationSalleDao;
import net.eni.gestion.pedagogie.DAO.SalleDao;
import net.eni.gestion.pedagogie.DAO.SessionValidationDao;
import net.eni.gestion.pedagogie.DAO.StagiaireDao;
import net.eni.gestion.pedagogie.DAO.TitreProfessionnelDao;
import net.eni.gestion.pedagogie.DAO.UniteFormationDao;
import net.eni.gestion.pedagogie.DAO.UniteParFormationDao;
import net.eni.gestion.pedagogie.DAO.UtilisateurDao;
import net.eni.gestion.pedagogie.DAO.implementation.AbsenceDaoImpl;
import net.eni.gestion.pedagogie.DAO.implementation.AvisDaoImpl;
import net.eni.gestion.pedagogie.DAO.implementation.CoursDaoImpl;
import net.eni.gestion.pedagogie.DAO.implementation.DroitDaoImpl;
import net.eni.gestion.pedagogie.DAO.implementation.DroitProfilDaoImpl;
import net.eni.gestion.pedagogie.DAO.implementation.EchangeDaoImpl;
import net.eni.gestion.pedagogie.DAO.implementation.EvaluationDaoImpl;
import net.eni.gestion.pedagogie.DAO.implementation.FonctionDaoImpl;
import net.eni.gestion.pedagogie.DAO.implementation.FormationDaoImpl;
import net.eni.gestion.pedagogie.DAO.implementation.HomologationDaoImpl;
import net.eni.gestion.pedagogie.DAO.implementation.InstanceCoursDaoImpl;
import net.eni.gestion.pedagogie.DAO.implementation.InstanceCoursStagiaireDaoImpl;
import net.eni.gestion.pedagogie.DAO.implementation.InstanceEvaluationDaoImpl;
import net.eni.gestion.pedagogie.DAO.implementation.InstanceEvaluationStagiaireDaoImpl;
import net.eni.gestion.pedagogie.DAO.implementation.InstanceSessionValidationDaoImpl;
import net.eni.gestion.pedagogie.DAO.implementation.InstanceSessionValidationStagiaireDaoImpl;
import net.eni.gestion.pedagogie.DAO.implementation.JuryDaoImpl;
import net.eni.gestion.pedagogie.DAO.implementation.ModuleDaoImpl;
import net.eni.gestion.pedagogie.DAO.implementation.ModuleParUniteDaoImpl;
import net.eni.gestion.pedagogie.DAO.implementation.ParametreDaoImpl;
import net.eni.gestion.pedagogie.DAO.implementation.PlanningIndividuelDetailDaoImpl;
import net.eni.gestion.pedagogie.DAO.implementation.PlanningIndividuelFormationDaoImpl;
import net.eni.gestion.pedagogie.DAO.implementation.ProfessionnelHomologueDaoImpl;
import net.eni.gestion.pedagogie.DAO.implementation.ProfilDaoImpl;
import net.eni.gestion.pedagogie.DAO.implementation.PromotionDaoImpl;
import net.eni.gestion.pedagogie.DAO.implementation.ReservationSalleDaoImpl;
import net.eni.gestion.pedagogie.DAO.implementation.SalleDaoImpl;
import net.eni.gestion.pedagogie.DAO.implementation.SessionValidationDaoImpl;
import net.eni.gestion.pedagogie.DAO.implementation.StagiaireDaoImpl;
import net.eni.gestion.pedagogie.DAO.implementation.TitreProfessionnelDaoImpl;
import net.eni.gestion.pedagogie.DAO.implementation.UniteFormationDaoImpl;
import net.eni.gestion.pedagogie.DAO.implementation.UniteParFormationDaoImpl;
import net.eni.gestion.pedagogie.DAO.implementation.UtilisateurDaoImpl;
import net.eni.gestion.pedagogie.service.AbsenceService;
import net.eni.gestion.pedagogie.service.AvisService;
import net.eni.gestion.pedagogie.service.CoursService;
import net.eni.gestion.pedagogie.service.EchangeService;
import net.eni.gestion.pedagogie.service.EvaluationService;
import net.eni.gestion.pedagogie.service.ModuleService;
import net.eni.gestion.pedagogie.service.ParametreService;
import net.eni.gestion.pedagogie.service.ProfessionnelHomologueService;
import net.eni.gestion.pedagogie.service.ProfilService;
import net.eni.gestion.pedagogie.service.ReservationSalleService;
import net.eni.gestion.pedagogie.service.SessionValidationService;
import net.eni.gestion.pedagogie.service.StagiaireService;
import net.eni.gestion.pedagogie.service.TitreProfessionnelService;
import net.eni.gestion.pedagogie.service.UtilisateurService;
import net.eni.gestion.pedagogie.service.implementation.AbsenceServiceImpl;
import net.eni.gestion.pedagogie.service.implementation.AvisServiceImpl;
import net.eni.gestion.pedagogie.service.implementation.CoursServiceImpl;
import net.eni.gestion.pedagogie.service.implementation.EchangeServiceImpl;
import net.eni.gestion.pedagogie.service.implementation.EvaluationServiceImpl;
import net.eni.gestion.pedagogie.service.implementation.ModuleServiceImpl;
import net.eni.gestion.pedagogie.service.implementation.ParametreServiceImpl;
import net.eni.gestion.pedagogie.service.implementation.ProfessionnelHomologueServiceImpl;
import net.eni.gestion.pedagogie.service.implementation.ProfilServiceImpl;
import net.eni.gestion.pedagogie.service.implementation.ReservationSalleServiceImpl;
import net.eni.gestion.pedagogie.service.implementation.SessionValidationServiceImpl;
import net.eni.gestion.pedagogie.service.implementation.StagiaireServiceImpl;
import net.eni.gestion.pedagogie.service.implementation.TitreProfessionnelServiceImpl;
import net.eni.gestion.pedagogie.service.implementation.UtilisateurServiceImpl;

import com.google.inject.AbstractModule;

/**
 * @author jollivier
 * Gestion des injections de dépendance pour l'application
 */
public class ModuleConfiguration extends AbstractModule {
    @Override
    protected void configure() {
    	// Binding des daos
    	bind(AbsenceDao.class).to(AbsenceDaoImpl.class);
    	bind(AvisDao.class).to(AvisDaoImpl.class);
    	bind(CoursDao.class).to(CoursDaoImpl.class);
    	bind(DroitDao.class).to(DroitDaoImpl.class);
    	bind(DroitProfilDao.class).to(DroitProfilDaoImpl.class);
    	bind(EchangeDao.class).to(EchangeDaoImpl.class);
    	bind(EvaluationDao.class).to(EvaluationDaoImpl.class);
    	bind(FonctionDao.class).to(FonctionDaoImpl.class);
    	bind(FormationDao.class).to(FormationDaoImpl.class);
    	bind(HomologationDao.class).to(HomologationDaoImpl.class);
    	bind(InstanceCoursDao.class).to(InstanceCoursDaoImpl.class);
    	bind(InstanceCoursStagiaireDao.class).to(InstanceCoursStagiaireDaoImpl.class);
    	bind(InstanceEvaluationDao.class).to(InstanceEvaluationDaoImpl.class);
    	bind(InstanceEvaluationStagiaireDao.class).to(InstanceEvaluationStagiaireDaoImpl.class);
    	bind(InstanceSessionValidationDao.class).to(InstanceSessionValidationDaoImpl.class);
    	bind(InstanceSessionValidationStagiaireDao.class).to(InstanceSessionValidationStagiaireDaoImpl.class);
    	bind(JuryDao.class).to(JuryDaoImpl.class);
    	bind(ModuleDao.class).to(ModuleDaoImpl.class);
    	bind(ModuleParUniteDao.class).to(ModuleParUniteDaoImpl.class);
    	bind(ParametreDao.class).to(ParametreDaoImpl.class);
    	bind(PlanningIndividuelDetailDao.class).to(PlanningIndividuelDetailDaoImpl.class);
    	bind(PlanningIndividuelFormationDao.class).to(PlanningIndividuelFormationDaoImpl.class);
    	bind(ProfessionnelHomologueDao.class).to(ProfessionnelHomologueDaoImpl.class);
    	bind(ProfilDao.class).to(ProfilDaoImpl.class);
    	bind(PromotionDao.class).to(PromotionDaoImpl.class);
    	bind(ReservationSalleDao.class).to(ReservationSalleDaoImpl.class);
    	bind(SalleDao.class).to(SalleDaoImpl.class);
    	bind(SessionValidationDao.class).to(SessionValidationDaoImpl.class);
    	bind(StagiaireDao.class).to(StagiaireDaoImpl.class);
    	bind(TitreProfessionnelDao.class).to(TitreProfessionnelDaoImpl.class);
    	bind(UniteFormationDao.class).to(UniteFormationDaoImpl.class);
    	bind(UniteParFormationDao.class).to(UniteParFormationDaoImpl.class);
    	bind(UtilisateurDao.class).to(UtilisateurDaoImpl.class);
    	
    	// Binding des services
    	bind(AbsenceService.class).to(AbsenceServiceImpl.class);
    	bind(AvisService.class).to(AvisServiceImpl.class);
    	bind(CoursService.class).to(CoursServiceImpl.class);
    	bind(EchangeService.class).to(EchangeServiceImpl.class);
    	bind(EvaluationService.class).to(EvaluationServiceImpl.class);
    	bind(ModuleService.class).to(ModuleServiceImpl.class);
    	bind(ParametreService.class).to(ParametreServiceImpl.class);
    	bind(ProfessionnelHomologueService.class).to(ProfessionnelHomologueServiceImpl.class);
    	bind(ProfilService.class).to(ProfilServiceImpl.class);
    	bind(ReservationSalleService.class).to(ReservationSalleServiceImpl.class);
    	bind(SessionValidationService.class).to(SessionValidationServiceImpl.class);
    	bind(StagiaireService.class).to(StagiaireServiceImpl.class);
    	bind(TitreProfessionnelService.class).to(TitreProfessionnelServiceImpl.class);
    	bind(UtilisateurService.class).to(UtilisateurServiceImpl.class);
    
    
    
    
    }
}
