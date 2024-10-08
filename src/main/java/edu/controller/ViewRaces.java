
package edu.controller;

import edu.matc.entity.Race;
import edu.matc.persistence.GenericDao;
import edu.matc.util.DeleteAllRaceResultDisplay;
import edu.matc.util.ForwardEntry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This class' purpose is to view all the races
 */
@WebServlet(
        urlPatterns = {"/viewRaces"}
)
public class ViewRaces extends HttpServlet {

    /**
     * This method's purpose is to view the races
     * @param req the request object
     * @param resp the response object
     * @throws ServletException the servlet exception object
     * @throws IOException the io exception object
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        GenericDao<Race> raceDao = new GenericDao<>(Race.class);

        //Forwards to the page
        new ForwardEntry<>("/viewRaces.jsp", req, resp, new DeleteAllRaceResultDisplay().showDeletionOfResults(raceDao), raceDao.getAll());
    }
}
