package edu.controller;


import edu.matc.entity.Race;
import edu.matc.entity.TeamRace;
import edu.matc.persistence.GenericDao;
import edu.matc.util.*;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;


/**
 * This class' purpose is to view the results of a race
 */
@WebServlet(

        name = "/viewRaceResult",
        urlPatterns = { "/viewRaceResult" }
)
public class ViewResultDisplay extends HttpServlet implements UseLogger {

    /**
     * This method's purpose is to view the results of a race
     *@param  req               the request object that we forward
     *@param  resp            the response object that we forward
     *@exception ServletException  if an error occurs with the Servlet
     *@exception IOException       if an error occurs with the IO operations
     */
    public void doGet(HttpServletRequest req,
                      HttpServletResponse resp)
            throws ServletException, IOException {


        Race race = new GetEntry<Race>().parseEntry(new GenericDao<>(Race.class), req, log());
        List<TeamRace> teamRaces = new GenericDao<>(TeamRace.class).findByPropertyEqual("race_id", race.getId());

        //using lambda expression to sort by the total time
        teamRaces.sort(Comparator.comparingDouble(TeamRace::getTotalTime));

        new ForwardEntry<>("/viewRaceResult.jsp", req, resp, null, teamRaces);

    }
}