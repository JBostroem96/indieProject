package edu.restService;

import edu.matc.entity.Team;
import edu.matc.persistence.GenericDao;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;


@Path("/teams")
public class Teams {

    private GenericDao genericDao;

    /**
     * Instantiates a new Teams.
     */
    public Teams() {

        genericDao = new GenericDao<>(Team.class);
    }

    @GET

    @Produces("text/plain")
    public Response getTeams() {

        String output = "Here are the teams " + genericDao.getAll();
        return Response.status(200).entity(output).build();
    }

    @GET

    @Path("/{param}")

    @Produces("text/plain")

    /**
     * This method's purpose is to get the team by id
     */
    public Response getTeamById(@PathParam("param") int id) {

        // Return the team specified by the id
        String output = "Here's the team: " + genericDao.getById(id);
        return Response.status(200).entity(output).build();
    }
}

