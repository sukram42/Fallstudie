package com.fallstudie.Interface;

import Exceptions.ZuWenigMitarbeiterException;
import Rules.Game;
import Unternehmung.Abteilung;
import Unternehmung.Abteilungen.HR;
import Unternehmung.Abteilungen.SozialeLeistungen;
import Unternehmung.Abteilungen.SozialeProjekte.SozialProjekt;
import Unternehmung.Kennzahlen.Kennzahl;
import Unternehmung.Kennzahlensammlung;
import Unternehmung.Mitarbeiter;
import Unternehmung.Unternehmen;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.json.simple.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.ArrayList;
import java.util.List;

@Path("/companies")
public class CompanyInterface {

    private static Gson gson = new Gson();

    public static Unternehmen getCompanyFromContext(SecurityContext context) {

        return context!=null?Game.getUnternehmenByName(context.getUserPrincipal().getName()):null;
    }

    /**
     * Erstellt eine neue Company
     * Erwartet einen JSON Code
     * <p>
     *
     * @param msg JSON Code
     * @return
     */

    @POST
    public Response newCompany(String msg) {
        //jsonCompany ist das Unternehmen, welches mit dem Json file in msg übergeben wird.
        Unternehmen jsonCompany = gson.fromJson(msg, Unternehmen.class);
        //In company wird ein neues Unternehmen erstellt.
        Unternehmen company = new Unternehmen(jsonCompany.getName(), jsonCompany.getPasswort(), 100000f);
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
        return Response.ok(gson.toJson(mitarbeiter)).build();
    }

    @GET
    @Secured
    @Path("bankrupt")
    public Response isBankrupt(@Context SecurityContext context)
    {
        Unternehmen unternehmen = getCompanyFromContext(context);
        return Response.ok(unternehmen.getKennzahlensammlung().isBankrupt()).build();
    }

    @Secured
    @GET
    @Path("employees/production")
    public Response getEmployeeCountInProduction(@Context SecurityContext context)
    {
        Unternehmen unternehmen = getCompanyFromContext(context);
        int anzahl = unternehmen.getAbteilung("produktion").getMitarbeiterAnzahl();
        return Response.ok(anzahl).build();
    }

    @Secured
    @GET
    @Path("employees/marketing")
    public Response getEmployeeCountInMarketing(@Context SecurityContext context)
    {
        Unternehmen unternehmen = getCompanyFromContext(context);
        int anzahl = unternehmen.getAbteilung("marketing").getMitarbeiterAnzahl();
        return Response.ok(anzahl).build();
    }

    @Secured
    @GET
    @Path("employees/research")
    public Response getEmployeeCountInResearch(@Context SecurityContext context)
    {
        Unternehmen unternehmen = getCompanyFromContext(context);
        int anzahl = unternehmen.getAbteilung("forschung").getMitarbeiterAnzahl();
        return Response.ok(anzahl).build();
    }

    @Secured
    @GET
    @Path("employees/hr")
    public Response getEmployeeCountInProductionHR(@Context SecurityContext context)
    {
        Unternehmen unternehmen = getCompanyFromContext(context);
        int anzahl = unternehmen.getAbteilung("hr").getMitarbeiterAnzahl();
        return Response.ok(anzahl).build();
    }

    @GET
    @Secured
    @Path("employees/sales")
    public Response getEmployeeCountInSales(@Context SecurityContext context)
    {
        Unternehmen unternehmen = getCompanyFromContext(context);
        int anzahl = unternehmen.getAbteilung("vertrieb").getMitarbeiterAnzahl();
        return Response.ok(anzahl).build();
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
            try {
                abteilung1.addMitarbeiter(object.get("anzahl").getAsInt(), object.get("gehalt").getAsInt());
            } catch (ZuWenigMitarbeiterException e){
                return Response.status(200).entity("ERROR:" + e.toString()).build();
            }
        }
        else
            return Response.serverError().entity("Abteilung nicht vorhanden").build();

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

    @PUT
    @Secured
    @Path("employees")
    public Response fireEmployee(@Context SecurityContext context, String mitarbeiter)
    {
        try {
            Unternehmen unternehmen = getCompanyFromContext(context);
            Mitarbeiter mitarbeiter1 = new Gson().fromJson(mitarbeiter, Mitarbeiter.class);

            boolean gekuendigt = ((HR) unternehmen.getAbteilung("hr")).kuendigeMitarbeiter(mitarbeiter1);

            return gekuendigt ? Response.ok("Mitarbeiter gekuendigt").build()
                              : Response.ok("Mitarbeiter nicht gefunden : " + mitarbeiter).build();
        }catch(Exception e)
        {
            return Response.status(409).entity(e.toString() + " "+ mitarbeiter).build();
        }
    }

    @PUT
    @Secured
    @Path("/employees/socialprojects")
    public Response changeSocialProject(@Context SecurityContext securityContext,String name)
    {
        Unternehmen unternehmen = getCompanyFromContext(securityContext);
        SozialeLeistungen sl = (SozialeLeistungen) unternehmen.getAbteilung("sozialeLeistungen");
        try {
            sl.changeProjectActivity(name);
            return Response.ok("started").build();
        } catch (Exception e) {
            return Response.serverError().entity("Project could not be started").build();
        }
    }

    @GET
    @Secured
    @Path("/employees/socialprojects")
    public Response getSocialProjects(@Context SecurityContext securityContext)
    {
        Unternehmen unternehmen = getCompanyFromContext(securityContext);
        List<SozialProjekt> projects = ((SozialeLeistungen)unternehmen.getAbteilung("sozialeLeistungen")).getProjects();
        return Response.ok(gson.toJson(projects)).build();
    }

}
