package com.fallstudie.Interface;

import Rules.Game;
import Unternehmung.Abteilung;
import Unternehmung.Abteilungen.HR;
import Unternehmung.Kennzahlen.Kennzahl;
import Unternehmung.Mitarbeiter;
import Unternehmung.Unternehmen;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.ArrayList;

@Path("/companies")
public class CompanyInterface {

    private static Gson gson = new Gson();

    /**
     * Erstellt eine neue Company
     * Erwartet einen JSON Code
     * <p>
     * TODO Umschreiben in verschiedene Parameter
     *
     * @param msg JSON Code
     * @return
     */

    @POST
    public Response newCompany(String msg) {
        //jsonCompany ist das Unternehmen, welches mit dem Json file in msg übergeben wird.
        Unternehmen jsonCompany = gson.fromJson(msg, Unternehmen.class);
        //In company wird ein neues Unternehmen erstellt.
        Unternehmen company = new Unternehmen(jsonCompany.getName(), jsonCompany.getPasswort(), 1000f);
        if (!Game.getCompanies().contains(company)) {
            Game.getCompanies().add(company);
            return Response.status(200).entity(company.toString()).build();
        }
        return Response.status(218).entity(company.toString()).build();
    }


    /**
     * Gibt das Unternehmen zurück, dessen Token man im Header trägt.
     *
     * @return Unternehmen in JSON Form
     */
    @GET
    @Secured
    public Response getCompanies(@Context SecurityContext securityContext) {
        return Response.status(200).entity(gson.toJson(getCompanyFromContext(securityContext))).build();
    }

    @GET
    @Secured
    @Path("employees")
    public Response getEmployees(@Context SecurityContext context)
    {
        Unternehmen unternehmen = getCompanyFromContext(context);
        ArrayList<Mitarbeiter> mitarbeiter = ((HR)unternehmen.getAbteilung("hr")).getTotalMitarbeiter();
//        Mitarbeiter[] array = mitarbeiter.toArray(new Mitarbeiter[mitarbeiter.size()]);
        return Response.ok(gson.toJson(mitarbeiter)).build();
    }

    @POST
    @Secured
    @Path("employees")
    public Response addEmployee(@Context SecurityContext context, String data)
    {
        JsonObject object = gson.fromJson(data, JsonObject.class);

        Unternehmen unternehmen = getCompanyFromContext(context);
        Abteilung abteilung1 = unternehmen.getAbteilung(object.get("abteilung").getAsString().toLowerCase());
        if(abteilung1 != null)
        {
            abteilung1.addMitarbeiter(object.get("anzahl").getAsInt(),object.get("gehalt").getAsInt());
        }
        else
            return Response.serverError().entity("Abteilung nicht vorhanden").build();

        System.err.println(object.get("anzahl") + " Mitarbeiter erstellt ");
        return Response.ok().build();
    }

    @GET
    @Secured
    @Path("/employees/count")
    public Response getEmployeeCount(@Context SecurityContext context)
    {
        Unternehmen unternehmen = getCompanyFromContext(context);
        int anzahl = ((HR)unternehmen.getAbteilung("hr")).getTotalMitarbeiterCount();
        return Response.ok(anzahl).build();
    }

    @GET
    @Secured
    @Path("keyfigures/soft/{keyfigure}")
    public Response getKeyFigure(@Context SecurityContext securityContext, @PathParam("keyfigure") String keyfigure) {

        Kennzahl kennzahl = getCompanyFromContext(securityContext).getKennzahlensammlung().getWeicheKennzahl(keyfigure);

            if (kennzahl != null) {
                System.out.println("WERT:" + kennzahl.getWert());
                return Response.ok(kennzahl.getWert()).build();
            }
            else
                return Response.ok(-1).build();
    }


    private Unternehmen getCompanyFromContext(SecurityContext context) {

        return context!=null?Game.getUnternehmenByName(context.getUserPrincipal().getName()):null;
    }

}
