package net.eni.gestion.pedagogie.DAO.implementation;

import java.sql.SQLException;

import net.eni.gestion.pedagogie.DAO.InstanceSessionValidationStagiaireDao;
import net.eni.gestion.pedagogie.commun.composant.Connexion;
import net.eni.gestion.pedagogie.modele.InstanceSessionValidationStagiaire;

import com.google.inject.Singleton;

/**
 * @author jollivier
 * Service métier "InstanceSessionValidationStagiaire"
 */
@Singleton
public class InstanceSessionValidationStagiaireDaoImpl extends ADaoImpl<InstanceSessionValidationStagiaire, Integer> implements InstanceSessionValidationStagiaireDao{
	
	/**
	 * Constructeur de la DAO InstanceSessionValidationStagiaireBase
	 * @throws SQLException
	 */
	public InstanceSessionValidationStagiaireDaoImpl() throws SQLException {
		super(Connexion.getConnexion(), InstanceSessionValidationStagiaire.class);
	}

}
