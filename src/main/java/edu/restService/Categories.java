package edu.restService;

import edu.matc.entity.Category;
import edu.matc.persistence.GenericDao;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/categories")
public class Categories {


    private GenericDao genericDao;

    public Categories() {

        genericDao = new GenericDao<>(Category.class);
    }

    @GET


    @Produces("text/plain")
    public Response getCategories() {

        String output = "Here are the categories " + genericDao.getAll();
        return Response.status(200).entity(output).build();
    }

    @GET

    @Path("/{param}")

    @Produces("text/plain")

    /**
     * This method's purpose is to get the category by id
     */
    public Response getCategoryById(@PathParam("param") int id) {

        // Return the category specified by the id
        String output = "Here's the category: " + genericDao.getById(id);
        return Response.status(200).entity(output).build();
    }
}

