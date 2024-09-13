package edu.matc.util;

import edu.matc.entity.Race;
import edu.matc.persistence.GenericDao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class' purpose is to display the delete button of a race entry if it contains any results
 */
public class DeleteAllRaceResultDisplay {

    /**
     * This method's purpose is to check to see if the race has any results
     * @param raceDao the race dao
     * @return raceHasResults - if the race has results
     */
    public Map<Integer, Boolean> showDeletionOfResults(GenericDao<Race> raceDao) {

        List<Race> races = raceDao.getAll();

        Map<Integer, Boolean> raceHasResults = new HashMap<>();

        //Add the id if the race has any results
        for (Race race : races) {
            raceHasResults.put(race.getId(), !race.getTeamRaces().isEmpty());
        }

        return raceHasResults;
    }
}
