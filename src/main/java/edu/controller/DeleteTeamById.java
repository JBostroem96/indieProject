package edu.controller;

import edu.matc.entity.Team;
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
        GenericDao<Team> dao = new GenericDao<>(Team.class);
        Team team = (Team) new GetEntry().parseEntry(dao, req, logger);
        try {

            dao.delete(team);
            req.setAttribute("deletedEntry", team);
        } catch (Exception e) {
            req.setAttribute("e", e);
            logger.error("Something went wrong!", e);
        }

        //Update the results after deletion


        RequestDispatcher dispatcher = req.getRequestDispatcher("/deleteTeam.jsp");
        dispatcher.forward(req, resp);
    }
}
