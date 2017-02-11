package controller;

import Exceptions.ZuWenigMitarbeiterException;
import Unternehmung.Abteilungen.Forschung;
import Unternehmung.Unternehmen;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

/**
 * Created by boebel on 23.01.2017.
 */

@Path("companies/research/")
public class ResearchController {

    private static Gson gson = new Gson();

    @POST
    @Secured
    @Path("projects")
    public Response startProject(@Context SecurityContext securityContext, String attr) {
        Forschung forschung = getResearchByContext(securityContext);

        JsonObject obj = gson.fromJson(attr, JsonObject.class);
        String product = obj.get("product").getAsString();
        String quality = obj.get("quality").getAsString();
        int mitCount = obj.get("mitCount").getAsInt();
        int dauer = obj.get("dauer").getAsInt();
        boolean herstellkosten = obj.get("herstellkosten").getAsBoolean();

        try {
            forschung.starteProjekt(product+quality, mitCount, dauer, herstellkosten);
        } catch (ZuWenigMitarbeiterException e) {
            return Response.ok("ERROR:Mitarbeiter" + e.toString()).build();
        }

        return Response.ok().build();
    }

    @GET
    @Secured
    @Path("projects")
    public Response getProjects(@Context SecurityContext securityContext) {
        Forschung forschung = getResearchByContext(securityContext);
        return Response.ok(gson.toJson(forschung.getProjekte())).build();
    }
    @GET
    @Secured
    @Path("projects/finished")
    public Response getFinishedProjects(@Context SecurityContext securityContext) {
        Forschung forschung = getResearchByContext(securityContext);
        return Response.ok(gson.toJson(forschung.getAbgeschlosseneForschungen())).build();
    }



    public Forschung getResearchByContext(SecurityContext context) {
        Unternehmen unternehmen = CompanyController.getCompanyFromContext(context);
        return (Forschung) unternehmen.getAbteilung("forschung");
    }


}