package com.fallstudie.Interface;

import Exceptions.ZuWenigMitarbeiterException;
import Rules.Game;
import Unternehmung.Abteilungen.Vertrieb;
import Unternehmung.Ausschreibung;
import Unternehmung.Unternehmen;
import com.google.gson.Gson;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.ArrayList;


@Path("companies/sales")
public class SalesInterface {
    private static Gson gson = new Gson();

    @POST
    @Secured
    @Path("ausschreibungen")
    public Response bewerben(@Context SecurityContext securityContext, String i) {
        Unternehmen unternehmen = CompanyInterface.getCompanyFromContext(securityContext);
        int index = Integer.parseInt(i);
        Vertrieb sales = (Vertrieb)unternehmen.getAbteilung("vertrieb");

        try {
            sales.bewerben(index);
            return Response.ok("Auf Ausschreibung beworben").build();
        } catch (ZuWenigMitarbeiterException e) {
            return Response.status(409).entity(e.toString()).build();
        }
    }
    @GET
    @Path("opportunities")
    @Secured
    public Response getOpportunities(@Context SecurityContext securityContext) {
        Unternehmen unternehmen = CompanyInterface.getCompanyFromContext(securityContext);
        Vertrieb sales = (Vertrieb) unternehmen.getAbteilung("vertrieb");

        return Response.ok().entity(gson.toJson(new ArrayList<>(sales.getOpportunities().values()))).build();
    }

    @GET
    @Path("accounts")
    @Secured
    public Response getAccounts(@Context SecurityContext securityContext)
    {
        Unternehmen unternehmen = CompanyInterface.getCompanyFromContext(securityContext);
        Vertrieb sales = (Vertrieb) unternehmen.getAbteilung("vertrieb");

        return Response.ok().entity(gson.toJson(sales.getAccounts())).build();
    }

    @GET
    @Path("ausschreibungen")
    public Response getAusschreibungen()
    {
        return Response.ok(gson.toJson(Game.getAusschreibungen())).build();
    }



}
