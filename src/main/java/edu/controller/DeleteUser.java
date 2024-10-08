package edu.controller;

import edu.matc.entity.User;
import edu.matc.persistence.GenericDao;
import edu.matc.util.UseLogger;
import org.apache.logging.log4j.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import edu.matc.util.Authorization;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This class' purpose is to delete the user account
 */
@WebServlet(
        urlPatterns = {"/deleteUser"}
)
public class DeleteUser extends HttpServlet implements UseLogger {

    /**
     * This method's purpose is to delete the entry
     *@param  req               the request object that we forward
     *@param  resp           the response object that we forward
     *@exception ServletException  if an error occurs with the Servlet
     *@exception IOException       if an error occurs with the IO operations
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        final Logger logger = log();

        GenericDao<User> dao = new GenericDao<>(User.class);

        try {

            User user = (User) req.getSession().getAttribute("user");

            dao.delete(user);

            //signs the user out
            req.getSession().invalidate();

            //redirect to the home page
            resp.sendRedirect("index.jsp");

        } catch (Exception e) {

            logger.error("There was an issue deleting this account", e);
        }
    }
}
