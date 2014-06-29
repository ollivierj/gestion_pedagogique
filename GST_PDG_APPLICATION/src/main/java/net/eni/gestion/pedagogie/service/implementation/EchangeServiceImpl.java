package net.eni.gestion.pedagogie.service.implementation;

import java.sql.SQLException;
import java.util.ArrayList;

import net.eni.gestion.pedagogie.DAO.EchangeDao;
import net.eni.gestion.pedagogie.commun.composant.GenericException;
import net.eni.gestion.pedagogie.modele.Echange;
import net.eni.gestion.pedagogie.service.EchangeService;

import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * @author jollivier
 * Classe d'implémentation pour le module de suivi des echanges
 */
@Singleton
public class EchangeServiceImpl implements EchangeService {

    /**
     * DAO echange
     */
    private final EchangeDao echangeDao;

    /**
     * Constructeur
     * @param DAO echange
     * @throws SQLException
     */
    @Inject
    public EchangeServiceImpl(EchangeDao pEchangeDao) throws SQLException {
        this.echangeDao = pEchangeDao;
    }

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.metier.contrat.generique.CRUDUnit#charger(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public ArrayList<Echange> charger(Echange pEchange)
			throws GenericException {
		try {
			return this.echangeDao.charger(pEchange);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	

	public Echange chargerDetail(Echange pModel) throws GenericException {
		// TODO Auto-generated method stub
		try {
			return this.echangeDao.chargerDetail(pModel);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.metier.contrat.generique.CRUDUnit#ajouter(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public Echange ajouter(Echange pModel) throws GenericException {
		try {
			return this.echangeDao.ajouter(pModel);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.metier.contrat.generique.CRUDUnit#mettreAJour(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public Echange mettreAJour(Echange pModel) throws GenericException {
		try {
			return this.echangeDao.mettreAJour(pModel);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.metier.contrat.generique.CRUDUnit#supprimer(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public Echange supprimer(Echange pModel) throws GenericException {
		try {
			return this.echangeDao.supprimer(pModel);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
