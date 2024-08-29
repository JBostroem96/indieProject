package edu.controller;


import edu.matc.entity.Race;
import edu.matc.entity.Team;
import edu.matc.entity.TeamRace;
import edu.matc.persistence.GenericDao;
import edu.matc.util.UseLogger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class' purpose is to forward to the jsp
 */
@WebServlet(
        urlPatterns = {"/deleteTeam"}
)
public class DeleteTeamDisplay extends HttpServlet implements UseLogger {

    /**
     * This method's purpose is to forward to the jsp
     *@param  req               the request object that we forward
     *@param  resp           the response object that we forward
     *@exception ServletException  if an error occurs with the Servlet
     *@exception IOException       if an error occurs with the IO operations
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        final Logger logger = log();
        GenericDao<TeamRace> teamRace = new GenericDao<>(TeamRace.class);

        Team retrievedTeam = (Team) new GetEntry().parseEntry(new GenericDao<>(Team.class), req, logger);
        List<Integer> races = new ArrayList<>();

        //Gets all the races where the teams are
        getAllRaces(teamRace, retrievedTeam, races);

        req.setAttribute("races", races);
        req.setAttribute("team", retrievedTeam);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/deleteTeam.jsp");
        dispatcher.forward(req, resp);
    }

    /**
     * This method's purpose is to get all the races where the teams are
     * @param teamRace the teamRace dao
     * @param retrievedTeam the retrieved team
     * @param races the races
     */
    public void getAllRaces(GenericDao<TeamRace> teamRace, Team retrievedTeam, List<Integer> races) {

        for (TeamRace entry : teamRace.getAll()) {

            String name = entry.getTeam().getName();

            if (name.equals(retrievedTeam.getName())) {

                races.add(entry.getRace_id());
            }
        }
    }
}

