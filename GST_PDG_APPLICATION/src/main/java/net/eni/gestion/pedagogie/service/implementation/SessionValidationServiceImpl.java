package net.eni.gestion.pedagogie.service.implementation;

import java.sql.SQLException;
import java.util.ArrayList;

import net.eni.gestion.pedagogie.DAO.SessionValidationDao;
import net.eni.gestion.pedagogie.commun.composant.GenericException;
import net.eni.gestion.pedagogie.modele.SessionValidation;
import net.eni.gestion.pedagogie.service.SessionValidationService;

import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * @author jollivier
 * Classe d'implémentation pour le module de gestion des sessions de validation
 */
@Singleton
public class SessionValidationServiceImpl implements SessionValidationService {

    /**
     * DAO sessionValidation
     */
    private final SessionValidationDao sessionValidationDao;

    /**
     * Constructeur
     * @param DAO sessionValidation
     * @throws SQLException
     */
    @Inject
    public SessionValidationServiceImpl(SessionValidationDao pSessionValidationDao) throws SQLException {
        this.sessionValidationDao = pSessionValidationDao;
    }

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.metier.contrat.generique.CRUDUnit#charger(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public ArrayList<SessionValidation> charger(SessionValidation pSessionValidation)
			throws GenericException {
		try {
			return this.sessionValidationDao.charger(pSessionValidation);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	

	public SessionValidation chargerDetail(SessionValidation pModel) throws GenericException {
		// TODO Auto-generated method stub
		try {
			return this.sessionValidationDao.chargerDetail(pModel);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.metier.contrat.generique.CRUDUnit#ajouter(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public SessionValidation ajouter(SessionValidation pModel) throws GenericException {
		try {
			return this.sessionValidationDao.ajouter(pModel);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.metier.contrat.generique.CRUDUnit#mettreAJour(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public SessionValidation mettreAJour(SessionValidation pModel) throws GenericException {
		try {
			return this.sessionValidationDao.mettreAJour(pModel);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.metier.contrat.generique.CRUDUnit#supprimer(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public SessionValidation supprimer(SessionValidation pModel) throws GenericException {
		try {
			return this.sessionValidationDao.supprimer(pModel);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
