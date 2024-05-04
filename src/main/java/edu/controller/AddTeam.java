package edu.controller;

import edu.matc.entity.Category;
import edu.matc.entity.Team;
import edu.matc.entity.TeamRace;
import edu.matc.entity.User;
import edu.matc.persistence.GenericDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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

        GenericDao teamDao = new GenericDao(Team.class);
        GenericDao categoryDao = new GenericDao(Category.class);
        Validate validate = new Validate();
        String name = req.getParameter("name");

        if (validate.validateName().contains(name)) {

            String message = "That team already exists. Please Enter something else.";
            req.setAttribute("message", message);

        } else {

            Category category = (Category)categoryDao.getById(Integer.parseInt(req.getParameter("id")));
            Team team = new Team(name, category, category.getDivision());
            teamDao.insert(team);
            req.setAttribute("team", team);
        }

        req.setAttribute("category", categoryDao.getAll());
        RequestDispatcher dispatcher = req.getRequestDispatcher("/addTeam.jsp");
        dispatcher.forward(req, resp);
    }
}



