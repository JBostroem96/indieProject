package edu.controller;

import edu.matc.entity.User;
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
        urlPatterns = {"/editUserRole"}
)
public class EditUserRoleDisplay extends HttpServlet {

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
        GenericDao<User> dao = new GenericDao<>(User.class);
        String id = req.getParameter("id");

        if (id != null && !id.isEmpty()) {

            try {

               User retrievedUser = dao.getById(Integer.parseInt(req.getParameter("id")));

                req.setAttribute("userRole", retrievedUser);

                RequestDispatcher dispatcher = req.getRequestDispatcher("/users.jsp");
                dispatcher.forward(req, resp);

            } catch (Exception e) {

                logger.error("Something went wrong!", e);
            }
        }
    }
}
