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
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


/**
 * This class' purpose is to edit a race and
 * display the result for the user
 */
@WebServlet(
        urlPatterns = {"/editRaceById"}
)

public class EditRaceById extends HttpServlet {

    /**
     * This method's purpose is to edit the entry by id
     *@param  req               the request object that we forward
     *@param  resp           the response object that we forward
     *@exception ServletException  if an error occurs with the Servlet
     *@exception IOException       if an error occurs with the IO operations
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        final Logger logger = LogManager.getLogger(this.getClass());

        GenericDao<Race> dao = new GenericDao<>(Race.class);

        String name = req.getParameter("name");
        String length = req.getParameter("length");
        String date = req.getParameter("date");
        String id = req.getParameter("id");
        Race raceToUpdate = null;

        if (id != null && !id.isEmpty()) {

            try {

                raceToUpdate = dao.getById(Integer.parseInt(id));

                if (name != null && !name.isEmpty()
                        && length != null && !length.isEmpty()
                        && date != null && !date.isEmpty()) {

                    Race updatedRace = new Race(name,
                            length,
                            LocalDate.parse(date));

                    if (new Validate().validateRace(updatedRace.getName(), dao)) {

                        String message = "That race already exists";
                        req.setAttribute("message", message);

                    } else {

                        raceToUpdate.setName(updatedRace.getName());
                        raceToUpdate.setLength(updatedRace.getLength());
                        raceToUpdate.setDate(updatedRace.getDate());
                        dao.update(raceToUpdate);

                        String message = "You have successfully updated the race!";
                        req.setAttribute("messageSuccess", message);
                    }

                } else {

                    req.setAttribute("missingField", "Fields can't be empty");
                }

            } catch (Exception e) {

                logger.error("There was an issue updating the data", e);
                req.setAttribute("e", "Something went wrong!");
            }
        }

        req.setAttribute("race", raceToUpdate);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/editRace.jsp");
        dispatcher.forward(req, resp);
    }
}