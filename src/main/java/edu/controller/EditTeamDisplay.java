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
 * This class' purpose is to forward to the jsp
 */
@WebServlet(
        urlPatterns = {"/editTeam"}
)

public class EditTeamDisplay extends HttpServlet {

    /**
     * This method's purpose is to forward to the jsp
     *@param  req               the request object that we forward
     *@param  resp           the response object that we forward
     *@exception ServletException  if an error occurs with the Servlet
     *@exception IOException       if an error occurs with the IO operations
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        final Logger logger = LogManager.getLogger(this.getClass());
        GenericDao<Team> dao = new GenericDao<>(Team.class);
        GenericDao<Category> categoryDao = new GenericDao<>(Category.class);
        String id = req.getParameter("id");

        if (id != null && !id.isEmpty()) {

            try {

               Team retrievedTeam = dao.getById(Integer.parseInt(id));

                req.setAttribute("team", retrievedTeam);
                req.setAttribute("category", categoryDao.getAll());

                RequestDispatcher dispatcher = req.getRequestDispatcher("/editTeam.jsp");
                dispatcher.forward(req, resp);

            } catch (Exception e) {

                logger.error("Something went wrong!", e);
            }
        }
    }
}
