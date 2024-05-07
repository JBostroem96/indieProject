package edu.controller;

import edu.matc.entity.Race;
import edu.matc.entity.Team;
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
import java.util.List;

/**
 * This class' purpose is to add the race result by grabbing the id
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

        final Logger logger = LogManager.getLogger(this.getClass());

        GenericDao raceDao = new GenericDao(Race.class);
        GenericDao teamDao = new GenericDao(Team.class);
        GenericDao teamRaceDao = new GenericDao(TeamRace.class);

        Validate validate = new Validate();

        Race race = (Race)raceDao.getById(Integer.parseInt(req.getParameter("id")));
        Team team = (Team)teamDao.getById(Integer.parseInt(req.getParameter("team")));

        if (validate.validateResult(race.getId(), teamRaceDao).contains(team.getName())) {

            String message = "That team already exists in that race. Please enter a different one.";
            req.setAttribute("message", message);

        } else {

            int cp = Integer.parseInt(req.getParameter("cp"));
            int penalty = Integer.parseInt(req.getParameter("penalty"));
            double totalTime = Double.parseDouble(req.getParameter("time"));

            TeamRace teamRace = new TeamRace(team, race, cp, penalty, totalTime);

            try {
                teamRaceDao.insert(teamRace);

            } catch (Exception e) {
                logger.error("there was an issue inserting the data", e);
            }

            req.setAttribute("teamRaceResult", teamRace);
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("/addRaceResultForm.jsp");
        req.setAttribute("team", teamDao.getAll());
        req.setAttribute("race", race);
        dispatcher.forward(req, resp);
    }
}
