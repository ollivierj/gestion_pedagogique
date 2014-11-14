package net.eni.gestion.pedagogie.DAO.implementation;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;

import net.eni.gestion.pedagogie.DAO.TypeSessionDao;
import net.eni.gestion.pedagogie.commun.composant.erreur.ApplicationException;
import net.eni.gestion.pedagogie.commun.configuration.ModeleMetier;
import net.eni.gestion.pedagogie.commun.modele.SessionValidation;
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
		super(TypeSession.class);
	}

	public HashMap<String, String> getTitleMap() throws ApplicationException {
		try{
			Iterator<TypeSession> lTypeSessions = this.queryForAll().iterator();
			HashMap<String, String> lResults = new HashMap<String, String>();
			while (lTypeSessions.hasNext()) {
				TypeSession lTitrePorfessionnel = lTypeSessions.next();
				lResults.put(lTitrePorfessionnel.getId().toString(), lTitrePorfessionnel.getCode());
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
		lQuery.append(ModeleMetier.SESSION_VALIDATION_TABLE_NAME);
		lQuery.append(" WHERE ");
		lQuery.append(SessionValidation.TYPE_SESSION_FIELD_NAME);
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
			throw new ApplicationException("Il existe au moins une session de validationréférençant ce type de session.\n Vous ne pouvez pas supprimer ce type de session.");
		}
	}
	

}
