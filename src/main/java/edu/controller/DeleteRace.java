package edu.controller;

import edu.matc.entity.Race;
import edu.matc.entity.Role;
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

/**
 * This class' purpose is to delete the entry by id
 */
@WebServlet(
        urlPatterns = {"/deleteRace"}
)
public class DeleteRace extends HttpServlet implements Authorization {

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
        GenericDao<Race> dao = new GenericDao<>(Race.class);
        Race race = new GetEntry<Race>().parseEntry(dao, req, logger);

        try {

            dao.delete(race);
            req.setAttribute("entryDeleted", "You deleted the entry");

        } catch (Exception e) {
            req.setAttribute("e", "Something went wrong!");
            logger.error("Something went wrong!", e);
        }

        req.setAttribute("races", dao.getAll());
        new Forward<>("/searchResults.jsp", req, resp, null, null);
    }
}
