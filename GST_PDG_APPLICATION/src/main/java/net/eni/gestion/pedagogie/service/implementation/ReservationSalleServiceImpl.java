package net.eni.gestion.pedagogie.service.implementation;

import java.sql.SQLException;
import java.util.ArrayList;

import net.eni.gestion.pedagogie.DAO.ReservationSalleDao;
import net.eni.gestion.pedagogie.commun.composant.GenericException;
import net.eni.gestion.pedagogie.modele.ReservationSalle;
import net.eni.gestion.pedagogie.service.ReservationSalleService;

import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * @author jollivier
 * Classe d'implémentation pour le module de réservation des salles
 */
@Singleton
public class ReservationSalleServiceImpl implements ReservationSalleService {

    /**
     * DAO reservationSalle
     */
    private final ReservationSalleDao reservationSalleDao;

    /**
     * Constructeur
     * @param DAO reservationSalle
     * @throws SQLException
     */
    @Inject
    public ReservationSalleServiceImpl(ReservationSalleDao pReservationSalleDao) throws SQLException {
        this.reservationSalleDao = pReservationSalleDao;
    }

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.metier.contrat.generique.CRUDUnit#charger(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public ArrayList<ReservationSalle> charger(ReservationSalle pModel)
			throws GenericException {
		try {
			return this.reservationSalleDao.charger(pModel);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	

	public ReservationSalle chargerDetail(ReservationSalle pModel) throws GenericException {
		// TODO Auto-generated method stub
		try {
			return this.reservationSalleDao.chargerDetail(pModel);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.metier.contrat.generique.CRUDUnit#ajouter(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public ReservationSalle ajouter(ReservationSalle pModel) throws GenericException {
		try {
			return this.reservationSalleDao.ajouter(pModel);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.metier.contrat.generique.CRUDUnit#mettreAJour(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public ReservationSalle mettreAJour(ReservationSalle pModel) throws GenericException {
		try {
			return this.reservationSalleDao.mettreAJour(pModel);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.metier.contrat.generique.CRUDUnit#supprimer(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public ReservationSalle supprimer(ReservationSalle pModel) throws GenericException {
		try {
			return this.reservationSalleDao.supprimer(pModel);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
