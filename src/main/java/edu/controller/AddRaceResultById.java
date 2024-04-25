package edu.controller;

import edu.matc.entity.Category;
import edu.matc.entity.Race;
import edu.matc.entity.TeamRaces;
import edu.matc.entity.Teams;
import edu.matc.persistence.GenericDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * This class' purpose is to add the reace result by grabbing the id
 */
@WebServlet(
        urlPatterns = {"/addRaceResultById"}
)

public class AddRaceResultById extends HttpServlet {

    /**
     * This method's purpose is to add the race result by getting the id
     * @param req the request object
     * @param resp the response object
     * @throws ServletException the servlet exception object
     * @throws IOException the io exception object
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        GenericDao raceDao = new GenericDao(Race.class);
        GenericDao teamDao = new GenericDao(Teams.class);
        GenericDao teamRaceDao = new GenericDao(TeamRaces.class);

        List<TeamRaces> teamNames = teamRaceDao.getAll();
        List<String> existingNames = new ArrayList<>();

        String name = req.getParameter("team");

        for (TeamRaces teamName : teamNames) {

            existingNames.add(teamName.getTeam().getName());
        }
        if (existingNames.contains(name)) {

            String message = "That team already exists. Please Enter something else.";
            req.setAttribute("message", message);

        } else {

            int cp = Integer.parseInt(req.getParameter("cp"));
            int penalty = Integer.parseInt(req.getParameter("penalty"));
            int totalTime = Integer.parseInt(req.getParameter("time"));

            int raceId = Integer.parseInt(req.getParameter("id"));
            Race race = (Race)raceDao.getById(raceId);

            int teamId = Integer.parseInt(req.getParameter("team"));
            Teams team = (Teams)teamDao.getById(teamId);
            TeamRaces teamRace = new TeamRaces(team, race, cp, penalty, totalTime);
            teamRaceDao.insert(teamRace);

            req.setAttribute("teamRaceResult", teamRace);
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("/addRaceResultForm.jsp");
        req.setAttribute("team", teamDao.getAll());
        dispatcher.forward(req, resp);
    }
}
