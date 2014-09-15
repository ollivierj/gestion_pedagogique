package net.eni.gestion.pedagogie.service.implementation;

import java.sql.SQLException;
import java.util.UUID;

import net.eni.gestion.pedagogie.DAO.CoursDao;
import net.eni.gestion.pedagogie.modele.Cours;
import net.eni.gestion.pedagogie.service.CoursService;

import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * @author jollivier
 * Classe d'implémentation pour le module de suivi des absences
 */
@Singleton
public class CoursServiceImpl extends AServiceImpl<Cours, UUID, CoursDao> implements CoursService {

       /**
     * Constructeur
     * @param DAO absence
     * @throws SQLException
     */
    @Inject
    public CoursServiceImpl(CoursDao pCoursDao) throws SQLException {
        super(pCoursDao);
    }

}
