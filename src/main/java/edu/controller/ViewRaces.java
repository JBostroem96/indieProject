
package edu.controller;

import edu.matc.entity.Race;
import edu.matc.entity.TeamRace;
import edu.matc.persistence.GenericDao;
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
        List<Race> races = raceDao.getAll();

        Map<Integer, Boolean> raceHasResults = new HashMap<>();

        //Add the id if the race has any results
        for (Race race : races) {
            raceHasResults.put(race.getId(), !race.getTeamRaces().isEmpty());
        }

        req.setAttribute("races", raceDao.getAll());
        req.setAttribute("results", raceHasResults);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/viewRaces.jsp");
        dispatcher.forward(req, resp);
    }
}
