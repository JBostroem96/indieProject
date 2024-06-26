
package edu.controller;


import edu.matc.entity.Race;
import edu.matc.entity.Team;
import edu.matc.persistence.GenericDao;
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
public class AddRaceResult extends HttpServlet {

    /**
     * This method's purpose is to add the race result
     * @param req the request object
     * @param resp the response object
     * @throws ServletException the servlet exception object
     * @throws IOException the io exception object
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        GenericDao teamDao = new GenericDao(Team.class);

        GenericDao dao = new GenericDao(Race.class);
        int raceId = Integer.parseInt(req.getParameter("id"));
        Race retrievedRace = (Race)dao.getById(raceId);

        req.setAttribute("race", retrievedRace);
        req.setAttribute("team", teamDao.getAll());

        RequestDispatcher dispatcher = req.getRequestDispatcher("/addRaceResultForm.jsp");
        dispatcher.forward(req, resp);
    }
}
