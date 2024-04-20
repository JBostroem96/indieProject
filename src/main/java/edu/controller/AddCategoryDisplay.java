package edu.controller;

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

        name = "/AddCategoryDisplay",
        urlPatterns = { "/AddCategoryDisplay" }
)
public class AddCategoryDisplay extends HttpServlet {

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

        String url = "/addCategory.jsp";

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }
}
