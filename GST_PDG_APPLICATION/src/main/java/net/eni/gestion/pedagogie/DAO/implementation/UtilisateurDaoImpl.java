package net.eni.gestion.pedagogie.DAO.implementation;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;

import net.eni.gestion.pedagogie.DAO.UtilisateurDao;
import net.eni.gestion.pedagogie.commun.composant.Connexion;
import net.eni.gestion.pedagogie.modele.Utilisateur;

import com.google.inject.Singleton;

/**
 * @author jollivier
 * Service métier "Utilisateur"
 */
@Singleton
public class UtilisateurDaoImpl extends ADaoImpl<Utilisateur, Integer> implements UtilisateurDao{
	
	/**
	 * Constructeur de la DAO UtilisateurBase
	 * @throws SQLException
	 */
	public UtilisateurDaoImpl() throws SQLException {
		super(Connexion.getConnexion(), Utilisateur.class);
	}
	
	public HashMap<String, String> getTitleMap() throws Exception {
		try{
			Iterator<Utilisateur> lUtilisateurs = this.queryForAll().iterator();
			HashMap<String, String> lResults = new HashMap<String, String>();
			while (lUtilisateurs.hasNext()) {
				Utilisateur lUtilisateur = lUtilisateurs.next();
				StringBuilder lStrBuilder = new StringBuilder();
				lStrBuilder.append(lUtilisateur.getPrenom());
				lStrBuilder.append(" ");
				lStrBuilder.append(lUtilisateur.getNom());
				lResults.put(lUtilisateur.getId().toString(), lStrBuilder.toString());
			}
			return lResults;
		} catch (Exception exception) {
			throw new Exception(
					"Echec de chargement de la liste d'enregistrements depuis la base de données");
		}
	}	

}
