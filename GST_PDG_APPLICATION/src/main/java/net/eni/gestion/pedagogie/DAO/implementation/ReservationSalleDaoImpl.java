package net.eni.gestion.pedagogie.DAO.implementation;

import java.sql.SQLException;
import java.util.ArrayList;
import net.eni.gestion.pedagogie.DAO.ReservationSalleDao;
import net.eni.gestion.pedagogie.commun.composant.Connexion;
import net.eni.gestion.pedagogie.commun.outil.CRUDHelper;
import net.eni.gestion.pedagogie.modele.ReservationSalle;
import com.google.inject.Singleton;
import com.j256.ormlite.dao.BaseDaoImpl;

/**
 * @author jollivier
 * Service métier "ReservationSalle"
 */
@Singleton
public class ReservationSalleDaoImpl extends BaseDaoImpl<ReservationSalle, Integer> implements ReservationSalleDao{
	
	
	/**
	 * Constructeur de la DAO ReservationSalleBase
	 * @throws SQLException
	 */
	public ReservationSalleDaoImpl() throws SQLException {
		super(Connexion.getConnexion(), ReservationSalle.class);
	}
	
	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.contrat.generique.CRUDBase#charger(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public ArrayList<ReservationSalle> charger(ReservationSalle pReservationSalle) throws Exception {
		return CRUDHelper.charger(this, pReservationSalle);
	}
	
	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.generique.CRUDBase#chargerDetail(net.eni.gestion.pedagogie.modele.ReservationSalle)
	 */
	public ReservationSalle chargerDetail(ReservationSalle pReservationSalle) throws Exception {
		return CRUDHelper.chargerDetail(this, pReservationSalle);
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.contrat.generique.CRUDBase#ajouter(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public ReservationSalle ajouter(ReservationSalle pReservationSalle) throws Exception {
		return CRUDHelper.ajouter(this, pReservationSalle);
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.contrat.generique.CRUDBase#mettreAJour(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public ReservationSalle mettreAJour(ReservationSalle pReservationSalle) throws Exception {
		return CRUDHelper.mettreAJour(this, pReservationSalle);
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.base.contrat.generique.CRUDBase#supprimer(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public ReservationSalle supprimer(ReservationSalle pReservationSalle) throws Exception {
		return CRUDHelper.supprimer(this, pReservationSalle);
	}

}
