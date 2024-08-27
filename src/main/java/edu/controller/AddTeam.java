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

        final Logger logger = LogManager.getLogger(this.getClass());

        GenericDao<Team> teamDao = new GenericDao<>(Team.class);
        GenericDao<Category> categoryDao = new GenericDao<>(Category.class);

        String name = req.getParameter("name");
        String id = req.getParameter("id");

        if (name != null && !name.isEmpty()
                && id != null && !id.isEmpty()) {

            try {

                Category category = categoryDao.getById(Integer.parseInt(id));

                if (new Validate().validateTeam(name, teamDao)) {

                    String message = "That team already exists. Please Enter something else.";
                    req.setAttribute("message", message);

                } else {

                    Team team = new Team(name, category, category.getDivision().toString());
                    teamDao.insert(team);
                    req.setAttribute("team", team);
                }

            } catch (Exception e) {

                req.setAttribute("e", "Something went wrong!");
                logger.error("Something went wrong!", e);
            }

        } else {

            req.setAttribute("missingField", "Fields can't be empty");
        }

        req.setAttribute("category", categoryDao.getAll());
        RequestDispatcher dispatcher = req.getRequestDispatcher("/addTeam.jsp");
        dispatcher.forward(req, resp);
    }
}



