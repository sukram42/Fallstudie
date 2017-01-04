package com.fallstudie.Interface;





import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.Priorities;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import javax.annotation.Priority;

/**
 * Created by boebel on 04.01.2017.
 */

@Secured
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {
    @Override
    public void filter(ContainerRequestContext requestContext)
    {
        String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);


        if(authorizationHeader == null || !authorizationHeader.startsWith("Bearer "))
        {
            throw new NotAuthorizedException("Authorization header must be provided");
        }
        String token = authorizationHeader.substring("Bearer".length()).trim();

        try{
            GameInterface.validateToken(token);
        }catch(NotAuthorizedException e) {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
        }
    }


}
