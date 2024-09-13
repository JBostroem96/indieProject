package edu.controller;


import edu.matc.entity.Role;
import edu.matc.entity.TeamRace;
import edu.matc.persistence.GenericDao;
import edu.matc.util.Authorization;
import edu.matc.util.Forward;
import edu.matc.util.GetEntry;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

/**
 * This class' purpose is to delete a race result
 */
@WebServlet(
        urlPatterns = {"/deleteRaceResult"}
)
public class DeleteRaceResult extends HttpServlet implements Authorization {

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
        GenericDao<TeamRace> dao = new GenericDao<>(TeamRace.class);
        TeamRace teamRace = new GetEntry<TeamRace>().parseEntry(dao, req, logger);

        try {
            dao.delete(teamRace);
            req.setAttribute("entryDeleted", "You deleted the entry");
            //Update the results after deletion
            new UpdateResults(teamRace.getRace_id(), dao);

        } catch (Exception e) {
            req.setAttribute("e", "Something went wrong!");
            logger.error("Something went wrong!", e);
        }

        List<TeamRace> teamRaces = new GenericDao<>(TeamRace.class).findByPropertyEqual("race_id", teamRace.getRace_id());
        //using lambda expression to sort by the total time
        teamRaces.sort(Comparator.comparingDouble(TeamRace::getTotalTime));

        new Forward<>("/viewRaceResult.jsp", req, resp, null, teamRaces);
    }
}

