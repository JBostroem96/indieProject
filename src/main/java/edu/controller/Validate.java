package edu.controller;

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
public class Validate {

    private boolean entryExists;

    /**
     * Instantiates a new Validate, thus resetting the boolean
     */
    public Validate() {

        entryExists = false;
    }

    /**
     * This method's purpose is to validate the team
     * @param name the name
     * @param dao  the dao
     * @return entryExists — if the team already exists
     */
    public boolean validateTeam(String name, GenericDao<Team> dao, HttpServletRequest req) {

        for (Team team : dao.getAll()) {

            checkExistence(team.getName(), name, req);
        }
        return !entryExists;
    }

    /**
     * This method's purpose is to validate the race
     * @param name the name
     * @param dao  the dao
     * @return entryExists — if the race already exists
     */
    public boolean validateRace(String name, GenericDao<Race> dao, HttpServletRequest req) {

        for (Race race : dao.getAll()) {

            checkExistence(race.getName(), name, req);
        }
        return !entryExists;
    }

    /**
     * This method's purpose is to validate the result
     * @param name      the name
     * @param dao the teamRace dao
     * @param raceId    the race id
     * @return entryExists — if the result already exists
     */
    public boolean validateResult(int raceId, GenericDao<TeamRace> dao, String name, HttpServletRequest req) {

        for (TeamRace teamRace : dao.getAll()) {

            if (teamRace.getRace_id() == raceId) {

                checkExistence(teamRace.getTeam().getName(), name, req);
            }
        }
        return !entryExists;
    }

    /**
     * This method's purpose is to validate the user,
     * and it checks to see if the user's username that's sent over matches
     * any in the table, and if it does, it returns the one from the table
     * @param user the user
     * @param dao the User dao
     * @return entryExists — if the user already exists
     */
    public User validateUser(User user, GenericDao<User> dao, HttpServletRequest req) {

        for (User userEntry : dao.getAll()) {

            checkExistence(userEntry.getUserName(), user.getUserName(), req);

            if (entryExists) {

                return userEntry;
            }
        }

        return user;
    }

    /**
     * This method's purpose is to validate the user role
     * @param newRole the new role to be assigned
     * @param role the current role
     * @return entryExists — if it already exists
     */
    public boolean validateUserRole(String newRole, String role, HttpServletRequest req) {

        checkExistence(role, newRole, req);

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

