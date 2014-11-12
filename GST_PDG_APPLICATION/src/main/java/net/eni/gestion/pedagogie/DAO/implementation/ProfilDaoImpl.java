package net.eni.gestion.pedagogie.DAO.implementation;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;

import net.eni.gestion.pedagogie.DAO.ProfilDao;
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
		super(Profil.class);
	}
	
	public HashMap<String, String> getTitleMap() throws ApplicationException {
		try{
			Iterator<Profil> lProfils = this.queryForAll().iterator();
			HashMap<String, String> lResults = new HashMap<String, String>();
			while (lProfils.hasNext()) {
				Profil lProfil = lProfils.next();
				lResults.put(lProfil.getId().toString(), lProfil.getLibelle());
			}
			return lResults;
		} catch (Exception exception) {
			throw new ApplicationException(
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
		String[] instanceExist;
		try {
			instanceExist = this.queryRaw(lQuery.toString()).getFirstResult();
		} catch (SQLException e) {
			throw new ApplicationException("Echec lors de la validation en base de données");
		}
		if (null==instanceExist){
			return true;
		}else {
			throw new ApplicationException("Il existe au moins un utilisateur pour ce profil.\n Vous devez vous assurer que le profil n'est pas utilisé par un utilisateur");
		}
	}
	
}
