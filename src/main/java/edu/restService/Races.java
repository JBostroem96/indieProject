
/*
package edu.restService;

import edu.matc.entity.Race;
import edu.matc.persistence.GenericDao;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;


public class Races {
    // The Java method will process HTTP GET requests
    private GenericDao genericDao;


    public Races() {

        genericDao = new GenericDao<>(Races.class);
    }

    @GET
    @Path("races/")
    // The Java method will produce content identified by the MIME Media type "text/plain"
    @Produces("text/html")
    public Response getRaces(String race) {

        String output = "Here are races <html><body><h1>" + genericDao.getAll();
        return Response.status(200).entity(output).build();
    }
}*/