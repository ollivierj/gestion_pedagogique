package net.eni.gestion.pedagogie.DAO.implementation;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;

import net.eni.gestion.pedagogie.DAO.ProfilDao;
import net.eni.gestion.pedagogie.commun.composant.connexion.Connexion;
import net.eni.gestion.pedagogie.commun.composant.erreur.ApplicationException;
import net.eni.gestion.pedagogie.commun.configuration.ModeleMetier;
import net.eni.gestion.pedagogie.commun.modele.Profil;
import net.eni.gestion.pedagogie.commun.modele.Utilisateur;

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
	
	@Override
	protected boolean validerAvantSuppression(Integer pId) throws  ApplicationException {
		StringBuilder lQuery = new StringBuilder();
		lQuery.append("SELECT TOP 1 1 FROM ");
		lQuery.append(ModeleMetier.UTILISATEUR_TABLE_NAME);
		lQuery.append(" WHERE ");
		lQuery.append(Utilisateur.PROFIL_FIELD_NAME);
		lQuery.append("=");
		lQuery.append(pId);
		boolean instanceExist;
		try {
			instanceExist = this.queryRaw(lQuery.toString()).getFirstResult().length==1;
		} catch (SQLException e) {
			throw new ApplicationException("Echec lors de la validation en base de données");
		}
		if (!instanceExist){
			return true;
		}else {
			throw new ApplicationException("Il existe au moins une instance d'évaluation déclarée pour cette évaluation.\n Il n'est donc pas possible de supprimer cette évaluation");
		}
	}
	
}
