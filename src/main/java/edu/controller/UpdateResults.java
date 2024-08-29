package edu.controller;

import edu.matc.entity.TeamRace;
import edu.matc.persistence.GenericDao;
import edu.matc.util.UseLogger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import java.util.Comparator;
import java.util.List;

/**
 * This class' purpose is to update the results in the database whenever needed
 */
public class UpdateResults implements UseLogger {

    private List<TeamRace> teamRaces;

    /**
     * This constructor's purpose is to update the result set
     * @param dao the TeamRace dao
     * @param req the request object
     * @param raceId the race id
     */
    public UpdateResults(String raceId, GenericDao<TeamRace> dao, HttpServletRequest req) {

        //In the event that only one race needs to be updated, get the parameter,
        //and if it's not null, it will get all the races
        if (raceId == null) {

            raceId = req.getParameter("race_id");
        }

        teamRaces = dao.findByPropertyEqual("race_id", raceId);
        updateResults(dao);
    }

    /**
     * This method's purpose is to update the results
     * @param dao the TeamRace dao
     */
    public void updateResults(GenericDao<TeamRace> dao) {

        final Logger logger = log();

        teamRaces.sort(Comparator.comparingDouble(TeamRace::getTotalTime));

        for (TeamRace teamRace : rankDivision(teamRaces)) {

            try {
                dao.update(teamRace);

            } catch (Exception e) {

                logger.error("There was an issue updating the data", e);
            }
        }
    }

    /**
     * This method's purpose is to rank the different divisions
     *
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
     *
     * @param teamRaces          the list of results
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

