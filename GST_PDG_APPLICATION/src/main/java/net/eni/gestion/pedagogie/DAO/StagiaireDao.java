package net.eni.gestion.pedagogie.DAO;

import java.sql.SQLException;

import net.eni.gestion.pedagogie.modele.Stagiaire;

/**
 * @author jollivier
 * Description des requêtes d'accès (écriture, lecture) en base de données 
 * pour la gestion des stagiaires
 */
public interface StagiaireDao extends ADao<Stagiaire, Integer> {
	public String getPhoto(Integer pId) throws SQLException;
}
