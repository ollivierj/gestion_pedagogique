package net.eni.gestion.pedagogie.commun.configuration;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;


// Customized {@code ContextResolver} implementation to pass ObjectMapper to use
@Provider
@Produces(MediaType.APPLICATION_JSON)
public class ResourceConfiguration implements ContextResolver<ObjectMapper> {
    private ObjectMapper objectMapper;

    public ResourceConfiguration() throws Exception {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        this.objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
    }
    public ObjectMapper getContext(Class<?> objectType) {
		return objectMapper;
	}
}