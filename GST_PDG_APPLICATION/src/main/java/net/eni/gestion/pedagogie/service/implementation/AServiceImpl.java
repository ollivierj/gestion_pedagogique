package net.eni.gestion.pedagogie.service.implementation;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import net.eni.gestion.pedagogie.DAO.ADao;
import net.eni.gestion.pedagogie.commun.composant.GenericException;
import net.eni.gestion.pedagogie.commun.composant.NamedObjectMap;
import net.eni.gestion.pedagogie.commun.composant.Pager;
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
    @GET
    @Path("/{page}/{pageSize}/{orderBy}")
    @Produces(MediaType.APPLICATION_JSON)
    public NamedObjectMap charger(Pager pPager) throws GenericException {
        try {
			return dao.charger(pPager);
		} catch (Exception e) {
			throw new GenericException("Echec lors du chargement depuis la base de données.");
		}
    }
        
	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.service.generique.CRUDService#chargerDetail(java.lang.Integer)
	 */
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
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
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
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
	@PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
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
	@DELETE
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ID supprimer(@PathParam("id") ID pId) throws GenericException {
		try {
			return this.dao.supprimer(pId);
		} catch (Exception e) {
			throw new GenericException("Echec lors de la suppression en base de données.");
		}
	}
}
