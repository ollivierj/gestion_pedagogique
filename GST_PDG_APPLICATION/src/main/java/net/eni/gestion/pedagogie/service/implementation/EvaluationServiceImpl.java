package net.eni.gestion.pedagogie.service.implementation;

import java.sql.SQLException;

import net.eni.gestion.pedagogie.DAO.EvaluationDao;
import net.eni.gestion.pedagogie.modele.Evaluation;
import net.eni.gestion.pedagogie.service.EvaluationService;

import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * @author jollivier
 * Classe d'implémentation pour le module de suivi des evaluations
 */
@Singleton
public class EvaluationServiceImpl extends AServiceImpl<Evaluation, Integer, EvaluationDao> implements EvaluationService {

       /**
     * Constructeur
     * @param DAO evaluation
     * @throws SQLException
     */
    @Inject
    public EvaluationServiceImpl(EvaluationDao pEvaluationDao) throws SQLException {
        super(pEvaluationDao);
    }

}
