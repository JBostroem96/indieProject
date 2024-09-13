package edu.controller;

import edu.matc.entity.Category;
import edu.matc.entity.Role;
import edu.matc.entity.User;
import edu.matc.persistence.GenericDao;
import edu.matc.util.Authorization;
import edu.matc.util.Forward;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


/**
 * This class's purpose is to forward all the divisions to the jsp
 */
@WebServlet(

        name = "/addTeamDisplay",
        urlPatterns = { "/addTeamDisplay" }
)
public class AddTeamDisplay extends HttpServlet implements Authorization {

    /**
     * This method's purpose is to forward all the divisions to the jsp
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

        //Forwards to the page
        new Forward<>("/addTeam.jsp", req, response, null, new GenericDao<>(Category.class).getAll());
    }
}
