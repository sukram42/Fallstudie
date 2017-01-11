package com.fallstudie.Interface;

import com.google.gson.Gson;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
//² // <- TODO wie kommt das da hin? ein Versehen? hat einen Error ausgelöst ;)
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

//        companies[no].getDepartment(department).addMitarbeiter(anzahl);
         return null;
//        return Response.status(200).entity(new Gson().toJson(companies[no].getDepartment(department).getMitarbeiter())).build();
    }


}
