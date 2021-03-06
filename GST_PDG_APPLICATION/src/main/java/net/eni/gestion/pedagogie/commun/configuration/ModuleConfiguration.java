package net.eni.gestion.pedagogie.commun.configuration;

import net.eni.gestion.pedagogie.DAO.AbsenceDao;
import net.eni.gestion.pedagogie.DAO.AvisDao;
import net.eni.gestion.pedagogie.DAO.CoursDao;
import net.eni.gestion.pedagogie.DAO.CoursStagiaireDao;
import net.eni.gestion.pedagogie.DAO.DroitDao;
import net.eni.gestion.pedagogie.DAO.DroitProfilDao;
import net.eni.gestion.pedagogie.DAO.EchangeDao;
import net.eni.gestion.pedagogie.DAO.EvaluationDao;
import net.eni.gestion.pedagogie.DAO.EvaluationStagiaireDao;
import net.eni.gestion.pedagogie.DAO.FonctionDao;
import net.eni.gestion.pedagogie.DAO.FormationDao;
import net.eni.gestion.pedagogie.DAO.HomologationDao;
import net.eni.gestion.pedagogie.DAO.InstanceCoursDao;
import net.eni.gestion.pedagogie.DAO.InstanceEvaluationDao;
import net.eni.gestion.pedagogie.DAO.InstanceSessionValidationDao;
import net.eni.gestion.pedagogie.DAO.JuryDao;
import net.eni.gestion.pedagogie.DAO.ModuleDao;
import net.eni.gestion.pedagogie.DAO.ModuleParUniteDao;
import net.eni.gestion.pedagogie.DAO.ParametreDao;
import net.eni.gestion.pedagogie.DAO.PlanningDao;
import net.eni.gestion.pedagogie.DAO.PlanningIndividuelDetailDao;
import net.eni.gestion.pedagogie.DAO.PlanningIndividuelFormationDao;
import net.eni.gestion.pedagogie.DAO.ProfessionnelHomologueDao;
import net.eni.gestion.pedagogie.DAO.ProfilDao;
import net.eni.gestion.pedagogie.DAO.PromotionDao;
import net.eni.gestion.pedagogie.DAO.ReservationSalleDao;
import net.eni.gestion.pedagogie.DAO.SalleDao;
import net.eni.gestion.pedagogie.DAO.SessionValidationDao;
import net.eni.gestion.pedagogie.DAO.SessionValidationStagiaireDao;
import net.eni.gestion.pedagogie.DAO.StagiaireAvisDao;
import net.eni.gestion.pedagogie.DAO.StagiaireDao;
import net.eni.gestion.pedagogie.DAO.StagiairePromotionDao;
import net.eni.gestion.pedagogie.DAO.SujetEvaluationDao;
import net.eni.gestion.pedagogie.DAO.TypeSessionDao;
import net.eni.gestion.pedagogie.DAO.UniteFormationDao;
import net.eni.gestion.pedagogie.DAO.UniteParFormationDao;
import net.eni.gestion.pedagogie.DAO.UtilisateurDao;
import net.eni.gestion.pedagogie.DAO.implementation.AbsenceDaoImpl;
import net.eni.gestion.pedagogie.DAO.implementation.AvisDaoImpl;
import net.eni.gestion.pedagogie.DAO.implementation.CoursDaoImpl;
import net.eni.gestion.pedagogie.DAO.implementation.CoursStagiaireDaoImpl;
import net.eni.gestion.pedagogie.DAO.implementation.DroitDaoImpl;
import net.eni.gestion.pedagogie.DAO.implementation.DroitProfilDaoImpl;
import net.eni.gestion.pedagogie.DAO.implementation.EchangeDaoImpl;
import net.eni.gestion.pedagogie.DAO.implementation.EvaluationDaoImpl;
import net.eni.gestion.pedagogie.DAO.implementation.EvaluationStagiaireDaoImpl;
import net.eni.gestion.pedagogie.DAO.implementation.FonctionDaoImpl;
import net.eni.gestion.pedagogie.DAO.implementation.FormationDaoImpl;
import net.eni.gestion.pedagogie.DAO.implementation.HomologationDaoImpl;
import net.eni.gestion.pedagogie.DAO.implementation.InstanceCoursDaoImpl;
import net.eni.gestion.pedagogie.DAO.implementation.InstanceEvaluationDaoImpl;
import net.eni.gestion.pedagogie.DAO.implementation.InstanceSessionValidationDaoImpl;
import net.eni.gestion.pedagogie.DAO.implementation.JuryDaoImpl;
import net.eni.gestion.pedagogie.DAO.implementation.ModuleDaoImpl;
import net.eni.gestion.pedagogie.DAO.implementation.ModuleParUniteDaoImpl;
import net.eni.gestion.pedagogie.DAO.implementation.ParametreDaoImpl;
import net.eni.gestion.pedagogie.DAO.implementation.PlanningDaoImpl;
import net.eni.gestion.pedagogie.DAO.implementation.PlanningIndividuelDetailDaoImpl;
import net.eni.gestion.pedagogie.DAO.implementation.PlanningIndividuelFormationDaoImpl;
import net.eni.gestion.pedagogie.DAO.implementation.ProfessionnelHomologueDaoImpl;
import net.eni.gestion.pedagogie.DAO.implementation.ProfilDaoImpl;
import net.eni.gestion.pedagogie.DAO.implementation.PromotionDaoImpl;
import net.eni.gestion.pedagogie.DAO.implementation.ReservationSalleDaoImpl;
import net.eni.gestion.pedagogie.DAO.implementation.SalleDaoImpl;
import net.eni.gestion.pedagogie.DAO.implementation.SessionValidationDaoImpl;
import net.eni.gestion.pedagogie.DAO.implementation.SessionValidationStagiaireDaoImpl;
import net.eni.gestion.pedagogie.DAO.implementation.StagiaireAvisDaoImpl;
import net.eni.gestion.pedagogie.DAO.implementation.StagiaireDaoImpl;
import net.eni.gestion.pedagogie.DAO.implementation.StagiairePromotionDaoImpl;
import net.eni.gestion.pedagogie.DAO.implementation.SujetEvaluationDaoImpl;
import net.eni.gestion.pedagogie.DAO.implementation.TypeSessionDaoImpl;
import net.eni.gestion.pedagogie.DAO.implementation.UniteFormationDaoImpl;
import net.eni.gestion.pedagogie.DAO.implementation.UniteParFormationDaoImpl;
import net.eni.gestion.pedagogie.DAO.implementation.UtilisateurDaoImpl;
import net.eni.gestion.pedagogie.service.AbsenceService;
import net.eni.gestion.pedagogie.service.AvisService;
import net.eni.gestion.pedagogie.service.CoursService;
import net.eni.gestion.pedagogie.service.EchangeService;
import net.eni.gestion.pedagogie.service.EvaluationService;
import net.eni.gestion.pedagogie.service.FichierService;
import net.eni.gestion.pedagogie.service.FonctionService;
import net.eni.gestion.pedagogie.service.InstanceCoursService;
import net.eni.gestion.pedagogie.service.InstanceEvaluationService;
import net.eni.gestion.pedagogie.service.InstanceSessionValidationService;
import net.eni.gestion.pedagogie.service.ModuleService;
import net.eni.gestion.pedagogie.service.ParametreService;
import net.eni.gestion.pedagogie.service.PlanningService;
import net.eni.gestion.pedagogie.service.ProfessionnelHomologueService;
import net.eni.gestion.pedagogie.service.ProfilService;
import net.eni.gestion.pedagogie.service.ReservationSalleService;
import net.eni.gestion.pedagogie.service.SalleService;
import net.eni.gestion.pedagogie.service.SessionValidationService;
import net.eni.gestion.pedagogie.service.StagiaireAvisService;
import net.eni.gestion.pedagogie.service.StagiairePromotionService;
import net.eni.gestion.pedagogie.service.StagiaireService;
import net.eni.gestion.pedagogie.service.SujetEvaluationService;
import net.eni.gestion.pedagogie.service.TypeSessionService;
import net.eni.gestion.pedagogie.service.UtilisateurService;
import net.eni.gestion.pedagogie.service.implementation.AbsenceServiceImpl;
import net.eni.gestion.pedagogie.service.implementation.AvisServiceImpl;
import net.eni.gestion.pedagogie.service.implementation.CoursServiceImpl;
import net.eni.gestion.pedagogie.service.implementation.EchangeServiceImpl;
import net.eni.gestion.pedagogie.service.implementation.EvaluationServiceImpl;
import net.eni.gestion.pedagogie.service.implementation.FichierServiceImpl;
import net.eni.gestion.pedagogie.service.implementation.FonctionServiceImpl;
import net.eni.gestion.pedagogie.service.implementation.InstanceCoursServiceImpl;
import net.eni.gestion.pedagogie.service.implementation.InstanceEvaluationServiceImpl;
import net.eni.gestion.pedagogie.service.implementation.InstanceSessionValidationServiceImpl;
import net.eni.gestion.pedagogie.service.implementation.ModuleServiceImpl;
import net.eni.gestion.pedagogie.service.implementation.ParametreServiceImpl;
import net.eni.gestion.pedagogie.service.implementation.PlanningServiceImpl;
import net.eni.gestion.pedagogie.service.implementation.ProfessionnelHomologueServiceImpl;
import net.eni.gestion.pedagogie.service.implementation.ProfilServiceImpl;
import net.eni.gestion.pedagogie.service.implementation.ReservationSalleServiceImpl;
import net.eni.gestion.pedagogie.service.implementation.SalleServiceImpl;
import net.eni.gestion.pedagogie.service.implementation.SessionValidationServiceImpl;
import net.eni.gestion.pedagogie.service.implementation.StagiaireAvisServiceImpl;
import net.eni.gestion.pedagogie.service.implementation.StagiairePromotionServiceImpl;
import net.eni.gestion.pedagogie.service.implementation.StagiaireServiceImpl;
import net.eni.gestion.pedagogie.service.implementation.SujetEvaluationServiceImpl;
import net.eni.gestion.pedagogie.service.implementation.TypeSessionServiceImpl;
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
    	bind(AbsenceDao.class).to(AbsenceDaoImpl.class).asEagerSingleton();
    	bind(AvisDao.class).to(AvisDaoImpl.class).asEagerSingleton();
    	bind(CoursDao.class).to(CoursDaoImpl.class).asEagerSingleton();
    	bind(DroitDao.class).to(DroitDaoImpl.class).asEagerSingleton();
    	bind(DroitProfilDao.class).to(DroitProfilDaoImpl.class).asEagerSingleton();
    	bind(EchangeDao.class).to(EchangeDaoImpl.class).asEagerSingleton();
    	bind(EvaluationDao.class).to(EvaluationDaoImpl.class).asEagerSingleton();
    	bind(FonctionDao.class).to(FonctionDaoImpl.class).asEagerSingleton();
    	bind(FormationDao.class).to(FormationDaoImpl.class).asEagerSingleton();
    	bind(HomologationDao.class).to(HomologationDaoImpl.class).asEagerSingleton();
    	bind(InstanceCoursDao.class).to(InstanceCoursDaoImpl.class).asEagerSingleton();
    	bind(CoursStagiaireDao.class).to(CoursStagiaireDaoImpl.class).asEagerSingleton();
    	bind(InstanceEvaluationDao.class).to(InstanceEvaluationDaoImpl.class).asEagerSingleton();
    	bind(EvaluationStagiaireDao.class).to(EvaluationStagiaireDaoImpl.class).asEagerSingleton();
    	bind(InstanceSessionValidationDao.class).to(InstanceSessionValidationDaoImpl.class).asEagerSingleton();
    	bind(SessionValidationStagiaireDao.class).to(SessionValidationStagiaireDaoImpl.class).asEagerSingleton();
    	bind(JuryDao.class).to(JuryDaoImpl.class).asEagerSingleton();
    	bind(ModuleDao.class).to(ModuleDaoImpl.class).asEagerSingleton();
    	bind(ModuleParUniteDao.class).to(ModuleParUniteDaoImpl.class).asEagerSingleton();
    	bind(ParametreDao.class).to(ParametreDaoImpl.class).asEagerSingleton();
    	bind(PlanningDao.class).to(PlanningDaoImpl.class).asEagerSingleton();
    	bind(PlanningIndividuelDetailDao.class).to(PlanningIndividuelDetailDaoImpl.class).asEagerSingleton();
    	bind(PlanningIndividuelFormationDao.class).to(PlanningIndividuelFormationDaoImpl.class).asEagerSingleton();
    	bind(ProfessionnelHomologueDao.class).to(ProfessionnelHomologueDaoImpl.class).asEagerSingleton();
    	bind(ProfilDao.class).to(ProfilDaoImpl.class).asEagerSingleton();
    	bind(PromotionDao.class).to(PromotionDaoImpl.class).asEagerSingleton();
    	bind(ReservationSalleDao.class).to(ReservationSalleDaoImpl.class).asEagerSingleton();
    	bind(SalleDao.class).to(SalleDaoImpl.class).asEagerSingleton();
    	bind(SessionValidationDao.class).to(SessionValidationDaoImpl.class).asEagerSingleton();
    	bind(SujetEvaluationDao.class).to(SujetEvaluationDaoImpl.class).asEagerSingleton();
    	bind(StagiaireDao.class).to(StagiaireDaoImpl.class).asEagerSingleton();
    	bind(StagiaireAvisDao.class).to(StagiaireAvisDaoImpl.class).asEagerSingleton();
    	bind(StagiairePromotionDao.class).to(StagiairePromotionDaoImpl.class).asEagerSingleton();
    	bind(TypeSessionDao.class).to(TypeSessionDaoImpl.class).asEagerSingleton();
    	bind(UniteFormationDao.class).to(UniteFormationDaoImpl.class).asEagerSingleton();
    	bind(UniteParFormationDao.class).to(UniteParFormationDaoImpl.class).asEagerSingleton();
    	bind(UtilisateurDao.class).to(UtilisateurDaoImpl.class).asEagerSingleton();
    	
    	// Binding des services
    	bind(AbsenceService.class).to(AbsenceServiceImpl.class).asEagerSingleton();
    	bind(AvisService.class).to(AvisServiceImpl.class).asEagerSingleton();
    	bind(CoursService.class).to(CoursServiceImpl.class).asEagerSingleton();
    	bind(EchangeService.class).to(EchangeServiceImpl.class).asEagerSingleton();
    	bind(EvaluationService.class).to(EvaluationServiceImpl.class).asEagerSingleton();
    	bind(FichierService.class).to(FichierServiceImpl.class).asEagerSingleton();
    	bind(FonctionService.class).to(FonctionServiceImpl.class).asEagerSingleton();
    	bind(InstanceCoursService.class).to(InstanceCoursServiceImpl.class).asEagerSingleton();
    	bind(InstanceEvaluationService.class).to(InstanceEvaluationServiceImpl.class).asEagerSingleton();
    	bind(InstanceSessionValidationService.class).to(InstanceSessionValidationServiceImpl.class).asEagerSingleton();
    	bind(ModuleService.class).to(ModuleServiceImpl.class).asEagerSingleton();
    	bind(ParametreService.class).to(ParametreServiceImpl.class).asEagerSingleton();
    	bind(ProfessionnelHomologueService.class).to(ProfessionnelHomologueServiceImpl.class).asEagerSingleton();
    	bind(ProfilService.class).to(ProfilServiceImpl.class).asEagerSingleton();
    	bind(ReservationSalleService.class).to(ReservationSalleServiceImpl.class).asEagerSingleton();
    	bind(SalleService.class).to(SalleServiceImpl.class).asEagerSingleton();
    	bind(SessionValidationService.class).to(SessionValidationServiceImpl.class).asEagerSingleton();
    	bind(SujetEvaluationService.class).to(SujetEvaluationServiceImpl.class).asEagerSingleton();
    	bind(StagiaireService.class).to(StagiaireServiceImpl.class).asEagerSingleton();
    	bind(StagiaireAvisService.class).to(StagiaireAvisServiceImpl.class).asEagerSingleton();
    	bind(StagiairePromotionService.class).to(StagiairePromotionServiceImpl.class).asEagerSingleton();
    	bind(TypeSessionService.class).to(TypeSessionServiceImpl.class).asEagerSingleton();
    	bind(UtilisateurService.class).to(UtilisateurServiceImpl.class).asEagerSingleton();
    	bind(PlanningService.class).to(PlanningServiceImpl.class).asEagerSingleton();
    }
}
