package net.eni.gestion.pedagogie.DAO.implementation;

import java.sql.SQLException;
import java.util.ArrayList;
import net.eni.gestion.pedagogie.DAO.SessionValidationDao;
import net.eni.gestion.pedagogie.commun.composant.Connexion;
import net.eni.gestion.pedagogie.commun.outil.CRUDHelper;
import net.eni.gestion.pedagogie.modele.SessionValidation;
import com.google.inject.Singleton;
import com.j256.ormlite.dao.BaseDaoImpl;

/**
 * @author jollivier
 * Service métier "SessionValidation"
 */
@Singleton
public class SessionValidationDaoImpl extends BaseDaoImpl<SessionValidation, Integer> implements SessionValidationDao{
	
	
	/**
	 * Constructeur de la DAO SessionValidationBase
	 * @throws SQLException
	 */
	public SessionValidationDaoImpl() throws SQLException {
		super(Connexion.getConnexion(), SessionValidation.class);
	}
	
	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.contrat.generique.CRUDBase#charger(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public ArrayList<SessionValidation> charger(SessionValidation pSessionValidation) throws Exception {
		return CRUDHelper.charger(this, pSessionValidation);
	}
	
	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.generique.CRUDBase#chargerDetail(net.eni.gestion.pedagogie.modele.SessionValidation)
	 */
	public SessionValidation chargerDetail(SessionValidation pSessionValidation) throws Exception {
		return CRUDHelper.chargerDetail(this, pSessionValidation);
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.contrat.generique.CRUDBase#ajouter(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public SessionValidation ajouter(SessionValidation pSessionValidation) throws Exception {
		return CRUDHelper.ajouter(this, pSessionValidation);
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.contrat.generique.CRUDBase#mettreAJour(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public SessionValidation mettreAJour(SessionValidation pSessionValidation) throws Exception {
		return CRUDHelper.mettreAJour(this, pSessionValidation);
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.contrat.generique.CRUDBase#supprimer(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public SessionValidation supprimer(SessionValidation pSessionValidation) throws Exception {
		return CRUDHelper.supprimer(this, pSessionValidation);
	}

}
