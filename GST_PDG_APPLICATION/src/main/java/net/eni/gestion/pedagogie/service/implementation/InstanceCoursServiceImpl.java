package net.eni.gestion.pedagogie.service.implementation;

import java.sql.SQLException;

import net.eni.gestion.pedagogie.DAO.InstanceCoursDao;
import net.eni.gestion.pedagogie.commun.modele.InstanceCours;
import net.eni.gestion.pedagogie.service.InstanceCoursService;

import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * @author jollivier
 * Classe d'implémentation pour le module de suivi des evaluations
 */
@Singleton
public class InstanceCoursServiceImpl extends AServiceImpl<InstanceCours, Integer, InstanceCoursDao> implements InstanceCoursService {

    /**
     * Constructeur
     * @param DAO evaluation
     * @throws SQLException
     */
    @Inject
    public InstanceCoursServiceImpl(InstanceCoursDao pInstanceCoursDao) throws SQLException {
        super(pInstanceCoursDao);
    }
    
}
