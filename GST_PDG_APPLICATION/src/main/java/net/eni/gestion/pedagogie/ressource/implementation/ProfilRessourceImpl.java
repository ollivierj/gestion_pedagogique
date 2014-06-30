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
import net.eni.gestion.pedagogie.modele.Profil;
import net.eni.gestion.pedagogie.ressource.ProfilRessource;
import net.eni.gestion.pedagogie.service.ProfilService;

import com.google.inject.Inject;

/**
 * @author jollivier
 * Classe d'implémentation pour le module de gestion des profils
 */
@Path("/profils")
public class ProfilRessourceImpl implements ProfilRessource {

    /**
     * Unité métier profil
     */
    private final ProfilService profilService;

    /**
     * Constructeur
     * @param ProfilService
     */
    @Inject
    public ProfilRessourceImpl(ProfilService ProfilService) {
        this.profilService = ProfilService;
    }

    /* (non-Javadoc)
     * @see net.eni.gestion.pedagogie.service.contrat.generique.CRUDService#charger()
     */
    @GET
    @Path("/{page}/{pageSize}/{orderBy}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Profil> charger(int page, int pageSize, String orderColumn, String orderDirection, String searchText) throws GenericException {
        return profilService.charger(page, pageSize, orderColumn, orderDirection, searchText);
    }
        
	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.service.generique.CRUDService#chargerDetail(java.lang.Integer)
	 */
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
	public Profil chargerDetail(@PathParam("id") Integer pId) throws GenericException {
		return profilService.chargerDetail(pId);

	}

    /* (non-Javadoc)
     * @see net.eni.gestion.pedagogie.service.contrat.generique.CRUDService#ajouter(net.eni.gestion.pedagogie.modele.AModele)
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public Profil ajouter(Profil pModel) throws GenericException {
		return this.profilService.ajouter(pModel);
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.service.contrat.generique.CRUDService#mettreAJour(net.eni.gestion.pedagogie.modele.AModele)
	 */
	@PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public Profil mettreAJour(Profil pModel) throws GenericException {
		return this.profilService.mettreAJour(pModel);
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.service.contrat.generique.CRUDService#supprimer(net.eni.gestion.pedagogie.modele.AModele)
	 */
	@DELETE
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Integer supprimer(@PathParam("id") Integer pId) throws GenericException {
		return this.profilService.supprimer(pId);
	}
	
   



}
