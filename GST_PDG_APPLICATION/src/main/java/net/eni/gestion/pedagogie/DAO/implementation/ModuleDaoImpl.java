package net.eni.gestion.pedagogie.DAO.implementation;

import java.sql.SQLException;
import java.util.ArrayList;

import net.eni.gestion.pedagogie.DAO.ModuleDao;
import net.eni.gestion.pedagogie.commun.composant.Connexion;
import net.eni.gestion.pedagogie.commun.outil.CRUDHelper;
import net.eni.gestion.pedagogie.modele.Module;

import com.google.inject.Singleton;
import com.j256.ormlite.dao.BaseDaoImpl;

/**
 * @author jollivier
 * Service métier "Module"
 */
@Singleton
public class ModuleDaoImpl extends BaseDaoImpl<Module, Integer> implements ModuleDao{
	
	
	/**
	 * Constructeur de la DAO ModuleBase
	 * @throws SQLException
	 */
	public ModuleDaoImpl() throws SQLException {
		super(Connexion.getConnexion(), Module.class);
	}
	
	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.contrat.generique.CRUDBase#charger(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public ArrayList<Module> charger(Module pModule) throws Exception {
		return CRUDHelper.charger(this, pModule);
	}
	
	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.generique.CRUDBase#chargerDetail(net.eni.gestion.pedagogie.modele.Module)
	 */
	public Module chargerDetail(Module pModule) throws Exception {
		return CRUDHelper.chargerDetail(this, pModule);
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.contrat.generique.CRUDBase#ajouter(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public Module ajouter(Module pModule) throws Exception {
		return CRUDHelper.ajouter(this, pModule);
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.contrat.generique.CRUDBase#mettreAJour(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public Module mettreAJour(Module pModule) throws Exception {
		return CRUDHelper.mettreAJour(this, pModule);
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.contrat.generique.CRUDBase#supprimer(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public Module supprimer(Module pModule) throws Exception {
		return CRUDHelper.supprimer(this, pModule);
	}

}
