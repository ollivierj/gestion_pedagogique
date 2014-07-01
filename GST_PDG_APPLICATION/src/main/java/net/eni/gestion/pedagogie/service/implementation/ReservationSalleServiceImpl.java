package net.eni.gestion.pedagogie.service.implementation;

import java.sql.SQLException;

import net.eni.gestion.pedagogie.DAO.ReservationSalleDao;
import net.eni.gestion.pedagogie.modele.ReservationSalle;
import net.eni.gestion.pedagogie.service.ReservationSalleService;

import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * @author jollivier
 * Classe d'implémentation pour le module de suivi des reservationSalles
 */
@Singleton
public class ReservationSalleServiceImpl extends AServiceImpl<ReservationSalle, Integer, ReservationSalleDao> implements ReservationSalleService {

       /**
     * Constructeur
     * @param DAO reservationSalle
     * @throws SQLException
     */
    @Inject
    public ReservationSalleServiceImpl(ReservationSalleDao pReservationSalleDao) throws SQLException {
        super(pReservationSalleDao);
    }
    
}
