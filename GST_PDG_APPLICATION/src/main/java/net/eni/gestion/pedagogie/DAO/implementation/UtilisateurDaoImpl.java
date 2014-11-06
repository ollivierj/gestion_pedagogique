package net.eni.gestion.pedagogie.DAO.implementation;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import net.eni.gestion.pedagogie.DAO.UtilisateurDao;
import net.eni.gestion.pedagogie.commun.modele.Profil;
import net.eni.gestion.pedagogie.commun.composant.connexion.Connexion;
import net.eni.gestion.pedagogie.commun.modele.Utilisateur;

import org.apache.commons.lang3.time.DateFormatUtils;

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
	
	public Integer checkConnection(String pLogin, String pMotdePasse, boolean loginOnly) throws Exception{
		try {
			StringBuilder lQuery = new StringBuilder();
			lQuery.append("SELECT TOP 1 UTIL_ID ");
			lQuery.append(" FROM UTILISATEUR");
			lQuery.append(" WHERE UTILISATEUR.UTIL_LOGIN = '");
			lQuery.append(pLogin);
			lQuery.append("'");
			if(!loginOnly){
				lQuery.append("' AND UTILISATEUR.UTIL_MOT_PASSE = '");
				lQuery.append(pMotdePasse);
				lQuery.append("'");
			}
			String[] lResult =  this.queryRaw(lQuery.toString()).getFirstResult();
			if (null != lResult){
				return Integer.parseInt(lResult[0]);
			}else {
				return null;
			}
		} catch (Exception exception) {
			throw new Exception("Echec de chargement de la liste d'enregistrements depuis la base de données");
		}
	}
	
	public boolean checkToken(String token) throws Exception{
		try {
			StringBuilder lQuery = new StringBuilder();
			lQuery.append("SELECT TOP 1 UTIL_ID ");
			lQuery.append(" FROM UTILISATEUR");
			lQuery.append(" WHERE ");
			lQuery.append(Utilisateur.TOKEN_FIELD_NAME);
			lQuery.append("=");
			lQuery.append("'");
			lQuery.append(token);
			lQuery.append("'");
			lQuery.append(" AND ");
			lQuery.append(Utilisateur.DATE_EXPIRATION_FIELD_NAME);
			lQuery.append(">");
			lQuery.append("'");
			lQuery.append(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
			lQuery.append("'");
			return (this.queryRaw(lQuery.toString()).getFirstResult().length==1);
		} catch (Exception exception) {
			throw new Exception("Echec de chargement de la liste d'enregistrements depuis la base de données");
		}
	}
	public Utilisateur loginwithtoken(String token) throws Exception{
		try {
			StringBuilder lQuery = new StringBuilder();
			lQuery.append("SELECT TOP 1 * ");
			lQuery.append(" FROM UTILISATEUR");
			lQuery.append(" WHERE ");
			lQuery.append(Utilisateur.TOKEN_FIELD_NAME);
			lQuery.append("=");
			lQuery.append("'");
			lQuery.append(token);
			lQuery.append("'");
			lQuery.append(" AND ");
			lQuery.append(Utilisateur.DATE_EXPIRATION_FIELD_NAME);
			lQuery.append(">");
			lQuery.append("'");
			lQuery.append(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
			lQuery.append("'");
			return this.queryRaw(lQuery.toString(), this.getRawRowMapper()).getFirstResult();
		} catch (Exception exception) {
			throw new Exception("Echec de chargement de la liste d'enregistrements depuis la base de données");
		}
	}

	@Override
	public List<Utilisateur> getByProfil(Integer profilId) throws Exception {
		List<Utilisateur> utilisateur = null;
		
		try {
			utilisateur = this.queryBuilder().where().eq(Utilisateur.PROFIL_FIELD_NAME, new Profil(profilId)).query();
		} catch (Exception exception) {
			throw new Exception("Echec de chargement de la liste d'enregistrements depuis la base de données");
		}
		
		return utilisateur;
	}

}
