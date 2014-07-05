package net.eni.gestion.pedagogie.configuration;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig.Feature;

// Customized {@code ContextResolver} implementation to pass ObjectMapper to use
@Provider
@Produces(MediaType.APPLICATION_JSON)
public class RessourceSetup implements ContextResolver<ObjectMapper> {
    private ObjectMapper objectMapper;

    public RessourceSetup() throws Exception {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        this.objectMapper.configure(Feature.INDENT_OUTPUT, true);
    }
    public ObjectMapper getContext(Class<?> objectType) {
        return objectMapper;
    }
}