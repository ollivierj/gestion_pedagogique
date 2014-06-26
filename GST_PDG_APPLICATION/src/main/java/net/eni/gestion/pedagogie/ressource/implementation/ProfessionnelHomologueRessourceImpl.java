package net.eni.gestion.pedagogie.ressource.implementation;

import java.util.List;

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
import net.eni.gestion.pedagogie.modele.ProfessionnelHomologue;
import net.eni.gestion.pedagogie.ressource.ProfessionnelHomologueRessource;
import net.eni.gestion.pedagogie.service.ProfessionnelHomologueService;
import com.google.inject.Inject;

/**
 * @author jollivier
 * Classe d'implémentation pour le module de gestion des professionnel homologues
 */
@Path("/professionnelHomologues")
public class ProfessionnelHomologueRessourceImpl implements ProfessionnelHomologueRessource {

    /**
     * Unité métier professionnelHomologue
     */
    private final ProfessionnelHomologueService professionnelHomologueService;

    /**
     * Constructeur
     * @param ProfessionnelHomologueService
     */
    @Inject
    public ProfessionnelHomologueRessourceImpl(ProfessionnelHomologueService ProfessionnelHomologueService) {
        this.professionnelHomologueService = ProfessionnelHomologueService;
    }

    /* (non-Javadoc)
     * @see net.eni.gestion.pedagogie.service.contrat.generique.CRUDService#charger()
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ProfessionnelHomologue> charger() throws GenericException {
        return professionnelHomologueService.charger(new ProfessionnelHomologue());
    }
        
	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.service.generique.CRUDService#chargerDetail(java.lang.Integer)
	 */
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
	public ProfessionnelHomologue chargerDetail(@PathParam("id") Integer id) throws GenericException {
		return professionnelHomologueService.chargerDetail(new ProfessionnelHomologue(id));

	}

    /* (non-Javadoc)
     * @see net.eni.gestion.pedagogie.service.contrat.generique.CRUDService#ajouter(net.eni.gestion.pedagogie.modele.AModele)
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public ProfessionnelHomologue ajouter(ProfessionnelHomologue pModel) throws GenericException {
		return this.professionnelHomologueService.ajouter(pModel);
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.service.contrat.generique.CRUDService#mettreAJour(net.eni.gestion.pedagogie.modele.AModele)
	 */
	@PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public ProfessionnelHomologue mettreAJour(ProfessionnelHomologue pModel) throws GenericException {
		return this.professionnelHomologueService.mettreAJour(pModel);
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.service.contrat.generique.CRUDService#supprimer(net.eni.gestion.pedagogie.modele.AModele)
	 */
	@DELETE
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ProfessionnelHomologue supprimer(@PathParam("id") Integer id) throws GenericException {
		return this.professionnelHomologueService.supprimer(new ProfessionnelHomologue(id));
	}


}
