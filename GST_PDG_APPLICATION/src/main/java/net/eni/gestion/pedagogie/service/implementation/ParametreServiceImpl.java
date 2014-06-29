package net.eni.gestion.pedagogie.service.implementation;

import java.sql.SQLException;
import java.util.ArrayList;

import net.eni.gestion.pedagogie.DAO.ParametreDao;
import net.eni.gestion.pedagogie.commun.composant.GenericException;
import net.eni.gestion.pedagogie.modele.Parametre;
import net.eni.gestion.pedagogie.service.ParametreService;

import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * @author jollivier
 * Classe d'implémentation pour le module de gestion des parametres
 */
@Singleton
public class ParametreServiceImpl implements ParametreService {

    /**
     * DAO parametre
     */
    private final ParametreDao parametreDao;

    /**
     * Constructeur
     * @param DAO parametre
     * @throws SQLException
     */
    @Inject
    public ParametreServiceImpl(ParametreDao pParametreDao) throws SQLException {
        this.parametreDao = pParametreDao;
    }

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.metier.contrat.generique.CRUDUnit#charger(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public ArrayList<Parametre> charger(Parametre pParametre)
			throws GenericException {
		try {
			return this.parametreDao.charger(pParametre);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	

	public Parametre chargerDetail(Parametre pModel) throws GenericException {
		// TODO Auto-generated method stub
		try {
			return this.parametreDao.chargerDetail(pModel);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.metier.contrat.generique.CRUDUnit#ajouter(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public Parametre ajouter(Parametre pModel) throws GenericException {
		try {
			return this.parametreDao.ajouter(pModel);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.metier.contrat.generique.CRUDUnit#mettreAJour(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public Parametre mettreAJour(Parametre pModel) throws GenericException {
		try {
			return this.parametreDao.mettreAJour(pModel);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.metier.contrat.generique.CRUDUnit#supprimer(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public Parametre supprimer(Parametre pModel) throws GenericException {
		try {
			return this.parametreDao.supprimer(pModel);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
