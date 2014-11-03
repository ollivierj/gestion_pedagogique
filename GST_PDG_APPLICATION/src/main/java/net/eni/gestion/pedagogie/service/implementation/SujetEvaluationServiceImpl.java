package net.eni.gestion.pedagogie.service.implementation;

import java.sql.SQLException;

import net.eni.gestion.pedagogie.DAO.SujetEvaluationDao;
import net.eni.gestion.pedagogie.commun.modele.SujetEvaluation;
import net.eni.gestion.pedagogie.service.SujetEvaluationService;

import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * @author jollivier
 * Classe d'implémentation pour le module de suivi des evaluations
 */
@Singleton
public class SujetEvaluationServiceImpl extends AServiceImpl<SujetEvaluation, Integer, SujetEvaluationDao> implements SujetEvaluationService {

       /**
     * Constructeur
     * @param DAO evaluation
     * @throws SQLException
     */
    @Inject
    public SujetEvaluationServiceImpl(SujetEvaluationDao pSujetEvaluationDao) throws SQLException {
        super(pSujetEvaluationDao);
    }

}
