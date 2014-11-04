package net.eni.gestion.pedagogie.service.implementation;

import java.sql.SQLException;

import net.eni.gestion.pedagogie.DAO.StagiaireAvisDao;
import net.eni.gestion.pedagogie.commun.modele.StagiaireAvis;
import net.eni.gestion.pedagogie.service.StagiaireAvisService;

import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * @author jollivier
 * Classe d'implémentation pour le module de suivi des aviss
 */
@Singleton
public class StagiaireAvisServiceImpl extends AServiceImpl<StagiaireAvis, Integer, StagiaireAvisDao> implements StagiaireAvisService {

       /**
     * Constructeur
     * @param DAO avis
     * @throws SQLException
     */
    @Inject
    public StagiaireAvisServiceImpl(StagiaireAvisDao pStagiaireAvisDao) throws SQLException {
        super(pStagiaireAvisDao);
    }

}
