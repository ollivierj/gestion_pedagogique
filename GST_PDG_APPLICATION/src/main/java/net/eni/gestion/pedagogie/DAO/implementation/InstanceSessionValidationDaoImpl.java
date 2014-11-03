package net.eni.gestion.pedagogie.DAO.implementation;

import java.sql.SQLException;

import net.eni.gestion.pedagogie.DAO.InstanceSessionValidationDao;
import net.eni.gestion.pedagogie.commun.composant.connexion.Connexion;
import net.eni.gestion.pedagogie.modele.InstanceSessionValidation;

import com.google.inject.Singleton;

/**
 * @author jollivier
 * Service métier "InstanceSessionValidation"
 */
@Singleton
public class InstanceSessionValidationDaoImpl extends ADaoImpl<InstanceSessionValidation, Integer> implements InstanceSessionValidationDao{
		
	/**
	 * Constructeur de la DAO InstanceSessionValidationBase
	 * @throws SQLException
	 */
	public InstanceSessionValidationDaoImpl() throws SQLException {
		super(Connexion.getConnexion(), InstanceSessionValidation.class);
	}

}
