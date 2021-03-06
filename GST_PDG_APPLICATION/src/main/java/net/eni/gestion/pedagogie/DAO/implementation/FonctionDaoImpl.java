package net.eni.gestion.pedagogie.DAO.implementation;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;

import net.eni.gestion.pedagogie.DAO.FonctionDao;
import net.eni.gestion.pedagogie.commun.composant.erreur.ApplicationException;
import net.eni.gestion.pedagogie.commun.modele.Fonction;

import com.google.inject.Singleton;

/**
 * @author jollivier
 * Service métier "Fonction"
 */
@Singleton
public class FonctionDaoImpl extends ADaoImpl<Fonction, String> implements FonctionDao{
	
	/**
	 * Constructeur de la DAO FonctionBase
	 * @throws SQLException
	 */
	public FonctionDaoImpl() throws SQLException {
		super(Fonction.class);
	}
	
	public HashMap<String, String> getTitleMap() throws ApplicationException {
		try{
			Iterator<Fonction> lFonctions = this.queryForAll().iterator();
			HashMap<String, String> lResults = new HashMap<String, String>();
			while (lFonctions.hasNext()) {
				Fonction lFonction = lFonctions.next();
				lResults.put(lFonction.getId().toString(), lFonction.getLibelle());
			}
			return lResults;
		} catch (Exception exception) {
			throw new ApplicationException(
					"Echec de chargement de la liste d'enregistrements depuis la base de données");
		}
	}
	
	
}
