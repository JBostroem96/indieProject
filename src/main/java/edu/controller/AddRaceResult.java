package edu.controller;

import edu.matc.entity.*;
import edu.matc.persistence.GenericDao;
import edu.matc.util.Authorization;
import edu.matc.util.ForwardEntry;
import edu.matc.util.GetEntry;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * This class' purpose is to add the race result
 */
@WebServlet(
        urlPatterns = {"/addRaceResult"}
)

public class AddRaceResult extends HttpServlet implements Authorization {

    /**
     * This method's purpose is to add the race result
     * @param req  the request object
     * @param resp the response object
     * @throws ServletException the servlet exception object
     * @throws IOException      the io exception object
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (!authorize(resp, req, Role.ADMIN, null)) {
            return;
        }

        final Logger logger = log();

        GenericDao<Team> teamDao = new GenericDao<>(Team.class);
        GenericDao<TeamRace> teamRaceDao = new GenericDao<>(TeamRace.class);

        String teamEntry = req.getParameter("team");
        String cpEntry = req.getParameter("cp");
        String penaltyEntry = req.getParameter("penalty");
        String timeEntry = req.getParameter("time");
        Race race = new GetEntry<Race>().parseEntry(new GenericDao<>(Race.class), req, logger);

        if (cpEntry != null && !cpEntry.isEmpty()
                && penaltyEntry != null && !penaltyEntry.isEmpty()
                && timeEntry != null && !timeEntry.isEmpty()
                && teamEntry != null && !teamEntry.isEmpty()) {
            
            try {

                HttpSession session = req.getSession();

                Team team = teamDao.getById(Integer.parseInt(teamEntry));

                if (new Validate<>().validateResults(team.getName(), race.getId(), teamRaceDao, req)) {

                    int cp = Integer.parseInt(cpEntry);
                    int penalty = Integer.parseInt(penaltyEntry);
                    double totalTime = Double.parseDouble(timeEntry);

                    TeamRace teamRace = new TeamRace(team, race, (User) session.getAttribute("user"), cp, penalty, totalTime);

                    teamRaceDao.insert(teamRace);
                    req.setAttribute("resultAdded", "You successfully added the result!");

                    //update the results once inserted
                    new UpdateResults(race.getId(), teamRaceDao);
                }

            } catch (Exception e){

                req.setAttribute("e", e);
                logger.error("Something went wrong!", e);
            }

        } else {

            req.setAttribute("missingField", "Fields can't be empty");

        }

        //Forwards to the page
        new ForwardEntry<>("/addRaceResultForm.jsp", req, resp, race, teamDao.getAll());
    }
}









