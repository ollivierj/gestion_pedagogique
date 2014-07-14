package net.eni.gestion.pedagogie.DAO.implementation;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;

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

	public HashMap<Integer, String> getTitleMap() throws Exception {
		try{
			Iterator<TitreProfessionnel> lTitreProfessionnels = this.queryForAll().iterator();
			HashMap<Integer, String> lResults = new HashMap<Integer, String>();
			while (lTitreProfessionnels.hasNext()) {
				TitreProfessionnel lTitrePorfessionnel = lTitreProfessionnels.next();
				lResults.put(lTitrePorfessionnel.getId(), lTitrePorfessionnel.getCode());
			}
			return lResults;
		} catch (Exception exception) {
			throw new Exception(
					"Echec de chargement de la liste d'enregistrements depuis la base de données");
		}
	}	
	

}
