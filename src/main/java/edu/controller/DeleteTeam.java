package edu.controller;

import edu.matc.entity.Role;
import edu.matc.entity.Team;
import edu.matc.entity.TeamRace;
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
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class' purpose is to delete a team
 */
@WebServlet(
        urlPatterns = {"/deleteTeam"}
)
public class DeleteTeam extends HttpServlet implements Authorization {

    /**
     * This method's purpose is to delete the entry by id
     *@param  req               the request object that we forward
     *@param  resp           the response object that we forward
     *@exception ServletException  if an error occurs with the Servlet
     *@exception IOException       if an error occurs with the IO operations
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (!authorize(resp, req, Role.ADMIN, null)) {
            return;
        }

        final Logger logger = log();
        GenericDao<TeamRace> teamRaceDao = new GenericDao<>(TeamRace.class);
        GenericDao<Team> dao = new GenericDao<>(Team.class);
        Team team = new GetEntry<Team>().parseEntry(dao, req, logger);
        List<Integer> races = new ArrayList<>();

        //Gets all the races where the teams are
        getAllRaces(teamRaceDao, team, races);

        try {

            dao.delete(team);
            req.setAttribute("entryDeleted", "You deleted the entry");

            //Only Update the results after deletion if the teams are in any races
            if (!races.isEmpty()) {
                updateRaces(teamRaceDao, races);
            }

        } catch (Exception e) {
            req.setAttribute("e", "Something went wrong!");
            logger.error("Something went wrong!", e);
        }

        req.setAttribute("teams", dao.getAll());
        new ForwardEntry<>("/searchResults.jsp", req, resp, null, null);
    }

    /**
     * This method's purpose is to update the races/results
     * @param dao the teamRace dao
     * @param races the list of race ids
     */
    public void updateRaces(GenericDao<TeamRace> dao, List<Integer> races) {

        for (Integer race : races) {

            new UpdateResults(race, dao);
        }
    }

    /**
     * This method's purpose is to get all the races where the teams are
     * @param teamRace the teamRace dao
     * @param retrievedTeam the retrieved team
     * @param races the races
     */
    public void getAllRaces(GenericDao<TeamRace> teamRace, Team retrievedTeam, List<Integer> races) {

        for (TeamRace entry : teamRace.getAll()) {

            if (entry.getTeam().getName().equals(retrievedTeam.getName())) {

                races.add(entry.getRace_id());
            }
        }
    }
}
