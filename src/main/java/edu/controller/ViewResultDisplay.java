package edu.controller;

import edu.matc.entity.Race;
import edu.matc.entity.TeamRaces;
import edu.matc.persistence.GenericDao;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;


/**
 * This class' purpose is to view the results of a race
 */
@WebServlet(

        name = "/viewRaceResult",
        urlPatterns = { "/viewRaceResult" }
)
public class ViewResultDisplay extends HttpServlet {

    /**
     * This method's purpose is to view the results of a race
     *@param  req               the request object that we forward
     *@param  resp            the response object that we forward
     *@exception ServletException  if an error occurs with the Servlet
     *@exception IOException       if an error occurs with the IO operations
     */
    public void doGet(HttpServletRequest req,
                      HttpServletResponse resp)
            throws ServletException, IOException {

        int overallPlace = 0;
        int maleDivision = 0;
        int femaleDivision = 0;
        int soloFemaleDivision = 0;
        int soloMaleDivision = 0;
        int mixedDivision = 0;

        GenericDao raceDao = new GenericDao(Race.class);
        GenericDao teamRaceDao = new GenericDao(TeamRaces.class);

        List<TeamRaces> teamRaces = teamRaceDao.findByPropertyEqual("race_id", req.getParameter("id"));

        //using lambda expression to sort by the total time
        teamRaces.sort(Comparator.comparingDouble(TeamRaces::getTotal_time));

        for (TeamRaces entry : teamRaces) {

            switch (entry.getTeam().getDivision()) {
                case "Male":

                    maleDivision++;
                    entry.setDivision_place(maleDivision);

                    break;
                case "Female":

                    femaleDivision++;
                    entry.setDivision_place(femaleDivision);

                    break;
                case "Solo Male":

                    soloMaleDivision++;
                    entry.setDivision_place(soloMaleDivision);

                    break;
                case "Solo Female":

                    soloFemaleDivision++;
                    entry.setDivision_place(soloFemaleDivision);

                    break;
                case "Mixed":

                    mixedDivision++;
                    entry.setDivision_place(mixedDivision);

                    break;
            }
            overallPlace++;
            entry.setOverall_place(overallPlace);
        }

        req.setAttribute("team_races", teamRaces);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/viewRaceResult.jsp");
        dispatcher.forward(req, resp);
    }
}