package controller;

import Exceptions.BankruptException;
import Exceptions.LaufzeitZuHochException;
import Exceptions.ZuHochVerschuldetException;
import Exceptions.ZuWenigMitarbeiterException;
import Rules.Game;
import Unternehmung.Abteilungen.Finanzen;
import Unternehmung.Abteilungen.Vertrieb;
import Unternehmung.Unternehmen;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.ArrayList;


@Path("companies/sales")
public class SalesController {
    private static Gson gson = new Gson();

    @POST
    @Secured
    @Path("ausschreibungen")
    public Response bewerben(@Context SecurityContext securityContext, String i) {
        Unternehmen unternehmen = CompanyController.getCompanyFromContext(securityContext);
        int index = Integer.parseInt(i);
        Vertrieb sales = (Vertrieb) unternehmen.getAbteilung("vertrieb");

        try {
            sales.bewerben(Game.getAusschreibungen().get(index));
            return Response.ok("Auf Ausschreibung beworben").build();
        } catch (ZuWenigMitarbeiterException e) {
            return Response.status(409).entity(e.toString()).build();
        }
    }

    @GET
    @Path("opportunities")
    @Secured
    public Response getOpportunities(@Context SecurityContext securityContext) {
        Unternehmen unternehmen = CompanyController.getCompanyFromContext(securityContext);
        Vertrieb sales = (Vertrieb) unternehmen.getAbteilung("vertrieb");

        return Response.ok().entity(gson.toJson(new ArrayList<>(sales.getOpportunities()))).build();
    }

    @GET
    @Path("accounts")
    @Secured
    public Response getAccounts(@Context SecurityContext securityContext) {
        Unternehmen unternehmen = CompanyController.getCompanyFromContext(securityContext);
        Vertrieb sales = (Vertrieb) unternehmen.getAbteilung("vertrieb");

        return Response.ok().entity(gson.toJson(sales.getAccounts())).build();
    }

    @GET
    @Path("ausschreibungen")
    public Response getAusschreibungen() {
        return Response.ok(gson.toJson(Game.getAusschreibungen())).build();
    }

    @POST
    @Secured
    @Path("credits")
    public Response requestCredit(@Context SecurityContext securityContext, String element) {
        int betrag, laufzeit;
        JsonObject object = gson.fromJson(element, JsonObject.class);
        betrag   = object.get("betrag").getAsInt();
        laufzeit = object.get("laufzeit").getAsInt();

        try {
            Finanzen finanzen = getFinanzenFromContext(securityContext);
            finanzen.kreditAufnehmen(betrag, laufzeit);
            return Response.ok().build();
        }  catch (LaufzeitZuHochException e) {
            return Response.ok("ERROR:LAUFZEIT" + e.toString()).build();
        } catch (ZuHochVerschuldetException e) {
            return Response.ok("ERROR:VERSCHULDET_" + e.toString()).build();
        } catch (ZuWenigMitarbeiterException e) {
            return Response.ok("ERROR:MITARBEITER_" + e.toString()).build();
        } catch (BankruptException e) {
            return Response.ok("ERROR:BANKRUPT_" + e.toString()).build();
        }
    }

    @GET
    @Secured
    @Path("credits")
    public Response getCredits(@Context SecurityContext context)
    {
        Finanzen finanzen = getFinanzenFromContext(context);
        return Response.ok(gson.toJson(finanzen.getKredite())).build();
    }

    public Finanzen getFinanzenFromContext(SecurityContext context) {
        Unternehmen unternehmen = CompanyController.getCompanyFromContext(context);
        return (Finanzen) unternehmen.getAbteilung("finanzen");

    }
}
