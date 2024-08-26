package edu.controller;
import edu.matc.entity.Race;
import edu.matc.persistence.GenericDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

/**
 * This Servlet's purpose is to add the entered user
 * @author Jimmy Bostroem
 */
@WebServlet(
        urlPatterns = {"/addRace"}
)

/**
 * This class' purpose is to add a race to the table and
 * display the result for the user
 */
public class AddRace extends HttpServlet {

    /**
     * This method's purpose is to add the race to the table
     * @param req the request object
     * @param resp the response object
     * @throws ServletException the servlet exception object
     * @throws IOException the io exception object
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        final Logger logger = LogManager.getLogger(this.getClass());
        GenericDao<Race> dao = new GenericDao<>(Race.class);

        try {

            if (req.getParameter("name") != null
                    && !req.getParameter("name").isEmpty()
                    && req.getParameter("length") != null
                    && !req.getParameter("length").isEmpty()
                    && req.getParameter("date") != null
                    && !req.getParameter("date").isEmpty()) {


                if (new Validate().validateRace(req.getParameter("name"), dao)) {

                    String message = "That race already exists";
                    req.setAttribute("message", message);

                } else {

                    Race race = new Race(req.getParameter("name"),
                            req.getParameter("length"),
                            LocalDate.parse(req.getParameter("date")));

                    dao.insert(race);

                    req.setAttribute("race", race);
                }

            } else {

                req.setAttribute("missingField", "Fields can't be empty");
            }

        } catch (Exception e) {

            logger.error("There was an issue inserting the data", e);
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("/addRace.jsp");
        dispatcher.forward(req, resp);

    }
}
