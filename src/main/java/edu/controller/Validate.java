package edu.controller;

import edu.matc.util.*;
import edu.matc.entity.Race;
import edu.matc.entity.Team;
import edu.matc.entity.TeamRace;
import edu.matc.entity.User;
import edu.matc.persistence.GenericDao;

import javax.servlet.http.HttpServletRequest;



/**
 * This class' purpose is to perform different validations,
 * and a single boolean flag is used because the different validations
 * are using different instances
 */
public class Validate<T extends Validation> {

    private boolean entryExists;

    /**
     * Instantiates a new Validate, thus resetting the boolean
     */
    public Validate() {

        entryExists = false;
    }

    /**
     * This method's purpose is to check to see if there is a match or not
     * @param name the name
     * @param dao the generic dao
     * @param req the request object
     * @return entryExists - if there is a match
     */
    public boolean validate(String name, GenericDao<T> dao, HttpServletRequest req) {

        for (T entry : dao.getAll()) {

            checkExistence(entry.getName(), name, req);
        }
        return !entryExists;
    }

    /**
     * This method's purpose is to validate the results
     * @param name the name
     * @param raceId the race id
     * @param dao the generic dao
     * @param req the request object
     * @return entryExists - if the entry already exists
     */
    public boolean validateResults(String name, int raceId, GenericDao<TeamRace> dao, HttpServletRequest req) {

        for (TeamRace entry : dao.getAll()) {

            if (entry.getRace_id() == raceId) {

                checkExistence(entry.getTeam().getName(), name, req);
            }
        }
        return !entryExists;
    }

    /**
     * This method's purpose is to validate the role
     * @param newRole the new role
     * @param role the role
     * @param req the request object
     * @return entryExists - if the entry exists or not
     */
    public boolean validateRole(String newRole, String role, HttpServletRequest req) {

        checkExistence(newRole, role, req);
        return !entryExists;
    }

    /**
     * This method's purpose is to check the entry's existence in the database
     * @param entryName the entry name
     * @param name the name
     */
    public void checkExistence(String entryName, String name, HttpServletRequest req) {

        if (entryName.equalsIgnoreCase(name)) {

            entryExists = true;
            req.setAttribute("message", "That entry already exists");
        }
    }
}

