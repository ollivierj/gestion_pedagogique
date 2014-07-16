package net.eni.gestion.pedagogie.service.implementation;

import net.eni.gestion.pedagogie.DAO.HomologationDao;
import net.eni.gestion.pedagogie.DAO.ProfessionnelHomologueDao;
import net.eni.gestion.pedagogie.commun.composant.GenericException;
import net.eni.gestion.pedagogie.modele.ProfessionnelHomologue;
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
			throws GenericException {
		ProfessionnelHomologue lProfessionnelHomologue = super
				.chargerDetail(pId);
		lProfessionnelHomologue.getHomologations();
		return lProfessionnelHomologue;
	}

	@Override
	public ProfessionnelHomologue ajouter(ProfessionnelHomologue pModel)
			throws GenericException {
		ProfessionnelHomologue lUpdatedModel = super.ajouter(pModel);
		try {
			this.homologationDao
					.mettreAJourCollectionHomologationForProfessionnelHomologue(
							lUpdatedModel, lUpdatedModel.getHomologations());
			return lUpdatedModel;
		} catch (Exception e) {
			throw new GenericException(
					"Echec lors de la mise à jour en base de données.");
		}
	}

	@Override
	public ProfessionnelHomologue mettreAJour(ProfessionnelHomologue pModel)
			throws GenericException {
		ProfessionnelHomologue lUpdatedModel = super.mettreAJour(pModel);
		try {
			this.homologationDao
					.mettreAJourCollectionHomologationForProfessionnelHomologue(
							lUpdatedModel, lUpdatedModel.getHomologations());
			return lUpdatedModel;
		} catch (Exception e) {
			throw new GenericException(
					"Echec lors de la mise à jour en base de données.");
		}
	}

}
