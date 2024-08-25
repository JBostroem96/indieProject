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
     *
     * @param req  the request object
     * @param resp the response object
     * @throws ServletException the servlet exception object
     * @throws IOException      the io exception object
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        final Logger logger = LogManager.getLogger(this.getClass());

        GenericDao<Team> teamDao = new GenericDao<>(Team.class);
        GenericDao<TeamRace> teamRaceDao = new GenericDao<>(TeamRace.class);

        Race race = new GenericDao<>(Race.class).getById(Integer.parseInt(req.getParameter("race_id")));
        Team team = teamDao.getById(Integer.parseInt(req.getParameter("team")));

        if (team != null && race != null
                && req.getParameter("cp") != null
                && req.getParameter("penalty") != null
                && req.getParameter("time") != null) {

            try {

                int cp = Integer.parseInt(req.getParameter("cp"));
                int penalty = Integer.parseInt(req.getParameter("penalty"));
                double totalTime = Double.parseDouble(req.getParameter("time"));

                if (new Validate().validateResult(race.getId(), teamRaceDao, team.getName())) {

                    String message = "That team already exists in that race. Please enter a different one.";
                    req.setAttribute("message", message);

                } else {

                    TeamRace teamRace = new TeamRace(team, race, cp, penalty, totalTime);

                    teamRaceDao.insert(teamRace);

                    //update the results once inserted
                    new UpdateResults(teamRaceDao, req);

                    req.setAttribute("teamRaceResult", teamRace);
                }

            } catch (NumberFormatException nfe) {

                req.setAttribute("nfe", nfe);
                logger.error("there was an issue parsing the data", nfe);

            } catch (Exception e) {

                req.setAttribute("e", e);
                logger.error("there was an issue inserting the data", e);
            }

            req.setAttribute("team", teamDao.getAll());
            req.setAttribute("race", race);

            RequestDispatcher dispatcher = req.getRequestDispatcher("/addRaceResultForm.jsp");
            dispatcher.forward(req, resp);
        }
    }
}






