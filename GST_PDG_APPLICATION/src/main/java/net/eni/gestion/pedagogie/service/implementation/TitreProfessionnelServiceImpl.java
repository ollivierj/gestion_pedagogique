package net.eni.gestion.pedagogie.service.implementation;

import java.sql.SQLException;
import java.util.ArrayList;

import net.eni.gestion.pedagogie.DAO.TitreProfessionnelDao;
import net.eni.gestion.pedagogie.commun.composant.GenericException;
import net.eni.gestion.pedagogie.modele.TitreProfessionnel;
import net.eni.gestion.pedagogie.service.TitreProfessionnelService;

import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * @author jollivier
 * Classe d'implémentation pour le module de gestion des titres profesionnels
 */
@Singleton
public class TitreProfessionnelServiceImpl implements TitreProfessionnelService {

    /**
     * DAO titreProfessionnel
     */
    private final TitreProfessionnelDao titreProfessionnelDao;

    /**
     * Constructeur
     * @param DAO titreProfessionnel
     * @throws SQLException
     */
    @Inject
    public TitreProfessionnelServiceImpl(TitreProfessionnelDao pTitreProfessionnelDao) throws SQLException {
        this.titreProfessionnelDao = pTitreProfessionnelDao;
    }

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.metier.contrat.generique.CRUDUnit#charger(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public ArrayList<TitreProfessionnel> charger(TitreProfessionnel pModel)
			throws GenericException {
		try {
			return this.titreProfessionnelDao.charger(pModel);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	

	public TitreProfessionnel chargerDetail(TitreProfessionnel pModel) throws GenericException {
		// TODO Auto-generated method stub
		try {
			return this.titreProfessionnelDao.chargerDetail(pModel);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.metier.contrat.generique.CRUDUnit#ajouter(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public TitreProfessionnel ajouter(TitreProfessionnel pModel) throws GenericException {
		try {
			return this.titreProfessionnelDao.ajouter(pModel);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.metier.contrat.generique.CRUDUnit#mettreAJour(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public TitreProfessionnel mettreAJour(TitreProfessionnel pModel) throws GenericException {
		try {
			return this.titreProfessionnelDao.mettreAJour(pModel);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.metier.contrat.generique.CRUDUnit#supprimer(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public TitreProfessionnel supprimer(TitreProfessionnel pModel) throws GenericException {
		try {
			return this.titreProfessionnelDao.supprimer(pModel);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
