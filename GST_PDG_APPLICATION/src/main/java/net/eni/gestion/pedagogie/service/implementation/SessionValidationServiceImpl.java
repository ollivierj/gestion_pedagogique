package net.eni.gestion.pedagogie.service.implementation;

import java.sql.SQLException;

import net.eni.gestion.pedagogie.DAO.SessionValidationDao;
import net.eni.gestion.pedagogie.modele.SessionValidation;
import net.eni.gestion.pedagogie.service.SessionValidationService;

import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * @author jollivier
 * Classe d'implémentation pour le module de suivi des sessionValidations
 */
@Singleton
public class SessionValidationServiceImpl extends AServiceImpl<SessionValidation, Integer, SessionValidationDao> implements SessionValidationService {

       /**
     * Constructeur
     * @param DAO sessionValidation
     * @throws SQLException
     */
    @Inject
    public SessionValidationServiceImpl(SessionValidationDao pSessionValidationDao) throws SQLException {
        super(pSessionValidationDao);
    }
    
}
