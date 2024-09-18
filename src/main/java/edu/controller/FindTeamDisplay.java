package edu.controller;

import edu.matc.util.ForwardEntry;

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

        name = "/FindTeamDisplay",
        urlPatterns = { "/FindTeamDisplay" }
)
public class FindTeamDisplay extends HttpServlet {

    /**
     * This method's purpose is to forward to the JSP
     *@param  request               the request object that we forward
     *@param  response              the response object that we forward
     *@exception ServletException  if an error occurs with the Servlet
     *@exception IOException       if an error occurs with the IO operations
     */
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException {

        //Forwards to the page
        new ForwardEntry<>("/searchTeams.jsp", request, response, null, null);
    }
}
