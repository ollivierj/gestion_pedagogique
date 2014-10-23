package net.eni.gestion.pedagogie.authentification;

import java.lang.reflect.Method;

import javax.ws.rs.container.DynamicFeature;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.FeatureContext;
import javax.ws.rs.ext.Provider;

import net.eni.gestion.pedagogie.resource.AResource.AuthenticationNotRequired;
import net.eni.gestion.pedagogie.service.UtilisateurService;

import com.google.inject.Inject;

@Provider
public class AuthentificationFeature implements DynamicFeature{
	
	@Inject
	UtilisateurService utilisateurService;
	 
	@Inject
    public AuthentificationFeature(UtilisateurService authentificationService) {
        this.utilisateurService = authentificationService;
    }
	
	@Override
	public void configure(ResourceInfo resourceInfo, FeatureContext context) {
		 Method method = resourceInfo.getResourceMethod();
	        if (method.isAnnotationPresent(AuthenticationNotRequired.class)) {
	        	AuthentificationFilter authentificationFilter =
	                    new AuthentificationFilter(this.utilisateurService);
	            context.register(authentificationFilter);
	        }
	}
}
