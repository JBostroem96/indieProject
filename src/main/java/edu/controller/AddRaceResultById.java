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
import java.time.LocalDate;
import java.util.List;


@WebServlet(
        urlPatterns = {"/addRaceResultById"}
)
public class AddRaceResultById extends HttpServlet {

/**
     * This class' purpose is to edit a race and
     * display the result for the user
     */


@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    GenericDao raceDao = new GenericDao(Race.class);
    GenericDao teamDao = new GenericDao(Teams.class);
    GenericDao teamRaces = new GenericDao(TeamRaces.class);
    //we probably need the team name, the cp, and the late_penalty

    int cp = Integer.parseInt(req.getParameter("cp"));
    int penalty = Integer.parseInt(req.getParameter("penalty"));
    int totalTime = Integer.parseInt(req.getParameter("time"));

    int raceId = Integer.parseInt(req.getParameter("id"));
    Race race = (Race)raceDao.getById(raceId);

    int teamId = Integer.parseInt(req.getParameter("team"));
    Teams team = (Teams)teamDao.getById(teamId);

    TeamRaces teamRace = new TeamRaces(team, race, cp, penalty, totalTime);
    teamRaces.insert(teamRace);
    req.setAttribute("teamRaceResult", teamRace);

            RequestDispatcher dispatcher = req.getRequestDispatcher("/addedRaceResult.jsp");
            dispatcher.forward(req, resp);
        }
}
