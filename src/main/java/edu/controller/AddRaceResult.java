
package edu.controller;


import edu.matc.entity.Race;
import edu.matc.entity.Team;
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

/**
 * This class' purpose is to add the race result by sending the necessary data to the form
 */
@WebServlet(
        urlPatterns = {"/addRaceResult"}
)
public class AddRaceResult extends HttpServlet implements UseLogger {

    /**
     * This method's purpose is to add the race result
     * @param req the request object
     * @param resp the response object
     * @throws ServletException the servlet exception object
     * @throws IOException the io exception object
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        final Logger logger = log();
        GenericDao<Team> teamDao = new GenericDao<>(Team.class);
        GenericDao<Race> dao = new GenericDao<>(Race.class);
        String id = req.getParameter("id");

        if (id != null && !id.isEmpty()) {

            try {

                Race retrievedRace = dao.getById(Integer.parseInt(id));
                req.setAttribute("race", retrievedRace);
                req.setAttribute("team", teamDao.getAll());

            } catch (Exception e) {

                logger.error("Something went wrong!", e);
            }
            RequestDispatcher dispatcher = req.getRequestDispatcher("/addRaceResultForm.jsp");
            dispatcher.forward(req, resp);
        }
    }
}
