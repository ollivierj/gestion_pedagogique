package net.eni.gestion.pedagogie.service.implementation;

import java.sql.SQLException;
import java.util.HashMap;

import net.eni.gestion.pedagogie.DAO.ModuleDao;
import net.eni.gestion.pedagogie.commun.composant.GenericException;
import net.eni.gestion.pedagogie.modele.Module;
import net.eni.gestion.pedagogie.service.ModuleService;

import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * @author jollivier Classe d'implémentation pour le module de suivi des
 *         titreProfessionnels
 */
@Singleton
public class ModuleServiceImpl extends
		AServiceImpl<Module, Integer, ModuleDao>
		implements ModuleService {

	/**
	 * Constructeur
	 * 
	 * @param DAO
	 *            titreProfessionnel
	 * @throws SQLException
	 */
	@Inject
	public ModuleServiceImpl(
			ModuleDao pModuleDao) throws SQLException {
		super(pModuleDao);
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
