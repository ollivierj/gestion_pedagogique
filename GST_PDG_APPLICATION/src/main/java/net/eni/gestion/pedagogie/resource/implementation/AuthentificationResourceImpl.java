package net.eni.gestion.pedagogie.resource.implementation;

import java.util.ArrayList;
import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import net.eni.gestion.pedagogie.commun.composant.GenericException;
import net.eni.gestion.pedagogie.commun.composant.NamedObjectMap;
import net.eni.gestion.pedagogie.commun.composant.Pager;
import net.eni.gestion.pedagogie.commun.composant.Pair;
import net.eni.gestion.pedagogie.modele.Absence;

import com.google.inject.Inject;

/**
 * @author fgirardeau
 * Classe d'implémentation pour le module d'authentification
 */
@Path("/authentification")
public class AuthentificationResourceImpl {

   
    @Inject
    public AuthentificationResourceImpl() {
    }

    @GET
    @Path("login/{login}/{mdp}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
	public String chargerAbsencesByDate(@PathParam("login") String login, @PathParam("mdp") String mdp)
			throws GenericException {
    		System.out.println(login);
    		System.out.println(mdp);
    		return "coucou";
	}

}
