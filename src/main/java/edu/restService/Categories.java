package edu.restService;

import edu.matc.entity.Category;
import edu.matc.entity.Team;
import edu.matc.persistence.GenericDao;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/categories")
public class Categories {

    // The Java method will process HTTP GET requests
    private GenericDao genericDao;

    public Categories() {

        genericDao = new GenericDao<>(Category.class);
    }

    @GET

    // The Java method will produce content identified by the MIME Media type "text/plain"
    @Produces("text/plain")
    public Response getCategories() {

        String output = "Here are the categories " + genericDao.getAll();
        return Response.status(200).entity(output).build();
    }

    @GET

    @Path("/{param}")

    @Produces("text/plain")

    /**
     * This method's purpose is to get the race by id
     */

    public Response getRaceById(@PathParam("param") int id) {

        // Return the user specified by the id
        String output = "Here's the category: " + genericDao.getById(id);
        return Response.status(200).entity(output).build();
    }
}

