package net.eni.gestion.pedagogie.resource.implementation;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import com.sun.jersey.api.client.ClientResponse.Status;

public class UnauthorizedException extends WebApplicationException
{
    private static final long serialVersionUID = 1L;
 
    public UnauthorizedException()
    {
        this("Please authenticate.", "Name of your web service");
    }
 
    public UnauthorizedException(String message, String realm)
    {
        super(Response.status(Status.UNAUTHORIZED).header(HttpHeaders.WWW_AUTHENTICATE,
                                                          "Basic realm=\"" + realm + "\"")
                .entity(message).build());
    }
}