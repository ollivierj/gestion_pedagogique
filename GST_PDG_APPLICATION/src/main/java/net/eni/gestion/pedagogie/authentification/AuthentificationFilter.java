package net.eni.gestion.pedagogie.authentification;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;

import net.eni.gestion.pedagogie.commun.composant.GenericException;
import net.eni.gestion.pedagogie.commun.composant.UnauthorizedException;
import net.eni.gestion.pedagogie.modele.Utilisateur;
import net.eni.gestion.pedagogie.service.UtilisateurService;

import com.google.inject.Inject;

public class AuthentificationFilter implements ContainerRequestFilter {
 
    UtilisateurService utilisateurService;
 
    @Inject
    public AuthentificationFilter(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }
 
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
    	Utilisateur utilsateur = new Utilisateur();
        Utilisateur utilisateurAuthentifie = null;
		try {
			// TODO : enrichir l'utilisateur avec les informations de contexte
			utilisateurAuthentifie = this.utilisateurService.authentifier(utilsateur);
			if (null == utilisateurAuthentifie){
				throw new UnauthorizedException();
			}
		} catch (GenericException e) {
			throw new UnauthorizedException();
		}
        requestContext.setProperty("Utilisateur", utilisateurAuthentifie);
    }
}