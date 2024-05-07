package edu.controller;


import edu.matc.entity.Team;
import edu.matc.persistence.GenericDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        urlPatterns = {"/deleteTeam"}
)
public class DeleteTeamDisplay extends HttpServlet {

    /**
     * This method's purpose is to verify the deletion of an entry
     *@param  req               the request object that we forward
     *@param  resp           the response object that we forward
     *@exception ServletException  if an error occurs with the Servlet
     *@exception IOException       if an error occurs with the IO operations
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        GenericDao dao = new GenericDao(Team.class);
        Team retrievedTeam = (Team)dao.getById(Integer.parseInt(req.getParameter("id")));
        req.setAttribute("team", retrievedTeam);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/deleteTeam.jsp");
        dispatcher.forward(req, resp);
    }
}
