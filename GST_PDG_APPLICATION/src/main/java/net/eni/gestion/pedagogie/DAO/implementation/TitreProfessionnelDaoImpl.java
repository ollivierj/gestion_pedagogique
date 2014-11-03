package net.eni.gestion.pedagogie.DAO.implementation;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;

import net.eni.gestion.pedagogie.DAO.TitreProfessionnelDao;
import net.eni.gestion.pedagogie.commun.composant.connexion.Connexion;
import net.eni.gestion.pedagogie.commun.modele.TitreProfessionnel;

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

	public HashMap<String, String> getTitleMap() throws Exception {
		try{
			Iterator<TitreProfessionnel> lTitreProfessionnels = this.queryForAll().iterator();
			HashMap<String, String> lResults = new HashMap<String, String>();
			while (lTitreProfessionnels.hasNext()) {
				TitreProfessionnel lTitrePorfessionnel = lTitreProfessionnels.next();
				lResults.put(lTitrePorfessionnel.getId().toString(), lTitrePorfessionnel.getCode());
			}
			return lResults;
		} catch (Exception exception) {
			throw new Exception(
					"Echec de chargement de la liste d'enregistrements depuis la base de données");
		}
	}	
	

}
