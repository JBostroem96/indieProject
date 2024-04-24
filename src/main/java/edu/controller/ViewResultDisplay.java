package edu.controller;

import edu.matc.entity.Race;
import edu.matc.entity.TeamRaces;
import edu.matc.entity.User;
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

        int overall_place = 0;
        int division_place = 0;
        GenericDao raceDao = new GenericDao(Race.class);
        GenericDao teamRaceDao = new GenericDao(TeamRaces.class);


        List<TeamRaces> teamRaces = teamRaceDao.findByPropertyEqual("race_id", req.getParameter("id"));
        Map<String, Integer> division_placements = new TreeMap<>();

        //using lambda expression to sort by the total time
        teamRaces.sort(Comparator.comparingDouble(TeamRaces::getTotal_time));

        int maleCounter = 0;
        int femaleCounter = 0;
        int soloFemaleCounter = 0;
        int soloMaleCounter = 0;

        for (TeamRaces entry : teamRaces) {

            switch (entry.getTeam().getDivision()) {
                case "Male":

                    maleCounter++;
                    entry.setDivision_place(maleCounter);

                    break;
                case "Female":

                    femaleCounter++;
                    entry.setDivision_place(femaleCounter);

                    break;
                case "Solo Male":

                    soloMaleCounter++;
                    entry.setDivision_place(soloMaleCounter);

                    break;
                case "Solo Female":

                    soloFemaleCounter++;
                    entry.setDivision_place(soloFemaleCounter);
                    break;
            }
            overall_place++;
            entry.setOverall_place(overall_place);
        }

        int raceId = Integer.parseInt(req.getParameter("id"));
        Race race = (Race)raceDao.getById(raceId);

        TeamRaces record = (TeamRaces)teamRaceDao.getById(raceId);


        req.setAttribute("team_races", teamRaces);
        req.setAttribute("division_placement", division_placements);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/viewRaceResult.jsp");
        dispatcher.forward(req, resp);
    }
}

