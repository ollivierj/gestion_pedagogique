package net.eni.gestion.pedagogie.resource.implementation;

import java.util.ArrayList;
import java.util.HashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import net.eni.gestion.pedagogie.commun.composant.GenericException;
import net.eni.gestion.pedagogie.commun.composant.NamedObjectMap;
import net.eni.gestion.pedagogie.commun.composant.Pager;
import net.eni.gestion.pedagogie.commun.composant.Pair;
import net.eni.gestion.pedagogie.modele.generique.AModele;
import net.eni.gestion.pedagogie.resource.AResource;
import net.eni.gestion.pedagogie.service.AService;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jackson.JsonLoader;
import com.github.reinert.jjschema.v1.JsonSchemaFactory;
import com.github.reinert.jjschema.v1.JsonSchemaV4Factory;
import com.google.inject.Inject;

public class AResourceImpl <M extends AModele<ID>, ID, S extends AService<M,ID>> implements AResource<M, ID>{
   

    protected final S service;
    
    protected final Class<M> modele;
    
    /**
     * Constructeur
     * @param MService
     */
    @Inject
    public AResourceImpl(S pService, Class<M> pModele) {
        service = pService;
        modele = pModele;
    }
    
    /* (non-Javadoc)
     * @see net.eni.gestion.pedagogie.resource.AResource#getJsonSchema()
     */
    @GET
    @Path("/jsonschema")
    @Produces(MediaType.APPLICATION_JSON)
    public String getJsonSchema() throws GenericException {
    	JsonLoader.class.getResource("/draftv4/schema");
    	JsonSchemaFactory schemaFactory = new JsonSchemaV4Factory();
    	schemaFactory.setAutoPutDollarSchema(true);
    	JsonNode productSchema = schemaFactory.createSchema(modele);
    	return productSchema.toString();
    }
 

    /* (non-Javadoc)
     * @see net.eni.gestion.pedagogie.service.contrat.generique.CRUDService#charger()
     */
    @POST
    @Path("/page")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public NamedObjectMap charger(Pager pPager) throws GenericException {
        Pair<ArrayList<M>, Long> page = service.charger(pPager);
        NamedObjectMap results = new NamedObjectMap();
        results.put("data", page.first());
        results.put("totalServerItems", page.second());
        return results;
    }
    
    
    
    /* (non-Javadoc)
     * @see net.eni.gestion.pedagogie.service.contrat.generique.CRUDService#charger()
     */
    @POST
	@Path("/csv")
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response exporter(Pager pPager) throws GenericException {
    	pPager.setPagingOptions(null);
    	Pair<ArrayList<M>, Long> page = service.charger(pPager);
    	StringBuilder lStrBuilder = new StringBuilder();
    	for (M lObject : page.first()) {
			lStrBuilder.append(lObject.toString());
			lStrBuilder.append("\n");
		}
		try {
			return Response
					.ok(lStrBuilder.toString().getBytes(), MediaType.APPLICATION_OCTET_STREAM)
					.header("content-disposition",
							"attachment; filename=export.csv").build();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		throw new WebApplicationException(404);
    }
    
    @GET
    @Path("/autocomplete/{search}")
    @Produces(MediaType.APPLICATION_JSON)
	public ArrayList<M> chargerForAutocompleteSearch(@PathParam("search") String pSearchText) throws GenericException {
    	return service.chargerForAutocompleteSearch(pSearchText);
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
    @Path("/creation")
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

	@POST
	@Path("/addOrUpdate")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public M addOrUpdate(M pModel) throws GenericException {
		return this.service.addOrUpdate(pModel);
	}
	
	/* (non-Javadoc)
     * @see net.eni.gestion.pedagogie.service.contrat.generique.CRUDService#charger()
     */
    @GET
    @Path("/titlemap")
    @Produces(MediaType.APPLICATION_JSON)
    public HashMap<String, String> getTitleMap() throws GenericException {
        return service.getTitleMap();
    }
}
