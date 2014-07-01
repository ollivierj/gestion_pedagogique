package net.eni.gestion.pedagogie.ressource.implementation;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import net.eni.gestion.pedagogie.commun.composant.GenericException;
import net.eni.gestion.pedagogie.commun.composant.NamedObjectMap;
import net.eni.gestion.pedagogie.commun.composant.Pager;
import net.eni.gestion.pedagogie.modele.generique.AModele;
import net.eni.gestion.pedagogie.ressource.ARessource;
import net.eni.gestion.pedagogie.service.AService;

import com.google.inject.Inject;

public class ARessourceImpl <M extends AModele<ID>, ID, S extends AService<M,ID>> implements ARessource<M, ID>{
   

    protected final S service;
    
    /**
     * Constructeur
     * @param MService
     */
    @Inject
    public ARessourceImpl(S pService) {
        service = pService;
    }

    /* (non-Javadoc)
     * @see net.eni.gestion.pedagogie.service.contrat.generique.CRUDService#charger()
     */
    @GET
    @Path("page/{page}/{pageSize}/{sortColumnBy}/{sortDirectionBy}")
    @Produces(MediaType.APPLICATION_JSON)
    public NamedObjectMap charger(@PathParam("page") int page, @PathParam("pageSize") int pageSize, @PathParam("sortColumnBy") String sortColumnBy, @PathParam("sortDirectionBy") String sortDirectionBy) throws GenericException {
        return service.charger(new Pager(page, pageSize, sortColumnBy, sortDirectionBy));
    }
        
	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.service.generique.CRUDService#chargerDetail(java.lang.Integer)
	 */
    @GET
    @Path("/detail/{id}")
    @Produces(MediaType.APPLICATION_JSON)
	public M chargerDetail(@PathParam("id") ID pId) throws GenericException {
		return service.chargerDetail(pId);

	}

    /* (non-Javadoc)
     * @see net.eni.gestion.pedagogie.service.contrat.generique.CRUDService#ajouter(net.eni.gestion.pedagogie.modele.AModele)
     */
    @POST
    @Path("/ajout")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public M ajouter(M pModel) throws GenericException {
		return this.service.ajouter(pModel);
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.service.contrat.generique.CRUDService#mettreAJour(net.eni.gestion.pedagogie.modele.AModele)
	 */
	@PUT
	@Path("/modification")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public M mettreAJour(M pModel) throws GenericException {
		return this.service.mettreAJour(pModel);
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.service.contrat.generique.CRUDService#supprimer(net.eni.gestion.pedagogie.modele.AModele)
	 */
	@DELETE
	@Path("/suppression/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ID supprimer(@PathParam("id") ID pId) throws GenericException {
		return this.service.supprimer(pId);
	}
}
