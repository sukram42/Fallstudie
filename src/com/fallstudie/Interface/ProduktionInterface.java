package com.fallstudie.Interface;

import Exceptions.ZuWenigMaschinenstellplatzException;
import Exceptions.ZuWenigMitarbeiterOderMaschinenException;
import Unternehmung.Abteilungen.Produktion;
import Unternehmung.Produktlinie;
import Unternehmung.Unternehmen;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.json.simple.JSONObject;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by boebel on 18.01.2017.
 */


@Path("companies/production")
public class ProduktionInterface {
    private static Gson gson = new Gson();

    @POST
    @Secured
    public Response createProduktLinie(@Context SecurityContext securityContext, String content) {
        try {

            System.err.println("Content : " + content);

            Unternehmen unternehmen = CompanyInterface.getCompanyFromContext(securityContext);
            JsonObject object = gson.fromJson(content, JsonObject.class);
            ((Produktion) unternehmen.getAbteilung("produktion"))
                    .produzieren(object.get("produkt").getAsString(),
                            object.get("quality").getAsCharacter(),
                            object.get("menge").getAsInt(),
                            object.get("laufzeit").getAsInt()
                    );
            return Response.ok().entity(content).build();
        } catch (ZuWenigMitarbeiterOderMaschinenException e) {
            return Response.status(409).entity(e.toString()).build();
        }
    }

    @GET
    @Secured
    @Path("productlines")
    public Response getProductLines(@Context SecurityContext securityContext) {
        Unternehmen unternehmen = CompanyInterface.getCompanyFromContext(securityContext);

        ArrayList<Produktlinie> auftraege = ((Produktion) unternehmen.getAbteilung("produktion")).getAufträge();
        Map<String, ArrayList<Produktlinie>> liste = new HashMap<>();


        liste.put("Rucksack", (ArrayList<Produktlinie>) auftraege
                .stream()
                .filter(s -> s.getId().contains("Rucksack"))
                .collect(Collectors.toList()));
        liste.put("Rucksacktech",(ArrayList<Produktlinie>) auftraege
                .stream()
                .filter(s->"rucksacktech".equals(s.getProdukt()))
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
            Unternehmen unternehmen = CompanyInterface.getCompanyFromContext(securityContext);
            JsonObject object = gson.fromJson(content, JsonObject.class);

            int anzahl;
            anzahl = object.get("anzahl") != null ? object.get("anzahl").getAsInt() : 1;
            ((Produktion) unternehmen.getAbteilung("produktion"))
                    .maschinenKaufen(object.get("product").getAsString(), object.get("klasse").getAsInt(), anzahl);

            return Response.ok().entity(content).build();
        } catch (ZuWenigMaschinenstellplatzException e) {
            return Response.status(409).entity(e.toString()).build();
        }

    }

    @POST
    @Path("warehouses")
    @Secured
    public Response buyWarehouse(@Context SecurityContext securityContext, String content) {
//        try {
            Unternehmen unternehmen = CompanyInterface.getCompanyFromContext(securityContext);
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
            Unternehmen unternehmen = CompanyInterface.getCompanyFromContext(securityContext);
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
            Unternehmen unternehmen = CompanyInterface.getCompanyFromContext(securityContext);
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
            Unternehmen unternehmen = CompanyInterface.getCompanyFromContext(securityContext);
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
            Unternehmen unternehmen = CompanyInterface.getCompanyFromContext(securityContext);
            Produktion produktion = (Produktion) unternehmen.getAbteilung("produktion");

            return Response.ok(gson.toJson(produktion.getMaschinen())).build();

        } catch (Exception e) {
            return Response.serverError().entity(e.toString()).build();
        }
    }


    @PUT
    @Path("machines")
    @Secured
    public Response repairMachines(@Context SecurityContext securityContext,String number) {
        try {
            Unternehmen unternehmen = CompanyInterface.getCompanyFromContext(securityContext);
            Produktion produktion = (Produktion) unternehmen.getAbteilung("produktion");

            int index = Integer.parseInt(number);
            produktion.getMaschinen().get(index).reparieren(unternehmen.getKennzahlensammlung());

            return Response.ok("Maschine repariert").build();

        } catch (Exception e) {
            return Response.serverError().entity(e.toString()).build();
        }
    }

}