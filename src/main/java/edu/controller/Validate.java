package edu.controller;

import edu.matc.entity.Race;
import edu.matc.entity.Team;
import edu.matc.entity.TeamRace;
import edu.matc.entity.User;
import edu.matc.persistence.GenericDao;

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
    public List<String> validateName() {

        GenericDao teamDao = new GenericDao(Team.class);
        List<Team> teamNames = teamDao.getAll();

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
    public List<String> validateName(int raceId, List<TeamRace> teamNames) {

        for (TeamRace teamName : teamNames) {

            if (teamName.getRace_id() == raceId) {

                existingNames.add(teamName.getTeam().getName());
            }
        }
        return existingNames;
    }

    /**
     * Validate race list.
     *
     * @param races the races
     * @return the list
     */
    public List<String> validateRace(List<Race> races) {

        for (Race race : races) {

            existingNames.add(race.getName());
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
}
