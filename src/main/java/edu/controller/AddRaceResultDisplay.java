
package edu.controller;

import edu.matc.entity.Race;
import edu.matc.persistence.GenericDao;
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
        urlPatterns = {"/addRaceResultDisplay"}
)
public class AddRaceResultDisplay extends HttpServlet {

    /**
     * This method's purpose is to forward to the jsp
     * @param req the request object
     * @param resp the response object
     * @throws ServletException the servlet exception object
     * @throws IOException the io exception object
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        GenericDao raceDao = new GenericDao(Race.class);

        req.setAttribute("races", raceDao.getAll());

        RequestDispatcher dispatcher = req.getRequestDispatcher("/addRaceResult.jsp");
        dispatcher.forward(req, resp);
    }
}
