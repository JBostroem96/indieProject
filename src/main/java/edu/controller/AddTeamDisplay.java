package edu.controller;

import edu.matc.entity.Category;
import edu.matc.persistence.GenericDao;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * This class's purpose is to forward all the divisions to the jsp
 */
@WebServlet(

        name = "/addTeamDisplay",
        urlPatterns = { "/addTeamDisplay" }
)
public class AddTeamDisplay extends HttpServlet {

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

        GenericDao dao = new GenericDao(Category.class);

        req.setAttribute("category", dao.getAll());
        
        String url = "/addTeam.jsp";

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(req, response);
    }
}
