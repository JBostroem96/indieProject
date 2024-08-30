package edu.controller;

import edu.matc.entity.TeamRace;
import edu.matc.persistence.GenericDao;
import javax.servlet.http.HttpServletRequest;

/**
 * This class' purpose is to validate the results
 */
public class ValidateResults {

    /**
     * This method's purpose is to validate the results
     * @param name the name
     * @param raceId the race id
     * @param dao the generic dao
     * @param req the request object
     * @return entryExists - if the entry already exists
     */
    public boolean validate(String name, int raceId, GenericDao<TeamRace> dao, HttpServletRequest req) {

        Validate validate = new Validate<>();

        for (TeamRace entry : dao.getAll()) {

            if (entry.getRace_id() == raceId) {

                validate.checkExistence(entry.getTeam().getName(), name, req);
            }
        }
        return validate.getEntryExists();
    }
}
