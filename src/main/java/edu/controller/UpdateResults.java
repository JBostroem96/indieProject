package edu.controller;

import edu.matc.entity.Division;
import edu.matc.entity.TeamRace;
import edu.matc.persistence.GenericDao;
import edu.matc.util.UseLogger;
import org.apache.logging.log4j.Logger;
import java.util.Comparator;
import java.util.List;
import java.util.EnumMap;

/**
 * This class updates the results in the database whenever needed.
 */
public class UpdateResults implements UseLogger {

    private List<TeamRace> teamRaces;
    private int overallPlacement;

    /**
     * Constructor for updating the result set.
     * @param raceId the race ID
     * @param dao the TeamRace DAO
     */
    public UpdateResults(int raceId, GenericDao<TeamRace> dao) {

        this.teamRaces = dao.findByPropertyEqual("race_id", raceId);
        this.overallPlacement = 0;
        updateResults(dao);
    }

    /**
     * Updates the results.
     * @param dao the TeamRace DAO
     */
    public void updateResults(GenericDao<TeamRace> dao) {

        final Logger logger = log();

        // Sort the team races by total time
        teamRaces.sort(Comparator.comparingDouble(TeamRace::getTotalTime));

        // Rank divisions and update results
        for (TeamRace teamRace : rankDivision(teamRaces)) {
            try {
                dao.update(teamRace);
            } catch (Exception e) {
                logger.error("There was an issue updating the data", e);
            }
        }
    }

    /**
     * Ranks the teams by division and overall placement.
     * @param teamRaces the list of team races
     * @return the ranked list of team races
     */
    public List<TeamRace> rankDivision(List<TeamRace> teamRaces) {

        //EnumMap for the different divisions
        EnumMap<Division, Integer> divisionPlacements = new EnumMap<>(Division.class);

        //Looping through the divisions, putting the divisions, starting at 0, into the map
        for (Division division : Division.values()) {
            divisionPlacements.put(division, 0);
        }

        //Increment them if they exist
        incrementDivisions(teamRaces, divisionPlacements);

        return teamRaces;
    }

    /**
     * Increments division and overall placements.
     * @param teamRaces the list of team races
     * @param divisionPlacements the division placements map
     */
    public void incrementDivisions(List<TeamRace> teamRaces, EnumMap<Division, Integer> divisionPlacements) {

        //Loop through the list
        for (TeamRace entry : teamRaces) {

            Division division = entry.getTeam().getCategory().getDivision();

            //If the division is encountered ...
            if (divisionPlacements.containsKey(division)) {
                //Increment
                divisionPlacements.put(division, divisionPlacements.get(division) + 1);
                entry.setDivisionPlace(divisionPlacements.get(division));
            }
            //Also increment the overall placement
            overallPlacement++;
            entry.setOverallPlace(overallPlacement);
        }
    }
}


