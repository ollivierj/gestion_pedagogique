package net.eni.gestion.pedagogie.service.implementation;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.eni.gestion.pedagogie.DAO.AbsenceDao;
import net.eni.gestion.pedagogie.DAO.AvisDao;
import net.eni.gestion.pedagogie.DAO.EchangeDao;
import net.eni.gestion.pedagogie.DAO.StagiaireDao;
import net.eni.gestion.pedagogie.DAO.implementation.AbsenceDaoImpl;
import net.eni.gestion.pedagogie.DAO.implementation.AvisDaoImpl;
import net.eni.gestion.pedagogie.DAO.implementation.EchangeDaoImpl;
import net.eni.gestion.pedagogie.commun.composant.GenericException;
import net.eni.gestion.pedagogie.commun.composant.Pager;
import net.eni.gestion.pedagogie.commun.composant.Pair;
import net.eni.gestion.pedagogie.modele.Absence;
import net.eni.gestion.pedagogie.modele.Avis;
import net.eni.gestion.pedagogie.modele.Echange;
import net.eni.gestion.pedagogie.modele.Stagiaire;
import net.eni.gestion.pedagogie.service.StagiaireService;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.j256.ormlite.dao.BaseDaoImpl;

/**
 * @author jollivier
 * Classe d'implémentation pour le module de suivi des stagiaires
 */
@Singleton
public class StagiaireServiceImpl extends AServiceImpl<Stagiaire, Integer, StagiaireDao> implements StagiaireService {

	protected final AbsenceDao absenceDao;
	protected final EchangeDao echangeDao;
	protected final AvisDao avisDao;
	
	/**
     * Constructeur
     * @param DAO stagiaire
     * @throws SQLException
     */
    @Inject
    public StagiaireServiceImpl(StagiaireDao pStagiaireDao, AbsenceDao absenceDao, EchangeDao echangeDao, AvisDao avisDao) throws SQLException {
        super(pStagiaireDao);
        this.absenceDao = absenceDao;
        this.echangeDao = echangeDao;
        this.avisDao = avisDao;
    }
    
    @Override
    public Stagiaire chargerDetail(Integer pId) throws GenericException {
    	Stagiaire stagiaire = super.chargerDetail(pId);
    	return stagiaire;
    }

	@Override
	public Pair<ArrayList<Absence>, Long> chargerAbsences(Pager pPager)
			throws GenericException {
		try {
			return dao.chargerAbsences((AbsenceDaoImpl)absenceDao, pPager);
		} catch (Exception e) {
			throw new GenericException("Erreur lors du chargement des données absences du stagiaire.");
		}
	}

	@Override
	public Pair<ArrayList<Echange>, Long> chargerEchanges(Pager pPager)
			throws GenericException {
		try {
			return dao.chargerEchanges((EchangeDaoImpl)echangeDao, pPager);
		} catch (Exception e) {
			throw new GenericException("Erreur lors du chargement des données échanges du stagiaire.");
		}
	}

	@Override
	public Pair<ArrayList<Avis>, Long> chargerAvis(Pager pPager) throws GenericException {
		try {
			return dao.chargerAvis((AvisDaoImpl)avisDao, pPager);
		} catch (Exception e) {
			throw new GenericException("Erreur lors du chargement des données avis du stagiaire.");
		}
	}
    
    
    
}
