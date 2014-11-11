package net.eni.gestion.pedagogie.resource.implementation;

import javax.ws.rs.Path;

import net.eni.gestion.pedagogie.commun.composant.connexion.Connexion;
import net.eni.gestion.pedagogie.commun.modele.TypeSession;
import net.eni.gestion.pedagogie.resource.TypeSessionResource;
import net.eni.gestion.pedagogie.service.TypeSessionService;

import com.google.inject.Inject;

/**
 * @author jollivier
 * Classe d'implémentation pour le module de gestion des titreProfessionnels
 */
@Path("/typesessions")
public class TypeSessionResourceImpl extends AResourceImpl<TypeSession, Integer, TypeSessionService> implements TypeSessionResource {

    /**
     * Constructeur
     * @param titreProfessionnelService
     */
    @Inject
    public TypeSessionResourceImpl(TypeSessionService titreProfessionnelService, Connexion pConnexion) {
    	super(titreProfessionnelService, TypeSession.class, pConnexion);
    }
    
}
