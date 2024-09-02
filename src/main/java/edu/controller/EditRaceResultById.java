package edu.controller;

import edu.matc.entity.Race;
import edu.matc.entity.Team;
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

/**
 * This class' purpose is to edit a race result
 */
@WebServlet(
        urlPatterns = {"/editRaceResultById"}
)

public class EditRaceResultById extends HttpServlet implements UseLogger {

    /**
     * This method's purpose is to edit the entry by id
     *@param  req               the request object that we forward
     *@param  resp           the response object that we forward
     *@exception ServletException  if an error occurs with the Servlet
     *@exception IOException       if an error occurs with the IO operations
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        final Logger logger = log();

        GenericDao<Team> teamDao = new GenericDao<>(Team.class);
        GenericDao<TeamRace> teamRaceDao = new GenericDao<>(TeamRace.class);

        String cp = req.getParameter("cp");
        String penalty = req.getParameter("penalty");
        String time = req.getParameter("time");
        TeamRace teamRaceToUpdate =  teamRaceToUpdate = new GetEntry<TeamRace>().parseEntry(new GenericDao<>(TeamRace.class), req, logger);;

        try {

            if (teamRaceToUpdate != null
                    && cp != null && !cp.isEmpty()
                    && penalty != null && !penalty.isEmpty()
                    && time != null && !time.isEmpty()) {

                    Team team = teamDao.getById(Integer.parseInt(req.getParameter("team")));
                    Race race = teamRaceToUpdate.getRace();

                    int cpEntry = Integer.parseInt(cp);
                    int penaltyEntry = Integer.parseInt(penalty);
                    double totalTimeEntry = Double.parseDouble(time);

                    TeamRace updatedTeamRace = new TeamRace(team, race, cpEntry, penaltyEntry, totalTimeEntry);

                    if (new Validate<Race>().validateResults(updatedTeamRace.getTeam().getName(), race.getId(), teamRaceDao, req)) {

                        teamRaceToUpdate.setTeam(updatedTeamRace.getTeam());
                        teamRaceToUpdate.setRace(updatedTeamRace.getRace());
                        teamRaceToUpdate.setCp(updatedTeamRace.getCp());
                        teamRaceToUpdate.setTotalTime(updatedTeamRace.getTotalTime());
                        teamRaceToUpdate.setLatePenalty(updatedTeamRace.getLatePenalty());

                        teamRaceDao.update(teamRaceToUpdate);
                        req.setAttribute("resultUpdated", "You successfully updated the result");
                        //Update the results after editing
                        new UpdateResults(teamRaceToUpdate.getRace_id(), teamRaceDao, req);
                    }

            } else {

                req.setAttribute("missingField", "Fields can't be empty");
            }

        } catch (Exception e) {

            req.setAttribute("e", e);
            logger.error("there was an issue inserting the data", e);
        }

        req.setAttribute("editedRaceResult", teamRaceToUpdate);
        req.setAttribute("team", teamDao.getAll());
        RequestDispatcher dispatcher = req.getRequestDispatcher("/editRaceResult.jsp");
        dispatcher.forward(req, resp);
    }
}

