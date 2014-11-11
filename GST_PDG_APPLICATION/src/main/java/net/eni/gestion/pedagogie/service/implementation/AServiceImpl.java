package net.eni.gestion.pedagogie.service.implementation;

import java.util.ArrayList;
import java.util.HashMap;

import javax.ws.rs.PathParam;

import net.eni.gestion.pedagogie.DAO.ADao;
import net.eni.gestion.pedagogie.commun.composant.erreur.ApplicationException;
import net.eni.gestion.pedagogie.commun.composant.pagination.Pager;
import net.eni.gestion.pedagogie.commun.composant.tuple.Pair;
import net.eni.gestion.pedagogie.commun.modele.generique.AModele;
import net.eni.gestion.pedagogie.service.AService;

import com.google.inject.Inject;

abstract class AServiceImpl<M extends AModele<ID>, ID, D extends ADao<M, ID>>
		implements AService<M, ID> {

	protected final D dao;

	/**
	 * Constructeur
	 * 
	 * @param MService
	 */
	@Inject
	public AServiceImpl(D pDao) {
		dao = pDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.eni.gestion.pedagogie.service.contrat.generique.CRUDService#charger()
	 */
	public Pair<ArrayList<M>, Long> charger(Pager pPager)
			throws ApplicationException {
		return dao.charger(pPager);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.eni.gestion.pedagogie.DAO.ADao#chargerForAutocompleteSearch(java.
	 * lang.String)
	 */
	public ArrayList<M> chargerForAutocompleteSearch(String pSearchText)
			throws ApplicationException {
		return dao.chargerForAutocompleteSearch(pSearchText);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.eni.gestion.pedagogie.service.generique.CRUDService#chargerDetail
	 * (java.lang.Integer)
	 */
	public M chargerDetail(ID pId) throws ApplicationException {
		return dao.chargerDetail(pId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.eni.gestion.pedagogie.service.contrat.generique.CRUDService#ajouter
	 * (net.eni.gestion.pedagogie.modele.AModele)
	 */
	public M ajouter(M pModel) throws ApplicationException {
		return this.dao.ajouter(pModel);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.eni.gestion.pedagogie.service.contrat.generique.CRUDService#mettreAJour
	 * (net.eni.gestion.pedagogie.modele.AModele)
	 */
	public M mettreAJour(M pModel) throws ApplicationException {
		return this.dao.mettreAJour(pModel);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.eni.gestion.pedagogie.service.contrat.generique.CRUDService#supprimer
	 * (net.eni.gestion.pedagogie.modele.AModele)
	 */
	public ID supprimer(@PathParam("id") ID pId) throws ApplicationException {
		return this.dao.supprimer(pId);
	}

	public M addOrUpdate(M pModel) throws ApplicationException {
		M m = dao.chargerDetail(pModel.getId());
		// Si le modèle n'existe pas en base on fait un ajout...
		if (m == null) {
			return dao.ajouter(pModel);
			// ... Sinon on le met à jour
		} else {
			return dao.mettreAJour(pModel);
		}
	}

	public HashMap<String, String> getTitleMap() throws ApplicationException {
		return dao.getTitleMap();
	}

}
