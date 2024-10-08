package edu.controller;

import edu.matc.entity.Role;
import edu.matc.entity.User;
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
 * This class' purpose is to edit the user role
 */
@WebServlet(
        urlPatterns = {"/editUserRole"}
)
public class EditUserRole extends HttpServlet implements Authorization {

    /**
     * This method's purpose is to edit entry
     *@param  req               the request object that we forward
     *@param  resp           the response object that we forward
     *@exception ServletException  if an error occurs with the Servlet
     *@exception IOException       if an error occurs with the IO operations
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (!authorize(resp, req, Role.ADMIN, null)) {
            return;
        }

        final Logger logger = log();
        GenericDao<User> dao = new GenericDao<>(User.class);

        String newRole = req.getParameter("role");
        User retrievedUser = new GetEntry<User>().parseEntry(new GenericDao<>(User.class), req, logger);;

        if (newRole != null && !newRole.isEmpty()) {

            try {

                if (new Validate<>().validateRole(newRole, retrievedUser.getRole(), req)) {

                    retrievedUser.setRole(Role.valueOf(newRole));
                    dao.update(retrievedUser);

                    req.setAttribute("updatedRole", "You successfully updated the user role!");
                }

            } catch (Exception e) {

                logger.error("There was an issue updating the data", e);
            }
        }

        //Forwards to the page
        new ForwardEntry<>("/users.jsp", req, resp, retrievedUser, null);
    }
}

