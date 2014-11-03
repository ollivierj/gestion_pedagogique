package net.eni.gestion.pedagogie.DAO.implementation;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;

import net.eni.gestion.pedagogie.DAO.ProfilDao;
import net.eni.gestion.pedagogie.commun.composant.connexion.Connexion;
import net.eni.gestion.pedagogie.modele.Profil;

import com.google.inject.Singleton;

/**
 * @author jollivier
 * Service métier "Profil"
 */
@Singleton
public class ProfilDaoImpl extends ADaoImpl<Profil, Integer> implements ProfilDao{
	
	/**
	 * Constructeur de la DAO ProfilBase
	 * @throws SQLException
	 */
	public ProfilDaoImpl() throws SQLException {
		super(Connexion.getConnexion(), Profil.class);
	}
	
	public HashMap<String, String> getTitleMap() throws Exception {
		try{
			Iterator<Profil> lProfils = this.queryForAll().iterator();
			HashMap<String, String> lResults = new HashMap<String, String>();
			while (lProfils.hasNext()) {
				Profil lProfil = lProfils.next();
				lResults.put(lProfil.getId().toString(), lProfil.getLibelle());
			}
			return lResults;
		} catch (Exception exception) {
			throw new Exception(
					"Echec de chargement de la liste d'enregistrements depuis la base de données");
		}
	}
	
}
