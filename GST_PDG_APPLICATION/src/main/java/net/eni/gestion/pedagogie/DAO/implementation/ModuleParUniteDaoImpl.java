package net.eni.gestion.pedagogie.DAO.implementation;

import java.sql.SQLException;

import net.eni.gestion.pedagogie.DAO.ModuleParUniteDao;
import net.eni.gestion.pedagogie.commun.modele.ModuleParUnite;

import com.google.inject.Singleton;

/**
 * @author jollivier
 * Service métier "ModuleParUnite"
 */
@Singleton
public class ModuleParUniteDaoImpl extends ADaoImpl<ModuleParUnite, Integer> implements ModuleParUniteDao{
	
	/**
	 * Constructeur de la DAO ModuleParUniteBase
	 * @throws SQLException
	 */
	public ModuleParUniteDaoImpl() throws SQLException {
		super(ModuleParUnite.class);
	}

}
