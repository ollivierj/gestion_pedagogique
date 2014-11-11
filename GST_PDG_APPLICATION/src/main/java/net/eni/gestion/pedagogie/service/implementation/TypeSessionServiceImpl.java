package net.eni.gestion.pedagogie.service.implementation;

import java.sql.SQLException;

import net.eni.gestion.pedagogie.DAO.TypeSessionDao;
import net.eni.gestion.pedagogie.commun.modele.TypeSession;
import net.eni.gestion.pedagogie.service.TypeSessionService;

import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * @author jollivier Classe d'implémentation pour le module de suivi des
 *         titreProfessionnels
 */
@Singleton
public class TypeSessionServiceImpl extends
		AServiceImpl<TypeSession, Integer, TypeSessionDao> implements
		TypeSessionService {

	/**
	 * Constructeur
	 * 
	 * @param DAO
	 *            titreProfessionnel
	 * @throws SQLException
	 */
	@Inject
	public TypeSessionServiceImpl(TypeSessionDao pTypeSessionDao)
			throws SQLException {
		super(pTypeSessionDao);
	}

}
