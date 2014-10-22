package net.eni.gestion.pedagogie.DAO.implementation;

import java.sql.SQLException;
import java.util.List;

import net.eni.gestion.pedagogie.DAO.DroitDao;
import net.eni.gestion.pedagogie.commun.composant.Connexion;
import net.eni.gestion.pedagogie.modele.Droit;

import com.google.inject.Singleton;
import com.j256.ormlite.dao.GenericRawResults;

/**
 * @author jollivier
 * Service métier "Droit"
 */
@Singleton
public class DroitDaoImpl extends ADaoImpl<Droit, Integer> implements DroitDao{

	
	/**
	 * Constructeur de la DAO DroitBase
	 * @throws SQLException
	 */
	public DroitDaoImpl() throws SQLException {
		super(Connexion.getConnexion(), Droit.class);
	}

	@Override
	public List<String[]> chargerParIdProfil(Integer id) throws Exception {
		
		
		List<String[]> droitProfil;
		
		try {
			StringBuilder lQuery = new StringBuilder();
			lQuery.append("SELECT DRT_CODE ");
			lQuery.append(" FROM DROIT");
			lQuery.append(" INNER JOIN DROIT_PROFIL");
			lQuery.append(" ON DROIT_PROFIL.DRT_PRF_DROIT = DROIT.DRT_ID");
			lQuery.append(" WHERE DROIT_PROFIL.DRT_PRF_PROFIL = '"+id+"'");
			 GenericRawResults<String[]> result = this.queryRaw(lQuery.toString());
			droitProfil = result.getResults();
		} catch (Exception exception) {
			throw new Exception("Echec de chargement de la liste d'enregistrements depuis la base de données");
		}
		
		if(droitProfil == null){
			return null;
		}
		return droitProfil;
		
		
	}


}
