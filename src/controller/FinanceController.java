package controller;

import Unternehmung.Kennzahlen.Bilanz;
import Unternehmung.Kennzahlen.Kennzahl;
import Unternehmung.Kennzahlensammlung;
import Unternehmung.Unternehmen;
import com.google.gson.Gson;
import org.json.simple.JSONObject;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.*;

/**
 * Created by boebel on 23.01.2017.
 */

@Path("companies/keyfigures/")
public class FinanceController {

    private static Gson gson = new Gson();

    @GET
    @Secured
    @Path("bilanz")
    public Response getBilanz(@Context SecurityContext context)
    {
        Unternehmen unternehmen = CompanyController.getCompanyFromContext(context);
        Bilanz bilanz = unternehmen.getKennzahlensammlung().getBilanz();
        return Response.ok(gson.toJson(bilanz)).build();

    }

    @GET
    @Secured
    @Path("soft/{keyfigure}")
    public Response getKeyFigure(@Context SecurityContext securityContext, @PathParam("keyfigure") String keyfigure) {

        Kennzahl kennzahl = CompanyController.getCompanyFromContext(securityContext).getKennzahlensammlung().getWeicheKennzahl(keyfigure);

        if (kennzahl != null) {
            return Response.ok(kennzahl.getWert()).build();
        }
        else
            return Response.ok(-1).build();
    }

    @GET
    @Secured
    @Path("")
    public Response getKennzahlensammlung(@Context SecurityContext securityContext) {
        Kennzahlensammlung kennzahl = CompanyController.getCompanyFromContext(securityContext).getKennzahlensammlung();
        kennzahl.getBilanz().setSummen();
            return Response.ok(gson.toJson(kennzahl)).build();
    }

    @GET
    @Secured
    @Path("soft")
    public Response getSoftKeyFigures(@Context SecurityContext securityContext)
    {
        Unternehmen unternehmen = CompanyController.getCompanyFromContext(securityContext);
        JSONObject object = new JSONObject();
        Kennzahlensammlung ks = unternehmen.getKennzahlensammlung();
        object.put("mitarbeiterzufriedenheit",ks.getWeicheKennzahl("mitarbeiterzufriedenheit").berechnen()*100f);
        object.put("image",ks.getWeicheKennzahl("image").berechnen()*100f);
        object.put("kundenzufriedenheit",ks.getWeicheKennzahl("kundenzufriedenheit").berechnen()*100f);
        object.put("bekanntheitsgrad",ks.getWeicheKennzahl("bekanntheitsgrad").berechnen()*100f);
        object.put("marktanteil",ks.getMarktanteil()*100f);
        return Response.ok().entity(gson.toJson(object)).build();
    }
    @GET
    @Secured
    @Path("archiv")
    public Response getArchiv(@Context SecurityContext securityContext)
    {
        Unternehmen unternehmen = CompanyController.getCompanyFromContext(securityContext);
        Queue<Map.Entry> archiv = unternehmen.getKennzahlensammlung().getGuv().getArchiv();
//        List<Map.Entry> erg = new ArrayList<>();


        return Response.ok(gson.toJson(archiv)).build();
    }

}