package edu.controller;

import edu.matc.entity.Race;
import edu.matc.entity.Role;
import edu.matc.util.Authorization;
import edu.matc.util.Forward;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This class' purpose is to forward to the JSP page
 * @author Jimmy Bostroem
 */
@WebServlet(

        name = "/AddRaceDisplay",
        urlPatterns = { "/AddRaceDisplay" }
)

public class AddRaceDisplay extends HttpServlet implements Authorization {

    /**
     * This method's purpose is to forward to the race JSP
     *@param  request               the request object that we forward
     *@param  response              the response object that we forward
     *@exception ServletException  if an error occurs with the Servlet
     *@exception IOException       if an error occurs with the IO operations
     */
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException {

        if (!authorize(response, request, Role.ADMIN, null)) {
            return;
        }

        //Forwards to the page
        new Forward<>("/addRace.jsp", request, response, null, null);
    }
}
