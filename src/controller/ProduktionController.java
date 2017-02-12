package controller;

import Exceptions.ZuWenigMaschinenstellplatzException;
import Exceptions.ZuWenigMitarbeiterOderMaschinenException;
import Unternehmung.Abteilungen.Produktion;
import Unternehmung.Objekte.Maschine;
import Unternehmung.Objekte.Produktlinie;
import Unternehmung.Unternehmen;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.json.simple.JSONObject;

import javax.swing.*;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

/**
 * Created by boebel on 18.01.2017.
 */


@Path("companies/production")
public class ProduktionController {
    private static Gson gson = new Gson();

    @POST
    @Secured
    public Response createProduktLinie(@Context SecurityContext securityContext, String content) {
        try {

            System.err.println("Content : " + content);

            Unternehmen unternehmen = CompanyController.getCompanyFromContext(securityContext);
            JsonObject object = gson.fromJson(content, JsonObject.class);
            ((Produktion) unternehmen.getAbteilung("produktion"))
                    .produzieren(object.get("produkt").getAsString(),
                            object.get("quality").getAsCharacter(),
                            object.get("menge").getAsInt(),
                            object.get("laufzeit").getAsInt()
                    );
            return Response.ok().entity(content).build();
        } catch (ZuWenigMitarbeiterOderMaschinenException e) {
            return Response.status(200).entity("ERROR:" + e.toString()).build();
        }
    }

    @GET
    @Secured
    @Path("productlines")
    public Response getProductLines(@Context SecurityContext securityContext) {
        Unternehmen unternehmen = CompanyController.getCompanyFromContext(securityContext);

        CopyOnWriteArrayList<Produktlinie> auftraege = ((Produktion) unternehmen.getAbteilung("produktion")).getAufträge();
        Map<String, ArrayList<Produktlinie>> liste = new HashMap<>();


        liste.put("Rucksack", (ArrayList<Produktlinie>) auftraege
                .stream()
                .filter(s -> s.getId().startsWith("Rucksack") && !s.getId().startsWith("Rucksacktech"))
                .collect(Collectors.toList()));
        liste.put("Rucksacktech", (ArrayList<Produktlinie>) auftraege
                .stream()
                .filter(s -> s.getId().startsWith("Rucksacktech"))
                .collect(Collectors.toList()));
        liste.put("Duffel", (ArrayList<Produktlinie>) auftraege
                .stream()
                .filter(s -> s.getId().contains("Duffel"))
                .collect(Collectors.toList()));
        liste.put("Reisetasche", (ArrayList<Produktlinie>) auftraege
                .stream()
                .filter(s -> s.getId().contains("Reisetasche"))
                .collect(Collectors.toList()));


        return Response.ok(gson.toJson(liste)).build();
    }

    @POST
    @Path("machines")
    @Secured
    public Response buyMachine(@Context SecurityContext securityContext, String content) {
        System.err.println("Content : " + content);
        try {
            Unternehmen unternehmen = CompanyController.getCompanyFromContext(securityContext);
            JsonObject object = gson.fromJson(content, JsonObject.class);

            int anzahl;
            anzahl = object.get("anzahl") != null ? object.get("anzahl").getAsInt() : 1;
            ((Produktion) unternehmen.getAbteilung("produktion"))
                    .maschinenKaufen(object.get("product").getAsString(), object.get("klasse").getAsInt(), anzahl);

            return Response.ok().entity(content).build();
        } catch (ZuWenigMaschinenstellplatzException e) {
            return Response.status(200).entity("ERROR:" + e.toString()).build();
        }

    }

    @POST
    @Path("warehouses")
    @Secured
    public Response buyWarehouse(@Context SecurityContext securityContext, String content) {
//        try {
        Unternehmen unternehmen = CompanyController.getCompanyFromContext(securityContext);
        int groeße = Integer.parseInt(content);

        ((Produktion) unternehmen.getAbteilung("produktion"))
                .lagerhalleKaufen(groeße);

        return Response.ok("Lagerhalle erfolgreich gebaut").build();
//        } catch (Exception e) {
//            return Response.serverError().entity(e.toString()).build();
//        }
    }

