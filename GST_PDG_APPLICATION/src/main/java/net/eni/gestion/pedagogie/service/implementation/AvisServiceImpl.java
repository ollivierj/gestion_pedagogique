package net.eni.gestion.pedagogie.service.implementation;

import java.sql.SQLException;
import java.util.ArrayList;

import net.eni.gestion.pedagogie.DAO.AvisDao;
import net.eni.gestion.pedagogie.commun.composant.GenericException;
import net.eni.gestion.pedagogie.modele.Avis;
import net.eni.gestion.pedagogie.service.AvisService;

import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * @author jollivier
 * Classe d'implémentation pour le module de suivi des avis
 */
@Singleton
public class AvisServiceImpl implements AvisService {

    /**
     * DAO avis
     */
    private final AvisDao avisDao;

    /**
     * Constructeur
     * @param DAO avis
     * @throws SQLException
     */
    @Inject
    public AvisServiceImpl(AvisDao pAvisDao) throws SQLException {
        this.avisDao = pAvisDao;
    }

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.metier.contrat.generique.CRUDUnit#charger(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public ArrayList<Avis> charger(int page, int pageSize, String orderColumn, String orderDirection, String searchText)
			throws GenericException {
		try {
			return this.avisDao.charger(page, pageSize, orderColumn, orderDirection, searchText);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	

	public Avis chargerDetail(Integer pId) throws GenericException {
		// TODO Auto-generated method stub
		try {
			return this.avisDao.chargerDetail(pId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.metier.contrat.generique.CRUDUnit#ajouter(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public Avis ajouter(Avis pModel) throws GenericException {
		try {
			return this.avisDao.ajouter(pModel);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.metier.contrat.generique.CRUDUnit#mettreAJour(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public Avis mettreAJour(Avis pModel) throws GenericException {
		try {
			return this.avisDao.mettreAJour(pModel);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.metier.contrat.generique.CRUDUnit#supprimer(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public Integer supprimer(Integer pId) throws GenericException {
		try {
			return this.avisDao.supprimer(pId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
