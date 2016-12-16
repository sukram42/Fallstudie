package com.fallstudie.Interface;

import Unternehmen.Unternehmen;
import com.google.gson.Gson;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/companies")
public class Interface {
		
		private static Unternehmen[] companies = new Unternehmen[3]; 

		@GET
		@Path("/test")
		public static Response test()
		{
			return Response.status(200).entity("test").build();
		}

		@POST
		@Path("/{companyno}/{department}/employees/{anzahl}")
		public static Response newEmployee(@PathParam("companyno")int no, @PathParam("department") String department, @PathParam("anzahl") int anzahl){
			
			System.out.println(no);
			companies[no].getDepartment(department).addMitarbeiter(anzahl);
			
			return Response.status(200).entity(new Gson().toJson(anzahl + " Mitarbeiter wurden hinzugefügt")).build();
			
		}
		
		@GET
		@Path("/{companyno}/{department}")
		public static Response getEmployee(@PathParam("companyno")int no, @PathParam("department") String department){
			
			System.out.println(companies[no]);
			String erg = new Gson().toJson(companies[no].getDepartment(department).getMitarbeiter());
			
			return Response.status(200).entity(erg).build();
			
		}
		
		@POST
		public Response newCompany(String msg)
		{
			System.out.println(new Gson().toJson(new Unternehmen("district gmbh","wurst",1000f)));
			Unternehmen test = new Gson().fromJson(msg,Unternehmen.class);

			for(int i = 0; i < companies.length;i++)
			{
				if(companies[i]==null)
				{
					companies[i] = test;
					return Response.status(200).entity(test.toString()).build();
				}
			}
			
			return Response.status(218).entity(test.toString()).build();
		}
		
		@DELETE
		@Path("/{companyno}")
		public Response deleteCompany(@PathParam("companyno")int no)
		{
			try
			{
				companies[no] = null;
				return Response.status(200).entity("Unternehmen gelöscht").build();
	
			}catch(ArrayIndexOutOfBoundsException e)
			{
				return Response.status(404).entity("Index zu hoch").build();
			}
		}
			
		@POST
		@Path("/login")
		public static Response logIn(@HeaderParam("company") String user, @HeaderParam("passwort") String passwort)
		{
				
			for(Unternehmen u : companies)
			{
				if( u.getName().equals(user) && u.getPasswort().equals(passwort))
				{
					return Response.status(200).entity(u).build();
				}
			}
			return Response.status(401).entity("NO LOGIN").build();
		}
		
		
}
