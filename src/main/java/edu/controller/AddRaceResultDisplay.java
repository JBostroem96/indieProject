
package edu.controller;


import edu.matc.entity.Race;
import edu.matc.entity.Role;
import edu.matc.entity.Team;
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

/**
 * This class' purpose is to add the race result by sending the necessary data to the form
 */
@WebServlet(
        urlPatterns = {"/addRaceResultDisplay"}
)
public class AddRaceResultDisplay extends HttpServlet implements Authorization {

    /**
     * This method's purpose is to add the race result
     * @param req the request object
     * @param resp the response object
     * @throws ServletException the servlet exception object
     * @throws IOException the io exception object
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (!authorize(resp, req, Role.ADMIN, null)) {
            return;
        }

        final Logger logger = log();
        GenericDao<Team> teamDao = new GenericDao<>(Team.class);
        Race retrievedRace = new GetEntry<Race>().parseEntry(new GenericDao<>(Race.class), req, logger);

        req.setAttribute("race", retrievedRace);
        req.setAttribute("team", teamDao.getAll());

        RequestDispatcher dispatcher = req.getRequestDispatcher("/addRaceResultForm.jsp");
        dispatcher.forward(req, resp);
    }
}
