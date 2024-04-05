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
import java.util.ArrayList;
import java.util.List;

@WebServlet(
        urlPatterns = {"/editRaceById"}
)

/**
 * This class' purpose is to edit a race and
 * display the result for the user
 */
public class EditRaceById extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        GenericDao dao = new GenericDao(Race.class);
        Race updatedRace = new Race(req.getParameter("name"),
                req.getParameter("length"),
                LocalDate.parse(req.getParameter(("date"))));
       String string = req.getParameter("id");
       int race = Integer.parseInt(string);

        Race raceToUpdate = (Race)dao.getById(race);;


        raceToUpdate.setName(updatedRace.getName());
        raceToUpdate.setDate(updatedRace.getDate());
        raceToUpdate.setLength(updatedRace.getLength());
        dao.update(raceToUpdate);

        req.setAttribute("race", raceToUpdate);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/editRaceResults.jsp");
        dispatcher.forward(req, resp);
    }
}