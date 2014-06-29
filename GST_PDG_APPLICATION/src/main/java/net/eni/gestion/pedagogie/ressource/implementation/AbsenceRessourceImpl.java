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
import net.eni.gestion.pedagogie.modele.Absence;
import net.eni.gestion.pedagogie.ressource.AbsenceRessource;
import net.eni.gestion.pedagogie.service.AbsenceService;
import com.google.inject.Inject;

/**
 * @author jollivier
 * Classe d'implémentation pour le module de suivi des absences
 */
@Path("/absences")
public class AbsenceRessourceImpl implements AbsenceRessource {

    /**
     * Unité métier absence
     */
    private final AbsenceService absenceService;

    /**
     * Constructeur
     * @param AbsenceService
     */
    @Inject
    public AbsenceRessourceImpl(AbsenceService AbsenceService) {
        this.absenceService = AbsenceService;
    }

    /* (non-Javadoc)
     * @see net.eni.gestion.pedagogie.service.contrat.generique.CRUDService#charger()
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Absence> charger() throws GenericException {
        return absenceService.charger(new Absence());
    }
        
	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.service.generique.CRUDService#chargerDetail(java.lang.Integer)
	 */
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
	public Absence chargerDetail(@PathParam("id") Integer id) throws GenericException {
		return absenceService.chargerDetail(new Absence(id));

	}

    /* (non-Javadoc)
     * @see net.eni.gestion.pedagogie.service.contrat.generique.CRUDService#ajouter(net.eni.gestion.pedagogie.modele.AModele)
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public Absence ajouter(Absence pModel) throws GenericException {
		return this.absenceService.ajouter(pModel);
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.service.contrat.generique.CRUDService#mettreAJour(net.eni.gestion.pedagogie.modele.AModele)
	 */
	@PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public Absence mettreAJour(Absence pModel) throws GenericException {
		return this.absenceService.mettreAJour(pModel);
	}

	/* (non-Javadoc)
	 * @see net.eni.gestion.pedagogie.service.contrat.generique.CRUDService#supprimer(net.eni.gestion.pedagogie.modele.AModele)
	 */
	@DELETE
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Absence supprimer(@PathParam("id") Integer id) throws GenericException {
		return this.absenceService.supprimer(new Absence(id));
	}


}
