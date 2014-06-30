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
import net.eni.gestion.pedagogie.modele.Utilisateur;
import net.eni.gestion.pedagogie.ressource.UtilisateurRessource;
import net.eni.gestion.pedagogie.service.UtilisateurService;

import com.google.inject.Inject;

/**
 * @author jollivier
 * Classe d'implémentation pour le module de gestion des utilisateurs
 */
@Path("/utilisateurs")
public class UtilisateurRessourceImpl implements UtilisateurRessource {

    /**
     * Unité métier utilisateur
     */
    private final UtilisateurService utilisateurService;

    /**
     * Constructeur
     * @param UtilisateurService
     */
    @Inject
    public UtilisateurRessourceImpl(UtilisateurService UtilisateurService) {
        this.utilisateurService = UtilisateurService;
    }

    /* (non-Javadoc)
     * @see net.eni.gestion.pedagogie.service.contrat.generique.CRUDService#charger()
     */
    @GET
    @Path("/{page}/{pageSize}/{orderBy}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Utilisateur> charger(int page, int pageSize, String orderColumn, String orderDirection, String searchText) throws GenericException {
        return utilisateurService.charger(page, pageSize, orderColumn, orderDirection, searchText);
    }
        
	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.service.generique.CRUDService#chargerDetail(java.lang.Integer)
	 */
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
	public Utilisateur chargerDetail(@PathParam("id") Integer pId) throws GenericException {
		return utilisateurService.chargerDetail(pId);

	}

    /* (non-Javadoc)
     * @see net.eni.gestion.pedagogie.service.contrat.generique.CRUDService#ajouter(net.eni.gestion.pedagogie.modele.AModele)
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public Utilisateur ajouter(Utilisateur pModel) throws GenericException {
		return this.utilisateurService.ajouter(pModel);
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.service.contrat.generique.CRUDService#mettreAJour(net.eni.gestion.pedagogie.modele.AModele)
	 */
	@PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public Utilisateur mettreAJour(Utilisateur pModel) throws GenericException {
		return this.utilisateurService.mettreAJour(pModel);
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.service.contrat.generique.CRUDService#supprimer(net.eni.gestion.pedagogie.modele.AModele)
	 */
	@DELETE
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Integer supprimer(@PathParam("id") Integer pId) throws GenericException {
		return this.utilisateurService.supprimer(pId);
	}
	
   



}
