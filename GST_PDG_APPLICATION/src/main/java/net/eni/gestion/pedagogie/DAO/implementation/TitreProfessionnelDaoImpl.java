package net.eni.gestion.pedagogie.DAO.implementation;

import java.sql.SQLException;

import net.eni.gestion.pedagogie.DAO.TitreProfessionnelDao;
import net.eni.gestion.pedagogie.commun.composant.Connexion;
import net.eni.gestion.pedagogie.modele.TitreProfessionnel;

import com.google.inject.Singleton;

/**
 * @author jollivier
 * Service métier "TitreProfessionnel"
 */
@Singleton
public class TitreProfessionnelDaoImpl extends ADaoImpl<TitreProfessionnel, Integer> implements TitreProfessionnelDao{
	
	/**
	 * Constructeur de la DAO TitreProfessionnelBase
	 * @throws SQLException
	 */
	public TitreProfessionnelDaoImpl() throws SQLException {
		super(Connexion.getConnexion(), TitreProfessionnel.class);
	}

}
