package edu.controller;

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
import javax.validation.ConstraintViolationException;
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
        GenericDao<Team> dao = new GenericDao<>(Team.class);
        String id = req.getParameter("id");

        if (id != null && !id.isEmpty()) {

            try {

                Team teamToDelete = dao.getById(Integer.parseInt(id));
                dao.delete(teamToDelete);
                req.setAttribute("deletedTeam", teamToDelete);

            } catch (Exception e) {

                logger.error("Something went wrong!", e);
            }
            RequestDispatcher dispatcher = req.getRequestDispatcher("/deleteTeam.jsp");
            dispatcher.forward(req, resp);
        }
    }
}