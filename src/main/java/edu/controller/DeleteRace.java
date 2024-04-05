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

@WebServlet(
        urlPatterns = {"/deleteRace"}
)

public class DeleteRace extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        GenericDao dao = new GenericDao(Race.class);
        int raceId = Integer.parseInt(req.getParameter("id"));
        Race retrievedRace = (Race)dao.getById(raceId);
        req.setAttribute("race", retrievedRace);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/deleteRace.jsp");
        dispatcher.forward(req, resp);
    }
}
