package net.eni.gestion.pedagogie.DAO.implementation;

import java.sql.SQLException;

import net.eni.gestion.pedagogie.DAO.ProfessionnelHomologueDao;
import net.eni.gestion.pedagogie.commun.composant.connexion.Connexion;
import net.eni.gestion.pedagogie.commun.composant.erreur.ApplicationException;
import net.eni.gestion.pedagogie.commun.configuration.ModeleMetier;
import net.eni.gestion.pedagogie.commun.modele.Jury;
import net.eni.gestion.pedagogie.commun.modele.ProfessionnelHomologue;

import com.google.inject.Singleton;

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
	public ProfessionnelHomologueDaoImpl() throws SQLException {
		super(Connexion.getConnexion(), ProfessionnelHomologue.class);
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
		boolean instanceExist;
		try {
			instanceExist = this.queryRaw(lQuery.toString()).getFirstResult().length==1;
		} catch (SQLException e) {
			throw new ApplicationException("Echec lors de la validation en base de données");
		}
		if (!instanceExist){
			return true;
		}else {
			throw new ApplicationException("Il existe des jurys s'appuyant sur contenant ce professionnel homologué.\n Il n'est donc pas possible de supprimer ce professionel homologué");
		}
	}
}
