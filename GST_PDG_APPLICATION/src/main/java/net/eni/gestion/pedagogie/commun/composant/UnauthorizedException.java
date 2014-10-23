package net.eni.gestion.pedagogie.commun.composant;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import com.sun.jersey.api.client.ClientResponse.Status;

public class UnauthorizedException extends WebApplicationException
{
    private static final long serialVersionUID = 1L;
 
    public UnauthorizedException()
    {
        this("Vous devez vous authentifier.", "ENI Ecole");
    }
 
    public UnauthorizedException(String message, String realm)
    {
        super(Response.status(Status.UNAUTHORIZED).header(HttpHeaders.WWW_AUTHENTICATE,
                                                          "Basic realm=\"" + realm + "\"")
                .entity(message).build());
    }
}