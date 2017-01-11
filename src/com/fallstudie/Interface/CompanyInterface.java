package com.fallstudie.Interface;

import Rules.Game;
import com.google.gson.Gson;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@Path("/companies/")
public class CompanyInterface {

    private static Gson gson = new Gson();


    /**
     * Gibt das Unternehmen zurück, dessen Token man im Header trägt.
     *
     * @return Unternehmen in JSON Form
     */
    @GET
    @Secured
    @Path("companies")
    public Response getCompanies(@Context SecurityContext securityContext) {
        return Response.status(200).entity(gson.toJson(Game.getUnternehmenByName(securityContext.getUserPrincipal().getName()))).build();
    }



}
