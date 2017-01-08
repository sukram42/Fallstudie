package com.fallstudie.Interface;

import Unternehmung.Unternehmen;
import com.google.gson.Gson;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/companies/{companyno}")
public class CompanyInterface {

    private static Gson gson = new Gson();


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
        System.out.println(new String("angèle"));
//        companies[no].getDepartment(department).addMitarbeiter(anzahl);
         return null;
//        return Response.status(200).entity(new Gson().toJson(companies[no].getDepartment(department).getMitarbeiter())).build();
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

        System.out.println(GameInterface.getCompanies());
//        String erg = gson.toJson(companies[no].getDepartment(department).getMitarbeiter());
        return null;
//        return Response.status(200).entity(erg).build();
    }


    /**
     * Gibt einzelndes Unternehmen zurück
     * @param no Unternemensnummer
     * @return Unternehmen in JSON Form
     */
    @GET
    @Secured
    public Response getCompanies(@PathParam("companyno") int no) {
        return Response.status(200).entity(gson.toJson(GameInterface.getCompanies()[no])).build();
    }

    /**
     * Löscht ein Unternehmen
     * @return Response
     */
    @DELETE
    public Response deleteCompany() {
        int no = 0;
        System.out.println("halloo" + no);
        try {
            GameInterface.setCompany(no,null);
            return Response.status(200).entity("Unternehmen gelöscht").build();

        } catch (ArrayIndexOutOfBoundsException e) {
            return Response.status(404).entity("Index zu hoch").build();
        }
    }

}
