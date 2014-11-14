package net.eni.gestion.pedagogie.DAO.implementation;

import java.sql.SQLException;

import net.eni.gestion.pedagogie.DAO.SalleDao;
import net.eni.gestion.pedagogie.commun.composant.erreur.ApplicationException;
import net.eni.gestion.pedagogie.commun.configuration.ModeleMetier;
import net.eni.gestion.pedagogie.commun.modele.ReservationSalle;
import net.eni.gestion.pedagogie.commun.modele.Salle;

import com.google.inject.Singleton;

/**
 * @author jollivier
 * Service métier "Salle"
 */
@Singleton
public class SalleDaoImpl extends ADaoImpl<Salle, Integer> implements SalleDao{
	
	/**
	 * Constructeur de la DAO SalleBase
	 * @throws SQLException
	 */
	public SalleDaoImpl() throws SQLException {
		super(Salle.class);
	}
	
	@Override
	protected boolean validerAvantSuppression(Integer pId) throws  ApplicationException {
		StringBuilder lQuery = new StringBuilder();
		lQuery.append("SELECT TOP 1 1 FROM ");
		lQuery.append(ModeleMetier.RESERVATION_SALLE_TABLE_NAME);
		lQuery.append(" WHERE ");
		lQuery.append(ReservationSalle.SALLE_FIELD_NAME);
		lQuery.append("=");
		lQuery.append(pId);
		String[] instanceExist;
		try {
			instanceExist = this.queryRaw(lQuery.toString()).getFirstResult();
		} catch (SQLException e) {
			throw new ApplicationException("Echec lors de la validation en base de données");
		}
		if (null==instanceExist){
			return true;
		}else {
			throw new ApplicationException("Il existe au moins une réservation de salle référençant cette salle.\n Vous ne pouvez pas supprimer cette salle.");
		}
	}
	
}
