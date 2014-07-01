package net.eni.gestion.pedagogie.service.implementation;

import java.sql.SQLException;

import net.eni.gestion.pedagogie.DAO.UtilisateurDao;
import net.eni.gestion.pedagogie.modele.Utilisateur;
import net.eni.gestion.pedagogie.service.UtilisateurService;

import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * @author jollivier
 * Classe d'implémentation pour le module de suivi des utilisateurs
 */
@Singleton
public class UtilisateurServiceImpl extends AServiceImpl<Utilisateur, Integer, UtilisateurDao> implements UtilisateurService {

       /**
     * Constructeur
     * @param DAO utilisateur
     * @throws SQLException
     */
    @Inject
    public UtilisateurServiceImpl(UtilisateurDao pUtilisateurDao) throws SQLException {
        super(pUtilisateurDao);
    }
    
}
