package com.fallstudie.Interface;


import Unternehmen.Unternehmen;
import com.google.gson.Gson;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;



@Path("/")
public class GameInterface {
    private static Gson gson = new Gson();
    private static Map<Unternehmen,String> auth = new HashMap<Unternehmen,String>();

    private static Unternehmen[] companies = new Unternehmen[3];

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
    @Path("companies")
    public static Response newCompany(String msg) {
        System.out.println(gson.toJson(new Unternehmen("district gmbh", "wurst", 1000f)));
        Unternehmen test = gson.fromJson(msg, Unternehmen.class);

        for (int i = 0; i < companies.length; i++) {
            if (companies[i] == null) {
                System.out.println(test);
                companies[i] = test;
                return Response.status(200).entity(gson.toJson(test)).build();
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
    @Path("companies")
    public static Response getCompaniesPOST() {
        return Response.status(200).entity(gson.toJson(companies)).build();
    }

    @POST
    @Produces("application/json")
    @Consumes("application/x-www-form-urlencoded")
    public static Response authenticateUser(@FormParam("username") String companyName, @FormParam("password") String password) {
        try {
            authenticate(companyName, password);
            String token = issueToken(companyName);
            return Response.ok(token).build();
        } catch (Exception e) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    private static String issueToken(String companyName) throws Exception {
        SecureRandom random = new SecureRandom();
        String token =  new BigInteger(130, random).toString(32);
        Unternehmen u = getUnternehmenByName(companyName);

        if(u!=null)
            auth.put(getUnternehmenByName(companyName),token);
        else
            throw new Exception("Token could not be created");

        return token;

    }

    private static void authenticate(String companyName, String password) throws Exception {
        Unternehmen u = getUnternehmenByName(companyName);
        if(u != null && password.equals(u.getPasswort()))
            return;
        throw new Exception("No such User");
    }

    public static Unternehmen getUnternehmenByName(String name)
    {
        for (Unternehmen u : companies) {
            if(name.equals(u.getName()))
                return u;
        }
        return null;
    }

    public static Unternehmen[] getCompanies()
    {
        return companies;
    }

    public static void setCompany(int no,Unternehmen u)
    {
        companies[no] = u;
    }

    public static void validateToken(String token) throws NotAuthorizedException{
        if(!auth.containsValue(token))
        {
            throw new NotAuthorizedException("not authorized token used");
        }

    }
}

