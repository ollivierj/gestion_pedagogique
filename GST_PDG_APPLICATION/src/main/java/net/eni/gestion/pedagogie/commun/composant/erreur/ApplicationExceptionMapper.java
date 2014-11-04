package net.eni.gestion.pedagogie.commun.composant.erreur;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.google.inject.Singleton;


@Provider
@Singleton
public class ApplicationExceptionMapper implements ExceptionMapper<ApplicationException> {
	public Response toResponse(ApplicationException ex) {
		return Response.status(ex.getStatus()).entity(new ErrorMessage(ex))
				.type(MediaType.APPLICATION_JSON).build();
	}
}
