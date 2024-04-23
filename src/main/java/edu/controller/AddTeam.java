package edu.controller;

import edu.matc.entity.Category;
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

/**
 * This class' purpose is to add the team to our table
 */
@WebServlet(
        urlPatterns = {"/addTeam"}
)
public class AddTeam extends HttpServlet {

    /**
     * This method's purpose is to add the team to our table
     * @param req the request object
     * @param resp the response object
     * @throws ServletException the servlet exception object
     * @throws IOException the io exception object
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        GenericDao teamDao = new GenericDao(Teams.class);
        GenericDao categoryDao = new GenericDao(Category.class);

        String name = req.getParameter("name");

        int categoryId = Integer.parseInt(req.getParameter("id"));
        Category category = (Category)categoryDao.getById(categoryId);

        Teams team = new Teams(name, category, category.getDivision());

        teamDao.insert(team);

        req.setAttribute("team", team);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/addTeamResult.jsp");
        dispatcher.forward(req, resp);
    }
}
