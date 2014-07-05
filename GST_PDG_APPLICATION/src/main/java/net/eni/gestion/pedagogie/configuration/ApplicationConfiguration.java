package net.eni.gestion.pedagogie.configuration;



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

    @Override
    protected Injector getInjector() {
        return Guice.createInjector(new ServletModule() {

            @Override
            protected void configureServlets() {

                super.configureServlets();

                ResourceConfig resourceConfig = new PackagesResourceConfig("net.eni.gestion.pedagogie.ressource");
                
                for (Class<?> resource : resourceConfig.getClasses()) {
                    bind(resource);
                }
                bind(ResourceConfiguration.class).in(Scopes.SINGLETON);
                bind(JacksonJsonProvider.class).in(Scopes.SINGLETON);

                serve("/web/*").with(GuiceContainer.class);
            }
        }, new ModuleConfiguration());
    }
}
