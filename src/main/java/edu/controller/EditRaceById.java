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
import java.time.LocalDate;


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
        Race updatedRace = new Race(req.getParameter("name"),
                req.getParameter("length"),
                LocalDate.parse(req.getParameter(("date"))));

       int raceId = Integer.parseInt(req.getParameter("id"));
       Race raceToUpdate = (Race)dao.getById(raceId);

       if (raceToUpdate.getName().equals(updatedRace.getName())
               && raceToUpdate.getLength().equals(updatedRace.getLength())
               && raceToUpdate.getDate().equals(updatedRace.getDate())) {

           String message = "The edit and current values are the same; please use different values.";
           req.setAttribute("message", message);
           req.setAttribute("race", raceToUpdate);

       } else {

           raceToUpdate.setName(updatedRace.getName());
           raceToUpdate.setDate(updatedRace.getDate());
           raceToUpdate.setLength(updatedRace.getLength());

           dao.update(raceToUpdate);

           req.setAttribute("editedRace", raceToUpdate);
       }


        RequestDispatcher dispatcher = req.getRequestDispatcher("/editRace.jsp");
        dispatcher.forward(req, resp);
    }
}