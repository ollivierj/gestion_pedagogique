package net.eni.gestion.pedagogie.service.implementation;

import java.sql.SQLException;

import net.eni.gestion.pedagogie.DAO.AvisDao;
import net.eni.gestion.pedagogie.modele.Avis;
import net.eni.gestion.pedagogie.service.AvisService;

import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * @author jollivier
 * Classe d'implémentation pour le module de suivi des aviss
 */
@Singleton
public class AvisServiceImpl extends AServiceImpl<Avis, Integer, AvisDao> implements AvisService {

       /**
     * Constructeur
     * @param DAO avis
     * @throws SQLException
     */
    @Inject
    public AvisServiceImpl(AvisDao pAvisDao) throws SQLException {
        super(pAvisDao);
    }
    
}
