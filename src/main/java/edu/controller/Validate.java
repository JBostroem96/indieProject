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
 * This class' purpose is to perform different validations
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
     * Validate team.
     *
     * @param name the name
     * @param dao  the dao
     * @return the list
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
     * Validate edit race list.
     *
     * @param name the name
     * @param dao  the dao
     * @return the list
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
     * Validate edit result list.
     *
     * @param name      the name
     * @param dao the teamRace dao
     * @param raceId    the race id
     * @return the list
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
     * @param user the user
     * @return the user
     */
    public boolean validateUser(User user) {

        GenericDao dao = new GenericDao(User.class);

        for (Object userName : dao.getAll()) {

            User userEntry = (User) userName;

            if (userEntry.getName().equals(user.getUserName())) {

                entryExists = true;
                break;

            }
            //insert user
        }

        return entryExists;
    }

    /**
     * This method's purpose is to validate the user
     * @param session the session object
     * @param userName the username
     */
    public void validateUser(HttpSession session, String userName) {

        GenericDao dao = new GenericDao(User.class);

        for (Object user : dao.getAll()) {

            User userEntry = (User) user;

            if (userEntry.getUserName().equals(userName)) {

                session.setAttribute("user", userEntry);
            }
        }
    }
}
