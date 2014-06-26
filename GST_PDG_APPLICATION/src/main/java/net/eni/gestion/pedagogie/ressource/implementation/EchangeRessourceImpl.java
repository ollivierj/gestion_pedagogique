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
import net.eni.gestion.pedagogie.modele.Echange;
import net.eni.gestion.pedagogie.ressource.EchangeRessource;
import net.eni.gestion.pedagogie.service.EchangeService;
import com.google.inject.Inject;

/**
 * @author jollivier
 * Classe d'implémentation pour le module de suivi des echanges
 */
@Path("/echanges")
public class EchangeRessourceImpl implements EchangeRessource {

    /**
     * Unité métier echange
     */
    private final EchangeService echangeService;

    /**
     * Constructeur
     * @param EchangeService
     */
    @Inject
    public EchangeRessourceImpl(EchangeService EchangeService) {
        this.echangeService = EchangeService;
    }

    /* (non-Javadoc)
     * @see net.eni.gestion.pedagogie.service.contrat.generique.CRUDService#charger()
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Echange> charger() throws GenericException {
        return echangeService.charger(new Echange());
    }
        
	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.service.generique.CRUDService#chargerDetail(java.lang.Integer)
	 */
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
	public Echange chargerDetail(@PathParam("id") Integer id) throws GenericException {
		return echangeService.chargerDetail(new Echange(id));

	}

    /* (non-Javadoc)
     * @see net.eni.gestion.pedagogie.service.contrat.generique.CRUDService#ajouter(net.eni.gestion.pedagogie.modele.AModele)
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public Echange ajouter(Echange pModel) throws GenericException {
		return this.echangeService.ajouter(pModel);
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.service.contrat.generique.CRUDService#mettreAJour(net.eni.gestion.pedagogie.modele.AModele)
	 */
	@PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public Echange mettreAJour(Echange pModel) throws GenericException {
		return this.echangeService.mettreAJour(pModel);
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.service.contrat.generique.CRUDService#supprimer(net.eni.gestion.pedagogie.modele.AModele)
	 */
	@DELETE
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Echange supprimer(@PathParam("id") Integer id) throws GenericException {
		return this.echangeService.supprimer(new Echange(id));
	}


}