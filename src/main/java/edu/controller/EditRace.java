package edu.controller;

import edu.matc.entity.Race;
import edu.matc.persistence.GenericDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This class' purpose is to verify the update of the entry
 */
@WebServlet(
        urlPatterns = {"/editRace"}
)

public class EditRace extends HttpServlet {

    /**
     * This method's purpose is to verify the update of the entry
     *@param  req               the request object that we forward
     *@param  resp           the response object that we forward
     *@exception ServletException  if an error occurs with the Servlet
     *@exception IOException       if an error occurs with the IO operations
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        GenericDao dao = new GenericDao(Race.class);
        int raceId = Integer.parseInt(req.getParameter("id"));
        Race retrievedRace = (Race)dao.getById(raceId);
        req.setAttribute("race", retrievedRace);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/editRace.jsp");
        dispatcher.forward(req, resp);
    }
}