package edu.controller;

import edu.matc.entity.Race;
import edu.matc.entity.Team;
import edu.matc.entity.TeamRace;
import edu.matc.entity.User;
import edu.matc.persistence.GenericDao;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * This class' purpose is to perform different validations,
 * and a single boolean flag is used because the different validations
 * are using different instances
 */
public class Validate {

    private boolean entryExists;

    /**
     * Instantiates a new Validate.
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
    public boolean validateTeam(String name, GenericDao dao) {

        for (Object team : dao.getAll()) {

            Team teamEntry = (Team) team;

            checkExistence(teamEntry.getName(), name);
        }
        return entryExists;
    }


    /**
     * This method's purpose is to validate the race
     * @param name the name
     * @param dao  the dao
     * @return entryExists — if the race already exists
     */
    public boolean validateRace(String name, GenericDao dao) {

        for (Object race : dao.getAll()) {

            Race raceEntry = (Race) race;

            checkExistence(raceEntry.getName(), name);

        }
        return entryExists;
    }

    /**
     * This method's purpose is to validate the result
     * @param name      the name
     * @param dao the teamRace dao
     * @param raceId    the race id
     * @return entryExists — if the result already exists
     */
    public boolean validateResult(int raceId, GenericDao dao, String name) {

        for (Object teamRace : dao.getAll()) {

            TeamRace teamRaceEntry = (TeamRace) teamRace;

            if (teamRaceEntry.getRace_id() == raceId) {

                checkExistence(teamRaceEntry.getTeam().getName(), name);
            }
        }
        return entryExists;
    }

    /**
     * This method's purpose is to validate the user
     * @param session the session object
     * @param user the user
     * @return entryExists — if the user already exists
     */
    public boolean validateUser(HttpSession session, User user) {

        GenericDao dao = new GenericDao(User.class);

        for (Object userName : dao.getAll()) {

            User userEntry = (User) userName;

            checkExistence(userEntry.getName(), user.getUserName());
        }

        return entryExists;
    }

    /**
     * This method's purpose is to check the name's existence in the database
     * @param entryName the entry name
     * @param name the name
     */
    public void checkExistence(String entryName, String name) {

        if (entryName.equals(name)) {

            entryExists = true;
        }
    }
}

