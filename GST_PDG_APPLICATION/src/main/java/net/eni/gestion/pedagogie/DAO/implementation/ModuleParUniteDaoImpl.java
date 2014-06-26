package net.eni.gestion.pedagogie.DAO.implementation;

import java.sql.SQLException;
import java.util.ArrayList;

import net.eni.gestion.pedagogie.DAO.ModuleParUniteDao;
import net.eni.gestion.pedagogie.commun.composant.Connexion;
import net.eni.gestion.pedagogie.commun.outil.CRUDHelper;
import net.eni.gestion.pedagogie.modele.ModuleParUnite;

import com.google.inject.Singleton;
import com.j256.ormlite.dao.BaseDaoImpl;

/**
 * @author jollivier
 * Service métier "ModuleParUnite"
 */
@Singleton
public class ModuleParUniteDaoImpl extends BaseDaoImpl<ModuleParUnite, Integer> implements ModuleParUniteDao{
	
	
	/**
	 * Constructeur de la DAO ModuleParUniteBase
	 * @throws SQLException
	 */
	public ModuleParUniteDaoImpl() throws SQLException {
		super(Connexion.getConnexion(), ModuleParUnite.class);
	}
	
	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.contrat.generique.CRUDBase#charger(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public ArrayList<ModuleParUnite> charger(ModuleParUnite pModuleParUnite) throws Exception {
		return CRUDHelper.charger(this, pModuleParUnite);
	}
	
	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.generique.CRUDBase#chargerDetail(net.eni.gestion.pedagogie.modele.ModuleParUnite)
	 */
	public ModuleParUnite chargerDetail(ModuleParUnite pModuleParUnite) throws Exception {
		return CRUDHelper.chargerDetail(this, pModuleParUnite);
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.contrat.generique.CRUDBase#ajouter(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public ModuleParUnite ajouter(ModuleParUnite pModuleParUnite) throws Exception {
		return CRUDHelper.ajouter(this, pModuleParUnite);
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.contrat.generique.CRUDBase#mettreAJour(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public ModuleParUnite mettreAJour(ModuleParUnite pModuleParUnite) throws Exception {
		return CRUDHelper.mettreAJour(this, pModuleParUnite);
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.contrat.generique.CRUDBase#supprimer(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public ModuleParUnite supprimer(ModuleParUnite pModuleParUnite) throws Exception {
		return CRUDHelper.supprimer(this, pModuleParUnite);
	}

}
