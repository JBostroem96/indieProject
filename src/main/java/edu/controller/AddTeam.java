package edu.controller;

import edu.matc.entity.Category;
import edu.matc.entity.Role;
import edu.matc.entity.Team;
import edu.matc.persistence.GenericDao;
import edu.matc.util.Authorization;
import edu.matc.util.GetEntry;
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
public class AddTeam extends HttpServlet implements Authorization {

    /**
     * This method's purpose is to add the team to our table
     * @param req the request object
     * @param resp the response object
     * @throws ServletException the servlet exception object
     * @throws IOException the io exception object
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (!authorize(resp, req, Role.ADMIN, null)) {
            return;
        }

        final Logger logger = log();

        GenericDao<Team> teamDao = new GenericDao<>(Team.class);
        GenericDao<Category> categoryDao = new GenericDao<>(Category.class);
        String name = req.getParameter("name");
        String category = req.getParameter("category");
        
        if (category != null && !category.isEmpty()
            && name != null && !name.isEmpty()) {

            try {

                Category categoryEntry = new GetEntry<Category>().parseEntry(categoryDao, req, logger);

                if (new Validate<Team>().validate(name, teamDao, req)) {

                    Team team = new Team(name, categoryEntry, categoryEntry.getDivision().toString());
                    teamDao.insert(team);
                    req.setAttribute("teamAdded", "You successfully added the team!");
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



