package edu.controller;

import edu.matc.entity.Race;
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

/**
 * This class' purpose is to delete the entry by id
 */
@WebServlet(
        urlPatterns = {"/deleteRaceById"}
)
public class DeleteRaceById extends HttpServlet {

    /**
     * This method's purpose is to delete the entry by id
     *@param  req               the request object that we forward
     *@param  resp           the response object that we forward
     *@exception ServletException  if an error occurs with the Servlet
     *@exception IOException       if an error occurs with the IO operations
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        final Logger logger = LogManager.getLogger(this.getClass());
        GenericDao dao = new GenericDao(Race.class);

        Race raceToDelete = (Race)dao.getById(Integer.parseInt(req.getParameter("id")));

        try {
            dao.delete(raceToDelete);
        } catch (Exception e) {
            logger.error("There was an issue deleting the data", e);
        }

        req.setAttribute("deletedRace", raceToDelete);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/deleteRace.jsp");
        dispatcher.forward(req, resp);
    }
}
