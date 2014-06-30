package net.eni.gestion.pedagogie.service.implementation;

import java.sql.SQLException;
import java.util.ArrayList;

import net.eni.gestion.pedagogie.DAO.StagiaireDao;
import net.eni.gestion.pedagogie.commun.composant.GenericException;
import net.eni.gestion.pedagogie.modele.Stagiaire;
import net.eni.gestion.pedagogie.service.StagiaireService;

import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * @author jollivier
 * Classe d'implémentation pour le module de consultation des stagiaires
 */
@Singleton
public class StagiaireServiceImpl implements StagiaireService {

    /**
     * DAO stagiaire
     */
    private final StagiaireDao stagiaireDao;

    /**
     * Constructeur
     * @param DAO stagiaire
     * @throws SQLException
     */
    @Inject
    public StagiaireServiceImpl(StagiaireDao pStagiaireDao) throws SQLException {
        this.stagiaireDao = pStagiaireDao;
    }

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.metier.contrat.generique.CRUDUnit#charger(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public ArrayList<Stagiaire> charger(int page, int pageSize, String orderColumn, String orderDirection, String searchText)
			throws GenericException {
		try {
			return this.stagiaireDao.charger(page, pageSize, orderColumn, orderDirection, searchText);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	

	public Stagiaire chargerDetail(Integer pId) throws GenericException {
		// TODO Auto-generated method stub
		try {
			return this.stagiaireDao.chargerDetail(pId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.metier.contrat.generique.CRUDUnit#ajouter(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public Stagiaire ajouter(Stagiaire pModel) throws GenericException {
		try {
			return this.stagiaireDao.ajouter(pModel);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.metier.contrat.generique.CRUDUnit#mettreAJour(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public Stagiaire mettreAJour(Stagiaire pModel) throws GenericException {
		try {
			return this.stagiaireDao.mettreAJour(pModel);
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
			return this.stagiaireDao.supprimer(pId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
