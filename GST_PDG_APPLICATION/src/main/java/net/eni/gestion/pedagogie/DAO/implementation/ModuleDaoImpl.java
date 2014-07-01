package net.eni.gestion.pedagogie.DAO.implementation;

import java.sql.SQLException;

import net.eni.gestion.pedagogie.DAO.ModuleDao;
import net.eni.gestion.pedagogie.commun.composant.Connexion;
import net.eni.gestion.pedagogie.modele.Module;

import com.google.inject.Singleton;

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
	public ModuleDaoImpl() throws SQLException {
		super(Connexion.getConnexion(), Module.class);
	}

}
