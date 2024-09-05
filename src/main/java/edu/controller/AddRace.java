package edu.controller;
import edu.matc.entity.Race;
import edu.matc.entity.Role;
import edu.matc.persistence.GenericDao;
import edu.matc.util.Authorization;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.time.LocalDate;

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
public class AddRace extends HttpServlet implements Authorization {

    /**
     * This method's purpose is to add the race to the table
     * @param req the request object
     * @param resp the response object
     * @throws ServletException the servlet exception object
     * @throws IOException the io exception object
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (!authorize(resp, req, Role.admin, null)) {
            return;
        }

        GenericDao<Race> dao = new GenericDao<>(Race.class);

        String name = req.getParameter("name");
        String length = req.getParameter("length");
        String date = req.getParameter("date");

        if (name != null && !name.isEmpty()
                && length != null && !length.isEmpty()
                && date != null && !date.isEmpty()) {

            try {

                if (new Validate<Race>().validate(name, dao, req)) {

                    Race race = new Race(name,
                            length,
                            LocalDate.parse(date));

                    dao.insert(race);

                    req.setAttribute("raceAdded", "You successfully added the race!");
                }

            } catch (Exception e) {

                req.setAttribute("e", "Something went wrong!");
                log().error("There was an issue inserting the data", e);
            }

        } else {

            req.setAttribute("missingField", "Fields can't be empty");
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("/addRace.jsp");
        dispatcher.forward(req, resp);
    }
}
