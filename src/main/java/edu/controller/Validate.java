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
    private List<Race> races;
    private List<Team> teamNames;

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

        teamNames = dao.getAll();

        for (Team teamName : teamNames) {

            if (!teamName.getName().equals(name)) {

                existingNames.add(teamName.getName());
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

        teamNames = dao.getAll();

        for (Team teamName : teamNames) {

            existingNames.add(teamName.getName());
        }
        return existingNames;
    }

    /**
     * This method's purpose is to validate the team name
     * @param raceId the race id
     * @param teamNames the team names
     * @return the list of existing names
     */
    public List<String> validateResult(int raceId, List<TeamRace> teamNames) {

        for (TeamRace teamName : teamNames) {

            if (teamName.getRace_id() == raceId) {

                existingNames.add(teamName.getTeam().getName());
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

        races = dao.getAll();

        for (Race race : races) {

            if (!race.getName().equals(name)) {
                existingNames.add(race.getName());
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

        List<Race> races = dao.getAll();

        for (Race race : races) {

            existingNames.add(race.getName());
        }

        return existingNames;
    }

    /**
     * Validate edit result list.
     *
     * @param name      the name
     * @param teamNames the team names
     * @param raceId    the race id
     * @return the list
     */
    public List<String> validateEditResult(String name, List<TeamRace> teamNames, int raceId) {

        for (TeamRace teamName : teamNames) {

            if (teamName.getRace_id() == raceId) {

                if (!teamName.getTeam().getName().equals(name)) {

                    existingNames.add(teamName.getTeam().getName());
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
        List<User> names = dao.getAll();

        for (User name : names) {

            existingNames.add(name.getUserName());
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

        GenericDao userDao = new GenericDao(User.class);
        List<User> users = userDao.getAll();

        for (User user : users) {

            if (user.getUserName().equals(userName)) {

                session.setAttribute("user", user);
            }
        }
    }
}
