package net.eni.gestion.pedagogie.DAO.implementation;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;

import net.eni.gestion.pedagogie.DAO.ModuleDao;
import net.eni.gestion.pedagogie.commun.composant.erreur.ApplicationException;
import net.eni.gestion.pedagogie.commun.modele.Module;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.j256.ormlite.support.ConnectionSource;

/**
 * @author jollivier
 * Service métier "Module"
 */
@Singleton
public class ModuleDaoImpl extends ADaoImpl<Module, Integer> implements ModuleDao{
	
	/**
	 * Constructeur de la DAO ModuleBase
	 * @throws SQLException
	 */
	@Inject
	public ModuleDaoImpl(Provider<ConnectionSource> connection) throws SQLException {
		super(connection, Module.class);
	}
	
	public HashMap<String, String> getTitleMap() throws ApplicationException {
		try{
			Iterator<Module> lModules = this.queryForAll().iterator();
			HashMap<String, String> lResults = new HashMap<String, String>();
			while (lModules.hasNext()) {
				Module lModule = lModules.next();
				lResults.put(lModule.getId().toString(), lModule.getLibelle());
			}
			return lResults;
		} catch (Exception exception) {
			throw new ApplicationException(
					"Echec de chargement de la liste d'enregistrements depuis la base de données");
		}
	}

}
