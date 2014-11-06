package net.eni.gestion.pedagogie.DAO.implementation;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;

import net.eni.gestion.pedagogie.DAO.TypeSessionDao;
import net.eni.gestion.pedagogie.commun.composant.connexion.Connexion;
import net.eni.gestion.pedagogie.commun.modele.TypeSession;

import com.google.inject.Singleton;

/**
 * @author jollivier
 * Service métier "TypeSession"
 */
@Singleton
public class TypeSessionDaoImpl extends ADaoImpl<TypeSession, Integer> implements TypeSessionDao{
	
	/**
	 * Constructeur de la DAO TypeSessionBase
	 * @throws SQLException
	 */
	public TypeSessionDaoImpl() throws SQLException {
		super(Connexion.getConnexion(), TypeSession.class);
	}

	public HashMap<String, String> getTitleMap() throws Exception {
		try{
			Iterator<TypeSession> lTypeSessions = this.queryForAll().iterator();
			HashMap<String, String> lResults = new HashMap<String, String>();
			while (lTypeSessions.hasNext()) {
				TypeSession lTitrePorfessionnel = lTypeSessions.next();
				lResults.put(lTitrePorfessionnel.getId().toString(), lTitrePorfessionnel.getCode());
			}
			return lResults;
		} catch (Exception exception) {
			throw new Exception(
					"Echec de chargement de la liste d'enregistrements depuis la base de données");
		}
	}	
	

}
