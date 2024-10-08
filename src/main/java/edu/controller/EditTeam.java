package edu.controller;

import edu.matc.entity.Category;
import edu.matc.entity.Role;
import edu.matc.entity.Team;
import edu.matc.persistence.GenericDao;
import edu.matc.util.Authorization;
import edu.matc.util.ForwardEntry;
import edu.matc.util.GetEntry;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * This class' purpose is to edit the team
 */
@WebServlet(
        urlPatterns = {"/editTeam"}
)

public class EditTeam extends HttpServlet implements Authorization {

    /**
     * This method's purpose is to edit the entry
     *@param  req               the request object that we forward
     *@param  resp           the response object that we forward
     *@exception ServletException  if an error occurs with the Servlet
     *@exception IOException       if an error occurs with the IO operations
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (!authorize(resp, req, Role.ADMIN, null)) {
            return;
        }

        final Logger logger = log();
        GenericDao<Team> dao = new GenericDao<>(Team.class);
        GenericDao<Category> categoryDao = new GenericDao<>(Category.class);

        String name = req.getParameter("name");
        String division = req.getParameter("division");
        Team teamToUpdate = new GetEntry<Team>().parseEntry(new GenericDao<>(Team.class), req, logger);

        if (name != null && !name.isEmpty()
                && division != null && !division.isEmpty()) {

            try {

                Category category = categoryDao.getById(Integer.parseInt(division));
                Team updatedTeam = new Team(name,
                            category, category.getDivision().toString());

                if (new Validate<Team>().validate(updatedTeam.getName(), dao, req)) {

                    teamToUpdate.setName(updatedTeam.getName());
                    teamToUpdate.setDivision(updatedTeam.getDivision());
                    teamToUpdate.setCategory(updatedTeam.getCategory());
                    dao.update(teamToUpdate);
                    req.setAttribute("teamUpdated", "You have successfully updated the team!");
                }

            } catch (Exception e) {

                logger.error("There was an issue updating the data");
                req.setAttribute("e", "Something went wrong!");
            }

        } else {

            req.setAttribute("missingField", "Fields can't be empty");
        }

        //Forwards to the page
        new ForwardEntry<>("/editTeam.jsp", req, resp, teamToUpdate, categoryDao.getAll());
    }
}
