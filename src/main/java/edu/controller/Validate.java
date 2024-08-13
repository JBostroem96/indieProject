package edu.controller;

import edu.matc.entity.Race;
import edu.matc.entity.Team;
import edu.matc.entity.TeamRace;
import edu.matc.entity.User;
import edu.matc.persistence.GenericDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpSession;
import javax.swing.text.html.parser.Entity;
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
    public boolean validateTeam(String name, GenericDao<Team> dao) {

        for (Team team : dao.getAll()) {

            checkExistence(team.getName(), name);
        }
        return entryExists;
    }


    /**
     * This method's purpose is to validate the race
     * @param name the name
     * @param dao  the dao
     * @return entryExists — if the race already exists
     */
    public boolean validateRace(String name, GenericDao<Race> dao) {

        for (Race race : dao.getAll()) {

            checkExistence(race.getName(), name);

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
    public boolean validateResult(int raceId, GenericDao<TeamRace> dao, String name) {

        for (TeamRace teamRace : dao.getAll()) {

            if (teamRace.getRace_id() == raceId) {

                checkExistence(teamRace.getTeam().getName(), name);
            }
        }
        return entryExists;
    }

    /**
     * This method's purpose is to validate the user
     * @param user the user
     * @return entryExists — if the user already exists
     */
    public boolean validateUser(User user) {

        GenericDao<User> dao = new GenericDao(User.class);

        for (User userName : dao.getAll()) {
            
            checkExistence(userName.getName(), user.getUserName());
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

