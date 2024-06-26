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
import java.time.LocalDate;
import java.util.List;

/**
 * This class' purpose is to edit a race result
 */
@WebServlet(
        urlPatterns = {"/editRaceResultById"}
)

public class EditRaceResultById extends HttpServlet {

    /**
     * This method's purpose is to edit the entry by id
     *@param  req               the request object that we forward
     *@param  resp           the response object that we forward
     *@exception ServletException  if an error occurs with the Servlet
     *@exception IOException       if an error occurs with the IO operations
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        final Logger logger = LogManager.getLogger(this.getClass());
        GenericDao raceDao = new GenericDao(Race.class);
        GenericDao teamDao = new GenericDao(Team.class);

        GenericDao teamRaceDao = new GenericDao(TeamRace.class);

        Validate validate = new Validate();

        int cp = Integer.parseInt(req.getParameter("cp"));
        int penalty = Integer.parseInt(req.getParameter("penalty"));
        double totalTime = Double.parseDouble(req.getParameter("time"));

        TeamRace teamRaceToUpdate = (TeamRace) teamRaceDao.getById(Integer.parseInt(req.getParameter("id")));
        Team team = (Team)teamDao.getById(Integer.parseInt(req.getParameter("team")));
        Race race = (Race) raceDao.getById(teamRaceToUpdate.getRace().getId());

        TeamRace updatedTeamRace = new TeamRace(team, race, cp, penalty, totalTime);

        if (validate.validateEditResult(teamRaceToUpdate.getTeam().getName(), teamRaceDao, race.getId()).contains(updatedTeamRace.getTeam().getName())) {

            String message = "That team name already exists";
            req.setAttribute("message", message);
            req.setAttribute("team_race", teamRaceToUpdate);

        } else {

            teamRaceToUpdate.setTeam(updatedTeamRace.getTeam());
            teamRaceToUpdate.setRace(updatedTeamRace.getRace());
            teamRaceToUpdate.setCp(updatedTeamRace.getCp());
            teamRaceToUpdate.setTotalTime(updatedTeamRace.getTotalTime());
            teamRaceToUpdate.setLatePenalty(updatedTeamRace.getLatePenalty());

            try {
                teamRaceDao.update(teamRaceToUpdate);
            } catch (Exception e) {
                logger.error("There was an issue updating the data", e);
            }

            req.setAttribute("editedRaceResult", teamRaceToUpdate);
        }
        req.setAttribute("team", teamDao.getAll());
        RequestDispatcher dispatcher = req.getRequestDispatcher("/editRaceResult.jsp");
        dispatcher.forward(req, resp);
    }
}
