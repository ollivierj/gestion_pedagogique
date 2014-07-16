package net.eni.gestion.pedagogie.service.implementation;

import java.sql.SQLException;
import java.util.HashMap;

import net.eni.gestion.pedagogie.DAO.TitreProfessionnelDao;
import net.eni.gestion.pedagogie.commun.composant.GenericException;
import net.eni.gestion.pedagogie.modele.TitreProfessionnel;
import net.eni.gestion.pedagogie.service.TitreProfessionnelService;

import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * @author jollivier Classe d'implémentation pour le module de suivi des
 *         titreProfessionnels
 */
@Singleton
public class TitreProfessionnelServiceImpl extends
		AServiceImpl<TitreProfessionnel, Integer, TitreProfessionnelDao>
		implements TitreProfessionnelService {

	/**
	 * Constructeur
	 * 
	 * @param DAO
	 *            titreProfessionnel
	 * @throws SQLException
	 */
	@Inject
	public TitreProfessionnelServiceImpl(
			TitreProfessionnelDao pTitreProfessionnelDao) throws SQLException {
		super(pTitreProfessionnelDao);
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