    @POST
    @Path("halls")
    @Secured
    public Response buyProductionHall(@Context SecurityContext securityContext, String content) {
        try {
            Unternehmen unternehmen = CompanyController.getCompanyFromContext(securityContext);
            int groesse = Integer.parseInt(content);

            ((Produktion) unternehmen.getAbteilung("produktion"))
                    .produktionshalleKaufen(groesse);

            return Response.ok("Produktionshalle erfolgreich gebaut").build();
        } catch (Exception e) {
            return Response.serverError().entity(e.toString()).build();
        }
    }

    @GET
    @Path("halls/capacities")
    @Secured
    public Response getProductionHallCapacity(@Context SecurityContext securityContext) {
        try {
            Unternehmen unternehmen = CompanyController.getCompanyFromContext(securityContext);
            Produktion produktion = (Produktion) unternehmen.getAbteilung("produktion");
            JSONObject object = new JSONObject();

            int frei = produktion.getFreienProduktionshallenPlatz();
            int gesamt = produktion.getGesamtenProduktionshallenPlatz();
            object.put("free", frei);
            object.put("gesamt", gesamt);

            return Response.ok(object.toJSONString()).build();

        } catch (Exception e) {
            return Response.serverError().entity(e.toString()).build();
        }
    }

    @GET
    @Path("warehouses/capacities")
    @Secured
    public Response getWarehouseCapacity(@Context SecurityContext securityContext) {
        try {
            Unternehmen unternehmen = CompanyController.getCompanyFromContext(securityContext);
            Produktion produktion = (Produktion) unternehmen.getAbteilung("produktion");
            JSONObject object = new JSONObject();

            int frei = produktion.getFreienLagerPlatz();
            int gesamt = produktion.getGesamtenLagerPlatz();
            object.put("free", frei);
            object.put("gesamt", gesamt);

            return Response.ok(object.toJSONString()).build();

        } catch (Exception e) {
            return Response.serverError().entity(e.toString()).build();
        }
    }


    @GET
    @Path("machines")
    @Secured
    public Response getMachines(@Context SecurityContext securityContext) {
        try {
            Unternehmen unternehmen = CompanyController.getCompanyFromContext(securityContext);
            Produktion produktion = (Produktion) unternehmen.getAbteilung("produktion");

            return Response.ok(gson.toJson(produktion.getMaschinen())).build();

        } catch (Exception e) {
            return Response.serverError().entity(e.toString()).build();
        }
    }

    @GET
    @Secured
    @Path("machines/{no}/status")
    public Response getMachinesStatus(@Context SecurityContext securityContext, @PathParam("no") String machinesNo) {
        Unternehmen unternehmen = CompanyController.getCompanyFromContext(securityContext);
        Produktion produktion = (Produktion) unternehmen.getAbteilung("produktion");

        try {
            int no = Integer.parseInt(machinesNo);
            return Response.ok(produktion.getMaschinen().get(no).getStatus()).build();
        } catch (NumberFormatException e) {
            return Response.status(500).entity(gson.toJson(e)).build();
        }

    }


    @PUT
    @Path("machines")
    @Secured
    public Response repairMachines(@Context SecurityContext securityContext, String number) {
        try {
            Unternehmen unternehmen = CompanyController.getCompanyFromContext(securityContext);
            Produktion produktion = (Produktion) unternehmen.getAbteilung("produktion");

            int index = Integer.parseInt(number);
            produktion.getMaschinen().get(index).reparieren(unternehmen.getKennzahlensammlung());

            return Response.ok("Maschine repariert").build();

        } catch (Exception e) {
            return Response.serverError().entity(e.toString()).build();
        }
    }

    @DELETE
    @Path("machines/{number}")
    @Secured
    public Response sellMachines(@Context SecurityContext securityContext, @PathParam("number") String number) {
        System.err.println(number);
        try {
            Unternehmen unternehmen = CompanyController.getCompanyFromContext(securityContext);
            Produktion produktion = (Produktion) unternehmen.getAbteilung("produktion");

            int index = Integer.parseInt(number);
            float preis = produktion.maschineVerkaufen(index);

            return Response.ok("SOLD " + preis).build();
        } catch (Exception e) {
            return Response.serverError().entity(e.toString()).build();
        }
    }

    @GET
    @Path("machines/energy")
    public Response getEnergiekosten() {
        return Response.ok(Maschine.getStaticEnergiekosten()).build();
    }
}