package edu.controller;

import edu.matc.entity.Race;
import edu.matc.entity.Team;
import edu.matc.persistence.GenericDao;
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

@WebServlet(
        urlPatterns = {"/deleteTeamById"}
)
public class DeleteTeamById extends HttpServlet {

    /**
     * This method's purpose is to delete the entry by id
     *@param  req               the request object that we forward
     *@param  resp           the response object that we forward
     *@exception ServletException  if an error occurs with the Servlet
     *@exception IOException       if an error occurs with the IO operations
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        GenericDao dao = new GenericDao(Team.class);
        final Logger logger = LogManager.getLogger(this.getClass());

        int teamId = Integer.parseInt(req.getParameter("id"));
        Team teamToDelete = (Team) dao.getById(teamId);

        try {
            dao.delete(teamToDelete);

        } catch (ConstraintViolationException e) {

            logger.error("This can't be deleted because it's in results", e);

        } catch (Error er) {

            logger.error("There was an issue", er);
        }

        req.setAttribute("deletedTeam", teamToDelete);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/deleteTeam.jsp");
        dispatcher.forward(req, resp);
    }
}