package edu.controller;


import edu.matc.entity.Role;
import edu.matc.entity.User;
import edu.matc.persistence.GenericDao;
import edu.matc.util.Authorization;
import edu.matc.util.ForwardEntry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class' purpose is to forward all guests with the role of "user"
 */
@WebServlet(
        urlPatterns = {"/displayUsers"}
)
public class DisplayUsers extends HttpServlet implements Authorization {

    /**
     * This method's purpose is to forward all users with the role "user"
     *@param  req               the request object that we forward
     *@param  response             the response object that we forward
     *@exception ServletException  if an error occurs with the Servlet
     *@exception IOException       if an error occurs with the IO operations
     */
    public void doGet(HttpServletRequest req,
                      HttpServletResponse response)
            throws ServletException, IOException {

        if (!authorize(response, req, Role.ADMIN, null)) {
            return;
        }

        GenericDao<User> dao = new GenericDao<>(User.class);
        List<User> users = new ArrayList<>();

        for (User user : dao.getAll()) {

            if (user.getRole().equals(Role.USER)) {

                users.add(user);
            }
        }

        //Forwards to the JSP
        new ForwardEntry<>("/users.jsp", req, response, null, users);
    }
}
