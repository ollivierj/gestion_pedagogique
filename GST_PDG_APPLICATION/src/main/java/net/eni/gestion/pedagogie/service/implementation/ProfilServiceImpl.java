package net.eni.gestion.pedagogie.service.implementation;

import java.sql.SQLException;
import java.util.HashMap;

import net.eni.gestion.pedagogie.DAO.ProfilDao;
import net.eni.gestion.pedagogie.commun.composant.GenericException;
import net.eni.gestion.pedagogie.modele.Profil;
import net.eni.gestion.pedagogie.service.ProfilService;

import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * @author jollivier
 * Classe d'implémentation pour le module de suivi des profils
 */
@Singleton
public class ProfilServiceImpl extends AServiceImpl<Profil, Integer, ProfilDao> implements ProfilService {

       /**
     * Constructeur
     * @param DAO profil
     * @throws SQLException
     */
    @Inject
    public ProfilServiceImpl(ProfilDao pProfilDao) throws SQLException {
        super(pProfilDao);
    }
    
	public HashMap<String, String> getTitleMap() throws GenericException {
		try {
			return dao.getTitleMap();
		} catch (Exception e) {
			throw new GenericException(
					"Echec lors du chargement depuis la base de données.");
		}
	}
    
}
