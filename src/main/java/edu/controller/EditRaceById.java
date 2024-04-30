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

        GenericDao dao = new GenericDao(Race.class);

        List<String> existingNames = new ArrayList<>();
        List<Race> races = dao.getAll();

        Race updatedRace = new Race(req.getParameter("name"),
                req.getParameter("length"),
                LocalDate.parse(req.getParameter(("date"))));

        int raceId = Integer.parseInt(req.getParameter("id"));
        Race raceToUpdate = (Race)dao.getById(raceId);

        for (Race race : races) {

            existingNames.add(race.getName());
        }

        if (existingNames.contains(updatedRace.getName())) {

            String message = "That name already exists";
            req.setAttribute("message", message);
            req.setAttribute("race", raceToUpdate);

        } else {

            raceToUpdate.setName(updatedRace.getName());
            raceToUpdate.setLength(updatedRace.getLength());
            raceToUpdate.setDate(updatedRace.getDate());

            dao.update(raceToUpdate);

            req.setAttribute("editedRace", raceToUpdate);
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("/editRace.jsp");
        dispatcher.forward(req, resp);
    }
}