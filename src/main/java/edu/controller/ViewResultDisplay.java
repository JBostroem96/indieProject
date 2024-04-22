package edu.controller;

import edu.matc.entity.Race;
import edu.matc.entity.TeamRaces;
import edu.matc.entity.Teams;
import edu.matc.persistence.GenericDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(

        name = "/viewRaceResult",
        urlPatterns = { "/viewRaceResult" }
)
public class ViewResultDisplay extends HttpServlet {

    /**
     * This method's purpose is to forward to the searchRace JSP
     *@param  req               the request object that we forward
     *@param  resp            the response object that we forward
     *@exception ServletException  if an error occurs with the Servlet
     *@exception IOException       if an error occurs with the IO operations
     */
    public void doGet(HttpServletRequest req,
                      HttpServletResponse resp)
            throws ServletException, IOException {

        GenericDao raceDao = new GenericDao(Race.class);
        GenericDao teamRaces = new GenericDao(TeamRaces.class);
        //we probably need the team name, the cp, and the late_penalty

        int raceId = Integer.parseInt(req.getParameter("id"));
        Race race = (Race)raceDao.getById(raceId);

        TeamRaces record = (TeamRaces)teamRaces.getById(raceId);

        req.setAttribute("team_race", record);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/viewRaceResult.jsp");
        dispatcher.forward(req, resp);
    }
}

