package net.eni.gestion.pedagogie.service.implementation;

import java.sql.SQLException;

import net.eni.gestion.pedagogie.DAO.ParametreDao;
import net.eni.gestion.pedagogie.commun.modele.Parametre;
import net.eni.gestion.pedagogie.service.ParametreService;

import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * @author jollivier
 * Classe d'implémentation pour le module de suivi des parametres
 */
@Singleton
public class ParametreServiceImpl extends AServiceImpl<Parametre, String, ParametreDao> implements ParametreService {

       /**
     * Constructeur
     * @param DAO parametre
     * @throws SQLException
     */
    @Inject
    public ParametreServiceImpl(ParametreDao pParametreDao) throws SQLException {
        super(pParametreDao);
    }
    
}
