package edu.controller;


import edu.matc.entity.Division;
import edu.matc.entity.TeamRace;
import edu.matc.persistence.GenericDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Array;
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

        GenericDao<TeamRace> teamRaceDao = new GenericDao<>(TeamRace.class);

        List<TeamRace> teamRaces = teamRaceDao.findByPropertyEqual("race_id", req.getParameter("id"));

        //using lambda expression to sort by the total time
        teamRaces.sort(Comparator.comparingDouble(TeamRace::getTotalTime));

        req.setAttribute("team_races", updateResults(rankDivision(teamRaces), teamRaceDao));

        RequestDispatcher dispatcher = req.getRequestDispatcher("/viewRaceResult.jsp");
        dispatcher.forward(req, resp);
    }

    /**
     * Update results list.
     *
     * @param teamRaces the team races
     * @param dao       the dao
     * @return the list
     */
    public List<TeamRace> updateResults(List<TeamRace> teamRaces, GenericDao<TeamRace> dao) {

        final Logger logger = LogManager.getLogger(this.getClass());

        for (TeamRace teamRace : teamRaces) {

            try {
                dao.update(teamRace);

            } catch (Exception e) {

                logger.error("There was an issue updating the data", e);
            }

        }
        return teamRaces;
    }

    /**
     * This method's purpose is to rank the different divisions
     * @param teamRaces the team races
     * @return the team races
     */
    public List<TeamRace> rankDivision(List<TeamRace> teamRaces) {

        int[] divisionPlacements = {0, 0, 0, 0, 0, 0};

        incrementDivisions(teamRaces, divisionPlacements);

        return teamRaces;
    }

    /**
     * This method's purpose is to loop through the results and display the ranks
     * @param teamRaces the list of results
     * @param divisionPlacements the different division rankings
     */
    public void incrementDivisions(List<TeamRace> teamRaces, int[] divisionPlacements) {

        for (TeamRace entry : teamRaces) {

            switch (entry.getTeam().getCategory().getDivision()) {
                case MALE:

                    divisionPlacements[0]++;
                    entry.setDivisionPlace(divisionPlacements[0]);

                    break;
                case FEMALE:

                    divisionPlacements[1]++;
                    entry.setDivisionPlace(divisionPlacements[1]);

                    break;
                case SOLO_MALE:

                    divisionPlacements[2]++;
                    entry.setDivisionPlace(divisionPlacements[2]);

                    break;
                case SOLO_FEMALE:

                    divisionPlacements[3]++;
                    entry.setDivisionPlace(divisionPlacements[3]);
                    break;
                case MIXED:

                    divisionPlacements[4]++;
                    entry.setDivisionPlace(divisionPlacements[4]);
                    break;
            }
            divisionPlacements[5]++;
            entry.setOverallPlace(divisionPlacements[5]);
        }
    }
}