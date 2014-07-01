package net.eni.gestion.pedagogie.service.implementation;

import java.sql.SQLException;

import net.eni.gestion.pedagogie.DAO.EchangeDao;
import net.eni.gestion.pedagogie.modele.Echange;
import net.eni.gestion.pedagogie.service.EchangeService;

import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * @author jollivier
 * Classe d'implémentation pour le module de suivi des echanges
 */
@Singleton
public class EchangeServiceImpl extends AServiceImpl<Echange, Integer, EchangeDao> implements EchangeService {

       /**
     * Constructeur
     * @param DAO echange
     * @throws SQLException
     */
    @Inject
    public EchangeServiceImpl(EchangeDao pEchangeDao) throws SQLException {
        super(pEchangeDao);
    }
    
}
