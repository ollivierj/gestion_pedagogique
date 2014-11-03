package net.eni.gestion.pedagogie.service.implementation;

import net.eni.gestion.pedagogie.DAO.HomologationDao;
import net.eni.gestion.pedagogie.DAO.ProfessionnelHomologueDao;
import net.eni.gestion.pedagogie.commun.composant.erreur.ApplicationException;
import net.eni.gestion.pedagogie.commun.modele.ProfessionnelHomologue;
import net.eni.gestion.pedagogie.service.ProfessionnelHomologueService;

import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * @author jollivier Classe d'implémentation pour le module de suivi des
 *         professionnelHomologues
 */
@Singleton
public class ProfessionnelHomologueServiceImpl
		extends
		AServiceImpl<ProfessionnelHomologue, Integer, ProfessionnelHomologueDao>
		implements ProfessionnelHomologueService {

	protected final HomologationDao homologationDao;

	/**
	 * Constructeur
	 * 
	 * @param pDao
	 * @param homologationDao
	 */
	@Inject
	public ProfessionnelHomologueServiceImpl(ProfessionnelHomologueDao pDao,
			HomologationDao homologationDao) {
		super(pDao);
		this.homologationDao = homologationDao;
	}

	@Override
	public ProfessionnelHomologue chargerDetail(Integer pId)
			throws ApplicationException {
		ProfessionnelHomologue lProfessionnelHomologue = super
				.chargerDetail(pId);
		lProfessionnelHomologue.getHomologations();
		return lProfessionnelHomologue;
	}

	@Override
	public ProfessionnelHomologue ajouter(ProfessionnelHomologue pModel)
			throws ApplicationException {
		ProfessionnelHomologue lUpdatedModel = super.ajouter(pModel);
		try {
			this.homologationDao
					.mettreAJourCollectionHomologationForProfessionnelHomologue(
							lUpdatedModel, lUpdatedModel.getHomologations());
			return lUpdatedModel;
		} catch (Exception e) {
			throw new ApplicationException(
					"Echec lors de la mise à jour en base de données.");
		}
	}

	@Override
	public ProfessionnelHomologue mettreAJour(ProfessionnelHomologue pModel)
			throws ApplicationException {
		ProfessionnelHomologue lUpdatedModel = super.mettreAJour(pModel);
		try {
			this.homologationDao
					.mettreAJourCollectionHomologationForProfessionnelHomologue(
							lUpdatedModel, lUpdatedModel.getHomologations());
			return lUpdatedModel;
		} catch (Exception e) {
			throw new ApplicationException(
					"Echec lors de la mise à jour en base de données.");
		}
	}

}
