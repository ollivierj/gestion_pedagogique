package net.eni.gestion.pedagogie.service.implementation;

import java.sql.SQLException;

import net.eni.gestion.pedagogie.DAO.ProfessionnelHomologueDao;
import net.eni.gestion.pedagogie.modele.ProfessionnelHomologue;
import net.eni.gestion.pedagogie.service.ProfessionnelHomologueService;

import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * @author jollivier
 * Classe d'implémentation pour le module de suivi des professionnelHomologues
 */
@Singleton
public class ProfessionnelHomologueServiceImpl extends AServiceImpl<ProfessionnelHomologue, Integer, ProfessionnelHomologueDao> implements ProfessionnelHomologueService {

       /**
     * Constructeur
     * @param DAO professionnelHomologue
     * @throws SQLException
     */
    @Inject
    public ProfessionnelHomologueServiceImpl(ProfessionnelHomologueDao pProfessionnelHomologueDao) throws SQLException {
        super(pProfessionnelHomologueDao);
    }
    
}
