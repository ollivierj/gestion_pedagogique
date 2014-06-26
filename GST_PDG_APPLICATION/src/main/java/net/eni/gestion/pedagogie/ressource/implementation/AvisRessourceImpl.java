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
import net.eni.gestion.pedagogie.modele.Avis;
import net.eni.gestion.pedagogie.ressource.AvisRessource;
import net.eni.gestion.pedagogie.service.AvisService;
import com.google.inject.Inject;

/**
 * @author jollivier
 * Classe d'implémentation pour le module de suivi des avis
 */
@Path("/avis")
public class AvisRessourceImpl implements AvisRessource {

    /**
     * Unité métier avis
     */
    private final AvisService avisService;

    /**
     * Constructeur
     * @param AvisService
     */
    @Inject
    public AvisRessourceImpl(AvisService AvisService) {
        this.avisService = AvisService;
    }

    /* (non-Javadoc)
     * @see net.eni.gestion.pedagogie.service.contrat.generique.CRUDService#charger()
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Avis> charger() throws GenericException {
        return avisService.charger(new Avis());
    }
        
	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.service.generique.CRUDService#chargerDetail(java.lang.Integer)
	 */
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
	public Avis chargerDetail(@PathParam("id") Integer id) throws GenericException {
		return avisService.chargerDetail(new Avis(id));

	}

    /* (non-Javadoc)
     * @see net.eni.gestion.pedagogie.service.contrat.generique.CRUDService#ajouter(net.eni.gestion.pedagogie.modele.AModele)
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public Avis ajouter(Avis pModel) throws GenericException {
		return this.avisService.ajouter(pModel);
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.service.contrat.generique.CRUDService#mettreAJour(net.eni.gestion.pedagogie.modele.AModele)
	 */
	@PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public Avis mettreAJour(Avis pModel) throws GenericException {
		return this.avisService.mettreAJour(pModel);
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.service.contrat.generique.CRUDService#supprimer(net.eni.gestion.pedagogie.modele.AModele)
	 */
	@DELETE
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Avis supprimer(@PathParam("id") Integer id) throws GenericException {
		return this.avisService.supprimer(new Avis(id));
	}


}
