package edu.controller;

import edu.matc.entity.Category;
import edu.matc.entity.Team;
import edu.matc.persistence.GenericDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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

        final Logger logger = LogManager.getLogger(this.getClass());
        GenericDao<Team> dao = new GenericDao<>(Team.class);
        GenericDao<Category> categoryDao = new GenericDao<>(Category.class);

        String name = req.getParameter("name");
        String division = req.getParameter("division");
        String id = req.getParameter("id");
        Team teamToUpdate = null;

        if (id != null && !id.isEmpty()) {

            try {

                teamToUpdate = dao.getById(Integer.parseInt(id));

                if (name != null && !name.isEmpty()
                        && division != null && !division.isEmpty()) {

                    Team updatedTeam = new Team(name,
                            division);

                    if (new Validate().validateTeam(updatedTeam.getName(), dao)) {

                        String message = "That name already exists";
                        req.setAttribute("alreadyExists", message);

                    } else {

                        teamToUpdate.setName(updatedTeam.getName());
                        teamToUpdate.setDivision(updatedTeam.getDivision());
                        dao.update(teamToUpdate);
                        req.setAttribute("messageSuccess", "You have successfully updated the team!");
                    }

                } else {

                    req.setAttribute("missingField", "Fields can't be empty");
                }

            } catch (Exception e) {

                logger.error("There was an issue updating the data");
                req.setAttribute("e", "Something went wrong!");
            }
        }
        
        req.setAttribute("team", teamToUpdate);
        req.setAttribute("category", categoryDao.getAll());

        RequestDispatcher dispatcher = req.getRequestDispatcher("/editTeam.jsp");
        dispatcher.forward(req, resp);
    }
}
