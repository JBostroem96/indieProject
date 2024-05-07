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
 * This class' purpose is to edit a user role
 */
@WebServlet(
        urlPatterns = {"/editUserRoleById"}
)
public class EditUserRoleById extends HttpServlet {

    /**
     * This method's purpose is to edit entry by id
     *@param  req               the request object that we forward
     *@param  resp           the response object that we forward
     *@exception ServletException  if an error occurs with the Servlet
     *@exception IOException       if an error occurs with the IO operations
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        final Logger logger = LogManager.getLogger(this.getClass());
        GenericDao dao = new GenericDao(User.class);

        User retrievedUser = (User)dao.getById(Integer.parseInt(req.getParameter("id")));
        String role = req.getParameter("role");

        if (retrievedUser.getRole().equals(role)) {

            String message = "That user already has that role";
            req.setAttribute("userRole", retrievedUser);
            req.setAttribute("failure", message);

        } else {

            retrievedUser.setRole(role);

            try {
                dao.update(retrievedUser);
            } catch (Exception e) {
                logger.error("There was an issue updating the data", e);
            }

            String message = "You updated the user's role!";
            req.setAttribute("userRole", retrievedUser);
            req.setAttribute("success", message);
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("/users.jsp");
        dispatcher.forward(req, resp);
    }
}
