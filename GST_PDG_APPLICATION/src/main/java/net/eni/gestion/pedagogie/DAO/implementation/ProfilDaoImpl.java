package net.eni.gestion.pedagogie.DAO.implementation;

import java.sql.SQLException;
import java.util.ArrayList;
import net.eni.gestion.pedagogie.DAO.ProfilDao;
import net.eni.gestion.pedagogie.commun.composant.Connexion;
import net.eni.gestion.pedagogie.commun.outil.CRUDHelper;
import net.eni.gestion.pedagogie.modele.Profil;
import com.google.inject.Singleton;
import com.j256.ormlite.dao.BaseDaoImpl;

/**
 * @author jollivier
 * Service métier "Profil"
 */
@Singleton
public class ProfilDaoImpl extends BaseDaoImpl<Profil, Integer> implements ProfilDao{
	
	
	/**
	 * Constructeur de la DAO ProfilBase
	 * @throws SQLException
	 */
	public ProfilDaoImpl() throws SQLException {
		super(Connexion.getConnexion(), Profil.class);
	}
	
	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.contrat.generique.CRUDBase#charger(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public ArrayList<Profil> charger(Profil pProfil) throws Exception {
		return CRUDHelper.charger(this, pProfil);
	}
	
	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.generique.CRUDBase#chargerDetail(net.eni.gestion.pedagogie.modele.Profil)
	 */
	public Profil chargerDetail(Profil pProfil) throws Exception {
		return CRUDHelper.chargerDetail(this, pProfil);
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.contrat.generique.CRUDBase#ajouter(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public Profil ajouter(Profil pProfil) throws Exception {
		return CRUDHelper.ajouter(this, pProfil);
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.contrat.generique.CRUDBase#mettreAJour(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public Profil mettreAJour(Profil pProfil) throws Exception {
		return CRUDHelper.mettreAJour(this, pProfil);
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.contrat.generique.CRUDBase#supprimer(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public Profil supprimer(Profil pProfil) throws Exception {
		return CRUDHelper.supprimer(this, pProfil);
	}

}
