package edu.controller;

import edu.matc.entity.Race;
import edu.matc.entity.Teams;
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
        urlPatterns = {"/addTeam"}
)
public class AddTeam extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        GenericDao dao = new GenericDao(Teams.class);
        Teams team = new Teams(req.getParameter("name"),
                req.getParameter("division"));

        dao.insert(team);

        req.setAttribute("team", team);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/addTeamResult.jsp");
        dispatcher.forward(req, resp);
    }
}
