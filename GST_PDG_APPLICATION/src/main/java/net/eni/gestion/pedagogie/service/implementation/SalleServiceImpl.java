package net.eni.gestion.pedagogie.service.implementation;

import java.sql.SQLException;

import net.eni.gestion.pedagogie.DAO.SalleDao;
import net.eni.gestion.pedagogie.commun.modele.Salle;
import net.eni.gestion.pedagogie.service.SalleService;

import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * @author jollivier Classe d'implémentation pour le module de suivi des aviss
 */
@Singleton
public class SalleServiceImpl extends AServiceImpl<Salle, Integer, SalleDao>
		implements SalleService {

	/**
	 * Constructeur
	 * 
	 * @param DAO
	 *            avis
	 * @throws SQLException
	 */
	@Inject
	public SalleServiceImpl(SalleDao pSalleDao) throws SQLException {
		super(pSalleDao);
	}

}
