package net.eni.gestion.pedagogie.service.implementation;

import java.sql.SQLException;

import net.eni.gestion.pedagogie.DAO.AbsenceDao;
import net.eni.gestion.pedagogie.DAO.StagiaireDao;
import net.eni.gestion.pedagogie.commun.composant.GenericException;
import net.eni.gestion.pedagogie.modele.Stagiaire;
import net.eni.gestion.pedagogie.service.StagiaireService;

import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * @author jollivier
 * Classe d'implémentation pour le module de suivi des stagiaires
 */
@Singleton
public class StagiaireServiceImpl extends AServiceImpl<Stagiaire, Integer, StagiaireDao> implements StagiaireService {

	protected final AbsenceDao absenceDao;
	/**
     * Constructeur
     * @param DAO stagiaire
     * @throws SQLException
     */
    @Inject
    public StagiaireServiceImpl(StagiaireDao pStagiaireDao, AbsenceDao absenceDao) throws SQLException {
        super(pStagiaireDao);
        this.absenceDao = absenceDao;
    }
    
    @Override
    public Stagiaire chargerDetail(Integer pId) throws GenericException {
    	Stagiaire stagiaire = super.chargerDetail(pId);
//    	stagiaire.getAbsences();
    	return stagiaire;
    }
}
