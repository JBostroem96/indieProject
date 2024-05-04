package edu.controller;

import edu.matc.entity.Race;
import edu.matc.entity.TeamRace;
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

        GenericDao raceDao = new GenericDao(Race.class);
        GenericDao teamRaceDao = new GenericDao(TeamRace.class);

        List<TeamRace> teamRaces = teamRaceDao.findByPropertyEqual("race_id", req.getParameter("id"));

        //using lambda expression to sort by the total time
        teamRaces.sort(Comparator.comparingDouble(TeamRace::getTotalTime));

        req.setAttribute("team_races", rankDivision(teamRaces));

        RequestDispatcher dispatcher = req.getRequestDispatcher("/viewRaceResult.jsp");
        dispatcher.forward(req, resp);
    }

    /**
     * This method's purpose is to rank the different divisions
     * @param teamRaces the team races
     * @return the team races
     */
    public List<TeamRace> rankDivision(List<TeamRace> teamRaces) {

        int overallPlace = 0;
        int maleDivision = 0;
        int femaleDivision = 0;
        int soloFemaleDivision = 0;
        int soloMaleDivision = 0;
        int mixedDivision = 0;

        for (TeamRace entry : teamRaces) {

            switch (entry.getTeam().getDivision()) {
                case "Male":

                    maleDivision++;
                    entry.setDivisionPlace(maleDivision);

                    break;
                case "Female":

                    femaleDivision++;
                    entry.setDivisionPlace(femaleDivision);

                    break;
                case "Solo Male":

                    soloMaleDivision++;
                    entry.setDivisionPlace(soloMaleDivision);

                    break;
                case "Solo Female":

                    soloFemaleDivision++;
                    entry.setDivisionPlace(soloFemaleDivision);

                    break;
                case "Mixed":

                    mixedDivision++;
                    entry.setDivisionPlace(mixedDivision);

                    break;
            }
            overallPlace++;
            entry.setOverallPlace(overallPlace);
        }
        return teamRaces;
    }
}