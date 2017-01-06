package com.fallstudie.Interface;

import Unternehmung.Unternehmen;
import com.google.gson.Gson;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/companies")
public class Interface {

    private static Unternehmen[] companies = new Unternehmen[3];
    private static Gson gson = new Gson();

    /**
     * Testmethode für Schnittstelle
     * @return Response
     */
    @GET
    @Path("/testCompany")
    public static Response test() {
        Unternehmen testcomp = new Unternehmen("hallo GmbG", "passwort", 100f, 1000f);

        return Response.status(200).entity(new Gson().toJson(testcomp)).build();
    }

    @POST
    @Path("/testCompany")
    public static Response test2() {
        Unternehmen testcomp = new Unternehmen("hallo GmbG", "passwort", 100f, 1000f);

        return Response.status(200).entity(new Gson().toJson(testcomp)).build();
    }

    /**
     * Methode zum Erstellen neuer Arbeitnehmern
     *
     * TODO Implementieren des Gehaltes
     *
     * @param no Unternehmensnummer
     * @param department Abteilung in welche Arbeitnehmer gehen
     * @param anzahl Anzahl der Arbeitnehmer
     * @return
     * */
    @POST
    @Produces("application/json;charset=UTF-8")
    @Path("/{companyno}/{department}/employees/{anzahl}")
    public static Response newEmployee(@PathParam("companyno") int no, @PathParam("department") String department, @PathParam("anzahl") int anzahl) {

        System.out.println(no);
        System.out.println(companies[no].getAbteilung(department));
        companies[no].getAbteilung(department).addMitarbeiter(anzahl);

        return Response.status(200).entity(new Gson().toJson(companies[no].getAbteilung(department).getMitarbeiter())).build();
    }

    /**
     * Getter Methode für
     * @param no
     * @param department
     *
     * @return gib eine Response zurück mit Statuswert 200 und gibt JSON zurück
     */
    @GET
    @Produces("application/json;charset=UTF-8")
    @Path("/{companyno}/{department}/employees")
    public static Response getEmployee(@PathParam("companyno") int no, @PathParam("department") String department) {

        System.out.println(companies[no]);
        String erg = gson.toJson(companies[no].getAbteilung(department).getMitarbeiter());

        return Response.status(200).entity(erg).build();
    }

    /**
     * Erstellt eine neue Company
     * Erwartet einen JSON Code
     *
     * TODO Umschreiben in verschiedene Parameter
     *
     * @param msg JSON Code
     * @return
     */
    @POST
    public Response newCompany(String msg) {
        System.out.println(new Gson().toJson(new Unternehmen("district gmbh", "wurst", 1000f)));
        Unternehmen test = new Gson().fromJson(msg, Unternehmen.class);

        for (int i = 0; i < companies.length; i++) {
            if (companies[i] == null) {
                companies[i] = test;
                return Response.status(200).entity(test.toString()).build();
            }
        }

        return Response.status(218).entity(test.toString()).build();
    }

    /**
     * Gibt alle Unternehmen zurück.
     *
     * @return Unternehmen als Array in JSON Form
     */
    @GET
    public Response getCompanies() {
        return Response.status(200).entity(gson.toJson(companies)).build();
    }

    /**
     * Gibt einzelndes Unternehmen zurück
     * @param no Unternemensnummer
     * @return Unternehmen in JSON Form
     */
    @GET
    @Path("/{companyno}")
    public Response getCompanies(@PathParam("companyno") int no) {
        return Response.status(200).entity(gson.toJson(companies[no])).build();
    }


    /**
     * Löscht ein Unternehmen
     * @param no Unternehmensnummer
     * @return
     */
    @DELETE
    @Path("/{companyno}")
    public Response deleteCompany(@PathParam("companyno") int no) {
        try {
            companies[no] = null;
            return Response.status(200).entity("Unternehmen gelöscht").build();

        } catch (ArrayIndexOutOfBoundsException e) {
            return Response.status(404).entity("Index zu hoch").build();
        }
    }

    /**
     * Prüft ob HeaderParameter dem Unternehmen passen
     * @param user
     * @param passwort
     * @return 200 oder 401
     */
    @POST
    @Path("/login")
    public static Response logIn(@HeaderParam("company") String user, @HeaderParam("passwort") String passwort) {

        for (Unternehmen u : companies) {
            if (u.getName().equals(user) && u.getPasswort().equals(passwort)) {
                return Response.status(200).entity(u).build();
            }
        }
        return Response.status(401).entity("NO LOGIN").build();
    }


}
