package controller;

import Exceptions.ZuWenigMitarbeiterException;
import Unternehmung.Abteilungen.Marketing;
import Unternehmung.Marketingkampagne;
import Unternehmung.Unternehmen;
import com.google.gson.Gson;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by boebel on 02.02.2017.
 */

@Path("/companies/marketing")
public class MarketingController {
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

             return Response.status(201).entity("Marketingkampagne erfolgreich gestartet").build();
        } catch (ZuWenigMitarbeiterException e) {
            return Response.ok("ERROR:" + e.toString()).build();
        }
    }

    @DELETE
    @Secured
    @Path("campaigns/{campaign}")
    public Response stopKampagne(@Context SecurityContext context,@PathParam("campaign") int id) {
        Marketing marketing = getMarketingFromContext(context);
        marketing.marketingkampagneAbbrechen(id);
        return Response.status(201).entity("MarketingKampagne erfolgreich gestartet").build();
    }


    @GET
    @Secured
    @Path("campaigns")
    public  Response getCampaigns(@Context SecurityContext context)
    {
        Marketing marketing = getMarketingFromContext(context);
        return Response.ok(gson.toJson(marketing.getKampagnen())).build();
    }

    @GET
    @Secured
    @Path("costs")
    public Response getCosts(@Context SecurityContext context)
    {
        Marketing marketing = getMarketingFromContext(context);
        Map<String,Marketingkampagne> erg = new HashMap();

        erg.put("tv",Marketingkampagne.setParamsByArt("TV"));
        erg.put("radio",Marketingkampagne.setParamsByArt("Radio"));
        erg.put("print",Marketingkampagne.setParamsByArt("Print"));
        erg.put("social",Marketingkampagne.setParamsByArt("Social Media"));

        return Response.ok(gson.toJson(erg)).build();
    }

    private Marketing getMarketingFromContext(SecurityContext context)
    {
        Unternehmen unternehmen =  CompanyController.getCompanyFromContext(context);
        return (Marketing)unternehmen.getAbteilung("marketing");
    }
}
