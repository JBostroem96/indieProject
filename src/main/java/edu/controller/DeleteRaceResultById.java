package edu.controller;


import edu.matc.entity.Race;
import edu.matc.entity.TeamRace;
import edu.matc.persistence.GenericDao;
import edu.matc.util.UseLogger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
public class DeleteRaceResultById extends HttpServlet implements UseLogger {

    /**
     * This method's purpose is to delete the entry by id
     *@param  req               the request object that we forward
     *@param  resp           the response object that we forward
     *@exception ServletException  if an error occurs with the Servlet
     *@exception IOException       if an error occurs with the IO operations
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        GenericDao<TeamRace> dao = new GenericDao<>(TeamRace.class);
        new DeleteEntry(dao, req);

        //Update the results after deletion
        new UpdateResults(dao, req);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/deleteRaceResult.jsp");
        dispatcher.forward(req, resp);
        }
    }

