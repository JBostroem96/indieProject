package edu.controller;

import edu.matc.entity.Category;
import edu.matc.entity.Team;
import edu.matc.persistence.GenericDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * This class' purpose is to edit a team
 */
@WebServlet(
        urlPatterns = {"/editTeamById"}
)

public class EditTeamById extends HttpServlet {

    /**
     * This method's purpose is to edit the entry by id
     *@param  req               the request object that we forward
     *@param  resp           the response object that we forward
     *@exception ServletException  if an error occurs with the Servlet
     *@exception IOException       if an error occurs with the IO operations
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        GenericDao dao = new GenericDao(Team.class);
        GenericDao categoryDao = new GenericDao(Category.class);

        Validate validate = new Validate();

        Team updatedTeam = new Team(req.getParameter("name"),
                req.getParameter("division"));

        Team teamToUpdate = (Team)dao.getById(Integer.parseInt(req.getParameter("id")));

        if (validate.validateEditTeam(teamToUpdate.getName(), dao).contains(updatedTeam.getName())) {

            String message = "That name already exists";
            req.setAttribute("alreadyExists", message);
            req.setAttribute("team", teamToUpdate);

        } else {

            teamToUpdate.setName(updatedTeam.getName());
            teamToUpdate.setDivision(updatedTeam.getDivision());

            dao.update(teamToUpdate);

            req.setAttribute("editedTeam", teamToUpdate);
        }

        req.setAttribute("category", categoryDao.getAll());

        RequestDispatcher dispatcher = req.getRequestDispatcher("/editTeam.jsp");
        dispatcher.forward(req, resp);
    }
}
