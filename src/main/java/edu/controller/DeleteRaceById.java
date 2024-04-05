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

@WebServlet(
        urlPatterns = {"/deleteRaceById"}
)
public class DeleteRaceById extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        GenericDao dao = new GenericDao(Race.class);

        int raceId = Integer.parseInt(req.getParameter("id"));
        Race raceToDelete = (Race)dao.getById(raceId);

        dao.delete(raceToDelete);

        req.setAttribute("race", raceToDelete);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/deleteRaceResult.jsp");
        dispatcher.forward(req, resp);
    }
}
