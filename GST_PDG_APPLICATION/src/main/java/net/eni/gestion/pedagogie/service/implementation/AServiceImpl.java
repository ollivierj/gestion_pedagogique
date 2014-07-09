package net.eni.gestion.pedagogie.service.implementation;

import java.util.ArrayList;

import javax.ws.rs.PathParam;

import net.eni.gestion.pedagogie.DAO.ADao;
import net.eni.gestion.pedagogie.commun.composant.FilterOptions;
import net.eni.gestion.pedagogie.commun.composant.GenericException;
import net.eni.gestion.pedagogie.commun.composant.Pager;
import net.eni.gestion.pedagogie.commun.composant.PagingOptions;
import net.eni.gestion.pedagogie.commun.composant.Pair;
import net.eni.gestion.pedagogie.commun.composant.SortOptions;
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
   public Pair<ArrayList<M>, Long> charger(Pager pPager) throws GenericException {
        try {
			return dao.charger(pPager);
		} catch (Exception e) {
			throw new GenericException("Echec lors du chargement depuis la base de données.");
		}
    }
        
	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.service.generique.CRUDService#chargerDetail(java.lang.Integer)
	 */
   public M chargerDetail(@PathParam("id") ID pId) throws GenericException {
		try {
			return dao.chargerDetail(pId);
		} catch (Exception e) {
			throw new GenericException("Echec lors du chargement depuis la base de données.");
		}

	}

    /* (non-Javadoc)
     * @see net.eni.gestion.pedagogie.service.contrat.generique.CRUDService#ajouter(net.eni.gestion.pedagogie.modele.AModele)
     */
   public M ajouter(M pModel) throws GenericException {
		try {
			return this.dao.ajouter(pModel);
		} catch (Exception e) {
			throw new GenericException("Echec lors de la création en base de données.");
		}
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.service.contrat.generique.CRUDService#mettreAJour(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public M mettreAJour(M pModel) throws GenericException {
		try {
			return this.dao.mettreAJour(pModel);
		} catch (Exception e) {
			throw new GenericException("Echec lors de la mise à jour en base de données.");
		}
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.service.contrat.generique.CRUDService#supprimer(net.eni.gestion.pedagogie.modele.AModele)
	 */
	public ID supprimer(@PathParam("id") ID pId) throws GenericException {
		try {
			return this.dao.supprimer(pId);
		} catch (Exception e) {
			throw new GenericException("Echec lors de la suppression en base de données.");
		}
	}
}
