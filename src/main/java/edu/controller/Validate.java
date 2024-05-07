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

    private List<String> existingNames;
    
    /**
     * Instantiates a new Validate.
     */
    public Validate() {

        existingNames = new ArrayList<>();
    }

    /**
     * This method's purpose is to validate the team name
     * @return the list of existing names
     */
    public List<String> validateEditTeam(String name, GenericDao dao) {

        for (Object team : dao.getAll()) {

            Team teamEntry = (Team) team;

            if (!teamEntry.getName().equals(name)) {

                existingNames.add(teamEntry.getName());
            }
        }

        return existingNames;
    }

    /**
     * Validate add team list.
     *
     * @param name the name
     * @param dao  the dao
     * @return the list
     */
    public List<String> validateAddTeam(String name, GenericDao dao) {

        for (Object team : dao.getAll()) {

            Team teamEntry = (Team) team;
            existingNames.add(teamEntry.getName());
        }
        return existingNames;

    }

    /**
     * This method's purpose is to validate the team name
     * @param raceId the race id
     * @param dao the teamRace dao
     * @return the list of existing names
     */
    public List<String> validateResult(int raceId, GenericDao dao) {

        for (Object teamRace : dao.getAll()) {

            TeamRace teamRaceEntry = (TeamRace) teamRace;

            if (teamRaceEntry.getRace_id() == raceId) {

                existingNames.add(teamRaceEntry.getTeam().getName());
            }
        }
        return existingNames;
    }


    /**
     * Validate edit race list.
     *
     * @param name the name
     * @param dao  the dao
     * @return the list
     */
    public List<String> validateEditRace(String name, GenericDao dao) {

        for (Object race : dao.getAll()) {

            Race raceEntry = (Race) race;

            if (!raceEntry.getName().equals(name)) {
                existingNames.add(raceEntry.getName());
            }
        }
        return existingNames;
    }

    /**
     * Validate add race list.
     *
     * @param name the name
     * @param dao  the dao
     * @return the list
     */
    public List<String> validateAddRace(String name, GenericDao dao) {

        for (Object race : dao.getAll()) {

            Race raceEntry = (Race) race;
            existingNames.add(raceEntry.getName());
        }

        return existingNames;
    }

    /**
     * Validate edit result list.
     *
     * @param name      the name
     * @param dao the teamRace dao
     * @param raceId    the race id
     * @return the list
     */
    public List<String> validateEditResult(String name, GenericDao dao, int raceId) {

        for (Object teamRace : dao.getAll()) {

            TeamRace teamRaceEntry = (TeamRace) teamRace;

            if (teamRaceEntry.getRace_id() == raceId) {

                if (!teamRaceEntry.getTeam().getName().equals(name)) {

                    existingNames.add(teamRaceEntry.getTeam().getName());
                }
            }
        }
        return existingNames;
    }

    /**
     * This method's purpose is to validate the user
     * @param user the user
     * @return the user
     */
    public User validateUser(User user) {

        GenericDao dao = new GenericDao(User.class);

        for (Object userName : dao.getAll()) {

            User userEntry = (User) userName;
            existingNames.add(userEntry.getUserName());
        }

        if (!existingNames.contains(user.getUserName())) {

            dao.insert(user);
        }
        return user;
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
