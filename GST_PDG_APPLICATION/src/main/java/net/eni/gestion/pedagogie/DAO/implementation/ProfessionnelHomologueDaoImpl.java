package net.eni.gestion.pedagogie.DAO.implementation;

import java.sql.SQLException;

import net.eni.gestion.pedagogie.DAO.ProfessionnelHomologueDao;
import net.eni.gestion.pedagogie.commun.composant.erreur.ApplicationException;
import net.eni.gestion.pedagogie.commun.configuration.ModeleMetier;
import net.eni.gestion.pedagogie.commun.modele.Jury;
import net.eni.gestion.pedagogie.commun.modele.ProfessionnelHomologue;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.j256.ormlite.support.ConnectionSource;

/**
 * @author jollivier
 * Service métier "ProfessionnelHomologue"
 */
@Singleton
public class ProfessionnelHomologueDaoImpl extends ADaoImpl<ProfessionnelHomologue, Integer> implements ProfessionnelHomologueDao{
	
	/**
	 * Constructeur de la DAO ProfessionnelHomologueBase
	 * @throws SQLException
	 */
	@Inject
	public ProfessionnelHomologueDaoImpl(Provider<ConnectionSource> connection) throws SQLException {
		super(connection, ProfessionnelHomologue.class);
	}

	
	@Override
	protected boolean validerAvantSuppression(Integer pId) throws  ApplicationException {
		StringBuilder lQuery = new StringBuilder();
		lQuery.append("SELECT TOP 1 1 FROM ");
		lQuery.append(ModeleMetier.JURY_TABLE_NAME);
		lQuery.append(" WHERE ");
		lQuery.append(Jury.PROFESSIONNEL_HOMOLOGUE_FIELD_NAME);
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
			throw new ApplicationException("Il existe des jurys s'appuyant sur contenant ce professionnel homologué.\n Il n'est donc pas possible de supprimer ce professionel homologué");
		}
	}
}
