package net.eni.gestion.pedagogie.service.implementation;

import java.util.ArrayList;
import java.util.HashMap;

import javax.ws.rs.PathParam;

import net.eni.gestion.pedagogie.DAO.ADao;
import net.eni.gestion.pedagogie.commun.composant.Pager;
import net.eni.gestion.pedagogie.commun.composant.Pair;
import net.eni.gestion.pedagogie.errorhandling.ApplicationException;
import net.eni.gestion.pedagogie.modele.generique.AModele;
import net.eni.gestion.pedagogie.service.AService;

import com.google.inject.Inject;

abstract class AServiceImpl <M extends AModele<ID>, ID, D extends ADao<M, ID>> implements AService<M, ID>{
   

    protected final D dao;
    
    /**
     * Constructeur
     * @param MService
     */
    @Inject
    public AServiceImpl(D pDao) {
        dao = pDao;
    }

    /* (non-Javadoc)
     * @see net.eni.gestion.pedagogie.service.contrat.generique.CRUDService#charger()
     */
   public Pair<ArrayList<M>, Long> charger(Pager pPager) throws ApplicationException {
        try {
			return dao.charger(pPager);
		} catch (Exception e) {
			throw new ApplicationException("Echec lors du chargement depuis la base de données.");
		}
    }
   
	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.DAO.ADao#chargerForAutocompleteSearch(java.lang.String)
	 */
	public ArrayList<M> chargerForAutocompleteSearch(String pSearchText) throws ApplicationException {
		try {
			return dao.chargerForAutocompleteSearch(pSearchText);
		} catch (Exception e) {
			throw new ApplicationException("Echec lors du chargement depuis la base de données.");
		}
	}
        
	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.service.generique.CRUDService#chargerDetail(java.lang.Integer)
	 */
   public M chargerDetail(ID pId) throws ApplicationException {
		try {
			return dao.chargerDetail(pId);
		} catch (Exception e) {
			throw new ApplicationException("Echec lors du chargement depuis la base de données.");
		}

	}

    /* (non-Javadoc)
     * @see net.eni.gestion.pedagogie.service.contrat.generique.CRUDService#ajouter(net.eni.gestion.pedagogie.modele.AModele)
     */
   public M ajouter(M pModel) throws ApplicationException {
		try {
			return this.dao.ajouter(pModel);
		} catch (Exception e) {
			throw new ApplicationException("Echec lors de la création en base de données.");
		}
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.service.contrat.generique.CRUDService#mettreAJour(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public M mettreAJour(M pModel) throws ApplicationException {
		try {
			return this.dao.mettreAJour(pModel);
		} catch (Exception e) {
			throw new ApplicationException("Echec lors de la mise à jour en base de données.");
		}
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.service.contrat.generique.CRUDService#supprimer(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public ID supprimer(@PathParam("id") ID pId) throws ApplicationException {
		try {
			return this.dao.supprimer(pId);
		} catch (Exception e) {
			throw new ApplicationException("Echec lors de la suppression en base de données.");
		}
	}
	
	public M addOrUpdate(M pModel) throws ApplicationException {
		try {
			M m = dao.chargerDetail(pModel.getId());
			// Si le modèle n'existe pas en base on fait un ajout...
			if (m == null) {
				return dao.ajouter(pModel);
			// ... Sinon on le met à jour
			} else {
				return dao.mettreAJour(pModel);
			}
		} catch (Exception e) {
			throw new ApplicationException("Echec lors de la mise à jour en base de données.");
		}
	}

	public HashMap<String, String> getTitleMap() throws ApplicationException {
		try {
			return dao.getTitleMap();
		} catch (Exception e) {
			throw new ApplicationException(
					"Echec lors du chargement depuis la base de données.");
		}
	}

}
