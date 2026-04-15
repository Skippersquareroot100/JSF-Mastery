package com.mycompany.jsf.lifecycle.lab.resources;

import jakarta.ws.rs.Path;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

/**
 * Jakarta REST Resource
 * @author maruf.anik
 */
@Path("/jakartaee8")
public class JakartaEE8Resource {
    
    @GET
    public Response ping() {
        return Response
                .ok()
                .entity("Ping from JakartaEE")
                .build();
    }
}
