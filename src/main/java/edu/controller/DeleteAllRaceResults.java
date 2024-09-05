package edu.controller;

import edu.matc.entity.Race;
import edu.matc.entity.Role;
import edu.matc.entity.TeamRace;
import edu.matc.persistence.GenericDao;
import edu.matc.util.Authorization;
import edu.matc.util.GetEntry;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;


@WebServlet(
        urlPatterns = {"/deleteAllRaceResults"}
)

public class DeleteAllRaceResults extends HttpServlet implements Authorization {

    /**
     * This method's purpose is to delete the entry by id
     *@param  req               the request object that we forward
     *@param  resp           the response object that we forward
     *@exception ServletException  if an error occurs with the Servlet
     *@exception IOException       if an error occurs with the IO operations
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (!authorize(resp, req, Role.ADMIN, null)) {
            return;
        }

        final Logger logger = log();
        Race race = new GetEntry<Race>().parseEntry(new GenericDao<>(Race.class), req, logger);
        
        try {
            //Deletes the results
            deleteResults(race.getTeamRaces());
            req.setAttribute("deletedEntry", race);
        } catch (Exception e) {
            req.setAttribute("e", e);
            logger.error("Something went wrong!", e);
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("/deleteAllRaceResults.jsp");
        dispatcher.forward(req, resp);
    }

    /**
     * This method's purpose is to delete the results in a race
     * @param results the results to be deleted
     */
    private void deleteResults(Set<TeamRace> results) {

        GenericDao<TeamRace> dao = new GenericDao<>(TeamRace.class);

        for (TeamRace entry : results) {

            dao.delete(entry);
        }
    }
}
