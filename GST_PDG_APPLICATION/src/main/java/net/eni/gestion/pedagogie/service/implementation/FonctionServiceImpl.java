package net.eni.gestion.pedagogie.service.implementation;

import java.sql.SQLException;
import java.util.HashMap;

import net.eni.gestion.pedagogie.DAO.FonctionDao;
import net.eni.gestion.pedagogie.commun.composant.GenericException;
import net.eni.gestion.pedagogie.modele.Fonction;
import net.eni.gestion.pedagogie.service.FonctionService;

import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * @author jollivier Classe d'implémentation pour le module de suivi des
 *         titreProfessionnels
 */
@Singleton
public class FonctionServiceImpl extends
		AServiceImpl<Fonction, String, FonctionDao>
		implements FonctionService {

	/**
	 * Constructeur
	 * 
	 * @param DAO
	 *            titreProfessionnel
	 * @throws SQLException
	 */
	@Inject
	public FonctionServiceImpl(
			FonctionDao pFonctionDao) throws SQLException {
		super(pFonctionDao);
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
