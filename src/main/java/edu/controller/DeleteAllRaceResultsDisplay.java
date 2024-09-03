package edu.controller;

import edu.matc.entity.Race;
import edu.matc.entity.TeamRace;
import edu.matc.persistence.GenericDao;
import edu.matc.util.UseLogger;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;


@WebServlet(
        urlPatterns = {"/deleteAllRaceResultsDisplay"}
)

public class DeleteAllRaceResultsDisplay extends HttpServlet implements UseLogger {


    /**
     * This method's purpose is to delete the entry by id
     *
     * @param req  the request object that we forward
     * @param resp the response object that we forward
     * @throws ServletException if an error occurs with the Servlet
     * @throws IOException      if an error occurs with the IO operations
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        final Logger logger = log();
        Race retrievedRace = new GetEntry<Race>().parseEntry(new GenericDao<>(Race.class), req, logger);
        req.setAttribute("race", retrievedRace);


        RequestDispatcher dispatcher = req.getRequestDispatcher("/deleteRaceResults.jsp");
        dispatcher.forward(req, resp);
    }
}

