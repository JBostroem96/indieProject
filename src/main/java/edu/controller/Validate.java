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

            if (teamEntry.getName().equals(name)) {

                entryExists = true;
                break;
            }
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

            if (raceEntry.getName().equals(name)) {

                entryExists = true;
                break;
            }
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

                if (teamRaceEntry.getTeam().getName().equals(name)) {

                    entryExists = true;
                    break;
                }
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

            authenticateUser(dao, session, userEntry, user);
        }

        return entryExists;
    }

    /**
     * This method's purpose is to authenticate the user
     * @param dao the GenericDao object
     * @param session the session object
     * @param userEntry the user entry
     * @param user the user
     */
    public void authenticateUser(GenericDao dao, HttpSession session, User userEntry, User user) {

        if (userEntry.getName().equals(user.getUserName())) {

            entryExists = true;
            //sign the user in
            session.setAttribute("user", userEntry);
        }
        //Only insert the user if they don't already exist
        if (!entryExists) {

            dao.insert(user);
        }
    }
}
