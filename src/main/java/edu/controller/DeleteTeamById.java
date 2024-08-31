package edu.controller;

import edu.matc.entity.Team;
import edu.matc.entity.TeamRace;
import edu.matc.persistence.GenericDao;
import edu.matc.util.UseLogger;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This class' purpose is to delete a team
 */
@WebServlet(
        urlPatterns = {"/deleteTeamById"}
)
public class DeleteTeamById extends HttpServlet implements UseLogger {

    /**
     * This method's purpose is to delete the entry by id
     *@param  req               the request object that we forward
     *@param  resp           the response object that we forward
     *@exception ServletException  if an error occurs with the Servlet
     *@exception IOException       if an error occurs with the IO operations
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        final Logger logger = log();
        GenericDao<TeamRace> teamRaceDao = new GenericDao<>(TeamRace.class);
        GenericDao<Team> dao = new GenericDao<>(Team.class);
        Team team = new GetEntry<Team>().parseEntry(dao, req, logger);

        try {

            dao.delete(team);
            req.setAttribute("deletedEntry", team);
            //Update the results after deletion
            updateRaces(teamRaceDao, req);

        } catch (Exception e) {
            req.setAttribute("e", e);
            logger.error("Something went wrong!", e);
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("/deleteTeam.jsp");
        dispatcher.forward(req, resp);
    }

    /**
     * This method's purpose is to update the races/results
     * @param dao the teamRace dao
     * @param req the request object
     */
    public void updateRaces(GenericDao<TeamRace> dao, HttpServletRequest req) {

        for (String race : req.getParameterValues("race_id")) {

            new UpdateResults(race, dao, req);
        }
    }
}
