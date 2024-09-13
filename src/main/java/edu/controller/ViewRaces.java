
package edu.controller;

import edu.matc.entity.Race;
import edu.matc.entity.TeamRace;
import edu.matc.persistence.GenericDao;
import edu.matc.util.DeleteAllRaceResultDisplay;
import edu.matc.util.Forward;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class' purpose is to forward to the jsp
 */
@WebServlet(
        urlPatterns = {"/viewRaces"}
)
public class ViewRaces extends HttpServlet {

    /**
     * This method's purpose is to forward to the jsp
     * @param req the request object
     * @param resp the response object
     * @throws ServletException the servlet exception object
     * @throws IOException the io exception object
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        GenericDao<Race> raceDao = new GenericDao<>(Race.class);

        new Forward<>("/viewRaces.jsp", req, resp, new DeleteAllRaceResultDisplay().showDeletionOfResults(raceDao), raceDao.getAll());
    }
}
