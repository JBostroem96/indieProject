package edu.controller;

import edu.matc.entity.Race;
import edu.matc.entity.Role;
import edu.matc.persistence.GenericDao;
import edu.matc.util.Authorization;
import edu.matc.util.Forward;
import edu.matc.util.GetEntry;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;


/**
 * This class' purpose is to edit a race and
 * display the result for the user
 */
@WebServlet(
        urlPatterns = {"/editRace"}
)

public class EditRace extends HttpServlet implements Authorization {

    /**
     * This method's purpose is to edit the entry by id
     *@param  req               the request object that we forward
     *@param  resp           the response object that we forward
     *@exception ServletException  if an error occurs with the Servlet
     *@exception IOException       if an error occurs with the IO operations
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (!authorize(resp, req, Role.ADMIN, null)) {
            return;
        }

        final Logger logger = log();

        GenericDao<Race> dao = new GenericDao<>(Race.class);

        String name = req.getParameter("name");
        String length = req.getParameter("length");
        String date = req.getParameter("date");
        Race raceToUpdate = new GetEntry<Race>().parseEntry(new GenericDao<>(Race.class), req, logger);

        try {

            if (name != null && !name.isEmpty()
                    && length != null && !length.isEmpty()
                    && date != null && !date.isEmpty()) {


                    Race updatedRace = new Race(name,
                            length,
                            LocalDate.parse(date));

                    if (new Validate<Race>().validate(updatedRace.getName(), dao, req)) {

                        raceToUpdate.setName(updatedRace.getName());
                        raceToUpdate.setLength(updatedRace.getLength());
                        raceToUpdate.setDate(updatedRace.getDate());
                        dao.update(raceToUpdate);
                        req.setAttribute("raceUpdated", "You successfully updated the race");
                    }

            } else {

                req.setAttribute("missingField", "Fields can't be empty");
            }

        } catch (Exception e) {

            logger.error("There was an issue updating the data", e);
            req.setAttribute("e", "Something went wrong!");
        }

        new Forward<>("/editRace.jsp", req, resp, raceToUpdate, null);
    }
}
