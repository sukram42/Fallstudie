package com.fallstudie.Interface;

import Exceptions.LaeuftBereitsException;
import Exceptions.ZuWenigMaschinenstellplatzException;
import Exceptions.ZuWenigMitarbeiterException;
import Unternehmung.Abteilungen.Marketing;
import Unternehmung.Unternehmen;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import jdk.nashorn.api.scripting.JSObject;
import org.json.simple.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

/**
 * Created by boebel on 02.02.2017.
 */

@Path("/companies/marketing")
public class MarketingInterface {
    private static Gson gson = new Gson();

    @POST
    @Secured
    @Path("campaigns/{campaign}")
    public Response startKampagne(@Context SecurityContext context,
                                  @PathParam("campaign") String art,
                                  String laufzeit) {
        Marketing marketing = getMarketingFromContext(context);
        try {
             marketing.marketingkampagneStarten(art, Integer.parseInt(laufzeit));
w             return Response.status(201).entity("Marketingkampagne erfolgreich gestartet").build();
        } catch (ZuWenigMitarbeiterException e) {
            return Response.ok("ERROR:" + e.toString()).build();
        }
    }

    @DELETE
    @Secured
    @Path("campaigns/{campaign}")
    public Response stopKampagne(@Context SecurityContext context,@PathParam("campaign") String art) {
        Marketing marketing = getMarketingFromContext(context);
        marketing.marketingkampagneAbbrechen(art);
        return Response.status(201).entity("MarketingKampagne erfolgreich gestartet").build();
    }

    /*
    @GET
    @Secured
    @Path("campaigns/")
    public  Response getCampaigns(@Context SecurityContext context)
    {
        Marketing marketing = getMarketingFromContext(context);
        return Response.ok().build();
    }
    */

    private Marketing getMarketingFromContext(SecurityContext context)
    {
        Unternehmen unternehmen =  CompanyInterface.getCompanyFromContext(context);
        return (Marketing)unternehmen.getAbteilung("marketing");
    }
}
