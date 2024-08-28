package edu.controller;
import edu.matc.entity.Race;
import edu.matc.persistence.GenericDao;
import edu.matc.util.UseLogger;
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
public class AddRace extends HttpServlet implements UseLogger {

    /**
     * This method's purpose is to add the race to the table
     * @param req the request object
     * @param resp the response object
     * @throws ServletException the servlet exception object
     * @throws IOException the io exception object
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        final Logger logger = log();
        GenericDao<Race> dao = new GenericDao<>(Race.class);

        String name = req.getParameter("name");
        String length = req.getParameter("length");
        String date = req.getParameter("date");

        if (name != null && !name.isEmpty()
                && length != null && !length.isEmpty()
                && date != null && !date.isEmpty()) {

            try {

                if (new Validate().validateRace(name, dao)) {

                    String message = "That race already exists";
                    req.setAttribute("message", message);

                } else {

                    Race race = new Race(name,
                            length,
                            LocalDate.parse(date));

                    dao.insert(race);

                    req.setAttribute("race", race);
                }

            } catch (Exception e) {

                req.setAttribute("e", "Something went wrong!");
                logger.error("There was an issue inserting the data", e);
            }

        } else {

            req.setAttribute("missingField", "Fields can't be empty");
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("/addRace.jsp");
        dispatcher.forward(req, resp);
    }
}
