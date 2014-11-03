package net.eni.gestion.pedagogie.service.implementation;

import java.sql.SQLException;
import java.util.List;

import net.eni.gestion.pedagogie.DAO.ReservationSalleDao;
import net.eni.gestion.pedagogie.DAO.SalleDao;
import net.eni.gestion.pedagogie.commun.composant.GenericException;
import net.eni.gestion.pedagogie.modele.ReservationSalle;
import net.eni.gestion.pedagogie.modele.Salle;
import net.eni.gestion.pedagogie.service.ReservationSalleService;

import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * @author jollivier
 * Classe d'implémentation pour le module de suivi des reservationSalles
 */
@Singleton
public class ReservationSalleServiceImpl extends AServiceImpl<ReservationSalle, Integer, ReservationSalleDao> implements ReservationSalleService {

	protected SalleDao salleDao;
	
   /**
     * Constructeur
     * @param DAO reservationSalle
     * @throws SQLException
     */
    @Inject
    public ReservationSalleServiceImpl(ReservationSalleDao pReservationSalleDao, SalleDao salleDao) throws SQLException {
        super(pReservationSalleDao);
        this.salleDao = salleDao;
    }

	@Override
	public List<Salle> getSalles() throws GenericException {
		try {
			return salleDao.chargerTous();
		} catch (Exception e) {
			throw new GenericException("Impossible de récupérer toutes les salles");
		}
	}
    
	
    
}
