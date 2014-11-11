package net.eni.gestion.pedagogie.DAO.implementation;

import java.sql.SQLException;

import net.eni.gestion.pedagogie.DAO.AvisDao;
import net.eni.gestion.pedagogie.commun.composant.connexion.Connexion;
import net.eni.gestion.pedagogie.commun.modele.Avis;

import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * @author jollivier
 * Service métier "Avis"
 */
@Singleton
public class AvisDaoImpl extends ADaoImpl<Avis, Integer> implements AvisDao{
	
	/**
	 * Constructeur de la DAO AvisBase
	 * @throws SQLException
	 */
	@Inject
	public AvisDaoImpl(Connexion pConnexion) throws SQLException {
		super( Avis.class, pConnexion);
	}

}
