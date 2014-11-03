package net.eni.gestion.pedagogie.configuration;



import java.io.File;

import net.TopLevelDomain;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Scopes;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;

/**
 * @author jollivier
 * Gestion de la configuration de la'application Web
 */
public class ApplicationConfiguration extends GuiceServletContextListener {

	public final static String DEV_MODE 						= "DEV";
	public final static String PROD_MODE 						= "PROD";
	
	public static String getWebInfLocation() {
		return TopLevelDomain.class.getResource("").getPath()
			+ ".." + File.separatorChar + ".." + File.separatorChar;
	}
	
    @Override
    protected Injector getInjector() {
        return Guice.createInjector(new ServletModule() {

            @Override
            protected void configureServlets() {

                super.configureServlets();

                ResourceConfig resourceConfig = new PackagesResourceConfig("net.eni.gestion.pedagogie.commun.composant.authentification","net.eni.gestion.pedagogie.resource","net.eni.gestion.pedagogie.errorhandling");
                
                for (Class<?> resource : resourceConfig.getClasses()) {
                    bind(resource);
                }
                bind(ResourceConfiguration.class).in(Scopes.SINGLETON);
                bind(JacksonJsonProvider.class).in(Scopes.SINGLETON);
                //bind(ApplicationExceptionMapper.class);
            	//bind(GenericExceptionMapper.class);
                serve("/web/*").with(GuiceContainer.class);
            }
        }, new ModuleConfiguration());
    }
}
