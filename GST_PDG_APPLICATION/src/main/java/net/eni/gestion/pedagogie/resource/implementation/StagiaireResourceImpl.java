package net.eni.gestion.pedagogie.resource.implementation;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import net.eni.gestion.pedagogie.commun.composant.GenericException;
import net.eni.gestion.pedagogie.commun.composant.NamedObjectMap;
import net.eni.gestion.pedagogie.commun.composant.Pager;
import net.eni.gestion.pedagogie.commun.composant.Pair;
import net.eni.gestion.pedagogie.modele.Absence;
import net.eni.gestion.pedagogie.modele.Avis;
import net.eni.gestion.pedagogie.modele.Echange;
import net.eni.gestion.pedagogie.modele.Stagiaire;
import net.eni.gestion.pedagogie.resource.StagiaireResource;
import net.eni.gestion.pedagogie.service.StagiaireService;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jackson.JsonLoader;
import com.github.reinert.jjschema.v1.JsonSchemaFactory;
import com.github.reinert.jjschema.v1.JsonSchemaV4Factory;
import com.google.inject.Inject;

/**
 * @author jollivier
 * Classe d'implémentation pour le module de gestion des stagiaires
 */
@Path("/stagiaires")
public class StagiaireResourceImpl extends AResourceImpl<Stagiaire, Integer, StagiaireService> implements StagiaireResource {

    /**
     * Constructeur
     * @param stagiaireService
     */
    @Inject
    public StagiaireResourceImpl(StagiaireService stagiaireService) {
    	super(stagiaireService, Stagiaire.class);
    }
    
    @POST
    @Path("/absences")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public NamedObjectMap chargerAbsences(Pager pPager) throws GenericException{
    	Pair<ArrayList<Absence>, Long> page = super.service.chargerAbsences(pPager);
    	NamedObjectMap results = new NamedObjectMap();
    	results.put("data", page.first());
        results.put("totalServerItems", page.second());
        return results;
    }

    @GET
    @Path("/echanges/jsonschema")
    @Produces(MediaType.APPLICATION_JSON)
    public String getJsonSchemaEchanges() throws GenericException {
    	JsonLoader.class.getResource("/draftv4/schema");
    	JsonSchemaFactory schemaFactory = new JsonSchemaV4Factory();
    	schemaFactory.setAutoPutDollarSchema(true);
    	JsonNode productSchema = schemaFactory.createSchema(Echange.class);
    	return productSchema.toString();
    }
    
    @GET
    @Path("/avis/jsonschema")
    @Produces(MediaType.APPLICATION_JSON)
    public String getJsonSchemaAvis() throws GenericException {
    	JsonLoader.class.getResource("/draftv4/schema");
    	JsonSchemaFactory schemaFactory = new JsonSchemaV4Factory();
    	schemaFactory.setAutoPutDollarSchema(true);
    	JsonNode productSchema = schemaFactory.createSchema(Avis.class);
    	return productSchema.toString();
    }
}
