package net.eni.gestion.pedagogie.DAO.implementation;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.eni.gestion.pedagogie.DAO.PlanningDao;
import net.eni.gestion.pedagogie.commun.composant.erreur.ApplicationException;
import net.eni.gestion.pedagogie.commun.configuration.ModeleMetier;
import net.eni.gestion.pedagogie.commun.modele.Planning;
import net.eni.gestion.pedagogie.commun.outil.ORMLiteHelper;

import org.apache.commons.lang3.StringUtils;

public class PlanningDaoImpl extends ADaoImpl<Planning, Long> implements PlanningDao {

	protected PlanningDaoImpl() throws SQLException {
		super( Planning.class);
	}

	@Override
	public List<Planning> charger(String dateDebut, String dateFin) throws ApplicationException {
		try {
			if (dateDebut != null && dateFin != null) {
				
				StringBuilder lQuery = new StringBuilder();
				lQuery.append("SELECT ");
				lQuery.append(StringUtils.join(ORMLiteHelper.getProjectionFields(this.getTableInfo()), ","));
				lQuery.append(" FROM ");
				lQuery.append(ModeleMetier.PLANNING_VIEW_NAME);
				lQuery.append(" WHERE ");
				lQuery.append(Planning.DATE_DEBUT_FIELD_NAME);
				lQuery.append(" >= '");
				lQuery.append(dateDebut);
				lQuery.append("' AND ");
				lQuery.append(Planning.DATE_DEBUT_FIELD_NAME);
				lQuery.append(" < '");
				lQuery.append(dateFin);
				lQuery.append("'");
				return new ArrayList<Planning>(this.queryRaw(lQuery.toString(), this.getRawRowMapper()).getResults());

			} else {
				throw new ApplicationException("Date de début et date de fin absents");
			}
				
			
		} catch (Exception exception) {
			throw new ApplicationException("Echec de chargement de la liste des évaluations depuis la base de données");
		}
	}


}
