package edu.controller;


import edu.matc.entity.TeamRace;
import edu.matc.persistence.GenericDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This class' purpose is to delete a race result
 */
@WebServlet(
        urlPatterns = {"/deleteRaceResultById"}
)
public class DeleteRaceResultById extends HttpServlet {

    /**
     * This method's purpose is to delete the entry by id
     *@param  req               the request object that we forward
     *@param  resp           the response object that we forward
     *@exception ServletException  if an error occurs with the Servlet
     *@exception IOException       if an error occurs with the IO operations
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        GenericDao dao = new GenericDao(TeamRace.class);

        TeamRace resultToDelete = (TeamRace)dao.getById(Integer.parseInt(req.getParameter("id")));

        dao.delete(resultToDelete);

        req.setAttribute("deletedRaceResult", resultToDelete);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/deleteRaceResult.jsp");
        dispatcher.forward(req, resp);
    }
}
