package com.fallstudie.Interface;


import Rules.Game;
import Unternehmung.Unternehmen;
import com.google.gson.Gson;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

@Path("/")
public class GameInterface implements ServletContextListener{
    private  Gson gson = new Gson();
    private  static Map<Unternehmen, String> auth = new HashMap<>();

    private  Game game;

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
    @Path("companies")
    public  Response newCompany(String msg) {
        System.out.println(gson.toJson(new Unternehmen("district gmbh", "wurst", 1000f)));
       //jsonCompany ist das Unternehmen, welches mit dem Json file in msg übergeben wird.
        Unternehmen jsonCompany = gson.fromJson(msg, Unternehmen.class);
        //in company wird ein neues Unternehmen erstellt.
        Unternehmen company = new Unternehmen(jsonCompany.getName(),jsonCompany.getPasswort(),1000f);
            if(!game.getCompanies().contains(company)) {
                game.getCompanies().add(company);
                return Response.status(200).entity(gson.toJson(company)).build();
            }
        return Response.status(218).entity(company.toString()).build();
    }

//    /**
//     * Gibt alle Unternehmen zurück.
//     *
//     * @return Unternehmen als Array in JSON Form
//     */
//    @GET
//    @Path("companies")
//    public  Response getCompaniesPOST() {
//        return Response.status(200).entity(gson.toJson(companies)).build();
//    }

    @POST
    @Produces("application/json")
    @Consumes("application/x-www-form-urlencoded")
    public  Response authenticateUser(@FormParam("username") String companyName, @FormParam("password") String password) {
        try {
            authenticate(companyName, password);
            String token = issueToken(companyName);
            return Response.ok(token).build();
        } catch (Exception e) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    /**
     * Gibt einzelndes Unternehmen zurück
     *
     * @return Unternehmen in JSON Form
     */
    @GET
    @Secured
    @Path("companies")
    public Response getCompanies(@Context SecurityContext securityContext) {
        return Response.status(200).entity(gson.toJson(game.getUnternehmenByName(securityContext.getUserPrincipal().getName()))).build();
    }

    @GET
    @Path("auth")
    public Response validate(@HeaderParam("Authorization") String authHeader) {
        String tmp_token;
        boolean auth=true;
        System.out.println(authHeader);
        if (authHeader.startsWith("Bearer ")) {
            tmp_token = authHeader.substring("Bearer ".length()).trim();

            System.out.println(tmp_token);

            try
            {
                validateToken(tmp_token);
            }catch(NotAuthorizedException e)
            {

                System.out.println("Not auth");
              auth =false;
            }
        }else auth = false;
        if(auth)
            return Response.status(Response.Status.OK).entity(true).build();
        return Response.status(Response.Status.OK).entity(false).build();
    }

    private String issueToken(String companyName) throws Exception {
        SecureRandom random = new SecureRandom();
        String token = new BigInteger(130, random).toString(32);
        Unternehmen u = game.getUnternehmenByName(companyName);

        if (u != null)
            auth.put(game.getUnternehmenByName(companyName), token);
        else
            throw new Exception("Token could not be created");

        return token;

    }

    private void authenticate(String companyName, String password) throws Exception {
        Unternehmen u = game.getUnternehmenByName(companyName);
        if (u != null && password.equals(u.getPasswort()))
            return;
        throw new Exception("No such User");
    }

    public static void validateToken(String token) throws NotAuthorizedException {
        if (!auth.containsValue(token)) {
            throw new NotAuthorizedException("not authorized token used");
        }
    }

    public static Unternehmen getUnternehmenByToken(String token) {
        for (Map.Entry<Unternehmen, String> entry : auth.entrySet()) {
            if (entry.getValue().equals(token))
                return entry.getKey();
        }
        return null;
    }

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        if(game == null)
        {
            game = new Game();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}



