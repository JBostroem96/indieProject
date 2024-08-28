package edu.controller;


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

/**
 * This class' purpose is to forward to the jsp
 */
@WebServlet(
        urlPatterns = {"/deleteRaceResultDisplay"}
)
public class DeleteRaceResultDisplay extends HttpServlet implements UseLogger {

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
        GenericDao<TeamRace> dao = new GenericDao<>(TeamRace.class);
        String id = req.getParameter("id");

        if (id != null && !id.isEmpty()) {

            try {

                TeamRace retrievedTeamRace = dao.getById(Integer.parseInt(id));
                req.setAttribute("team_race", retrievedTeamRace);

            } catch (Exception e) {

                logger.error("Something went wrong!", e);
            }
            RequestDispatcher dispatcher = req.getRequestDispatcher("/deleteRaceResult.jsp");
            dispatcher.forward(req, resp);
        }
    }
}
