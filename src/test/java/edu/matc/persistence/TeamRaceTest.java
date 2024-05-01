package edu.matc.persistence;
import edu.matc.entity.Category;
import edu.matc.testUtil.Database;
import edu.matc.entity.Race;
import edu.matc.entity.Team;
import edu.matc.entity.TeamRace;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This class' purpose is to test the functionality of this application
 */
class TeamRaceTest {

    private GenericDao teamRaceDao;
    private GenericDao teamDao;
    private GenericDao raceDao;

    /**
     * Triggers before everything else
     */
    @BeforeEach
    void setUp() {

        Database database = Database.getInstance();
        database.runSQL("clean_DB.sql");
        teamRaceDao = new GenericDao(TeamRace.class);
        teamDao = new GenericDao(Team.class);
        raceDao = new GenericDao(Race.class);
    }

    /**
     * Gets by id.
     */
    @Test
    void getById() {

        TeamRace retrievedTeamRace = (TeamRace)teamRaceDao.getById(2);
        assertNotNull(retrievedTeamRace);
        assertEquals(teamRaceDao.getById(2), retrievedTeamRace);
    }

    /**
     * Update.
     */

    @Test
    void update() {

        Category category = new Category();
        category.setCategory_id(5);
        category.setDivision("Mixed");

        Team team = new Team("Example2", category, category.getDivision());
        teamDao.insert(team);
        Race race = new Race("Example3", "5", LocalDate.now());
        raceDao.insert(race);
        TeamRace teamRaceToUpdate = (TeamRace) teamRaceDao.getById(5);

        teamRaceToUpdate.setTeam(team);
        teamRaceToUpdate.setRace(race);
        teamRaceToUpdate.setCp(22);
        teamRaceToUpdate.setLatePenalty(0);
        teamRaceToUpdate.setTotalTime(120);

        teamRaceDao.update(teamRaceToUpdate);
        assertNotEquals(0, teamRaceToUpdate.getId());
        TeamRace updatedTeamRace = (TeamRace) teamRaceDao.getById(5);
        assertEquals(teamRaceToUpdate, updatedTeamRace);
    }

    /**
     * Insert.
     */
    @Test
    void insert() {

        Category category = new Category();
        category.setCategory_id(5);
        category.setDivision("Mixed");

        Team team = new Team("Example", category, category.getDivision());
        Race race = new Race("Example", "3", LocalDate.now());

        TeamRace teamRace = new TeamRace(team, race, 22, 0, 120);

        int insertedTeamRaceId = teamRaceDao.insert(teamRace);
        assertNotEquals(0, insertedTeamRaceId);
        TeamRace insertedTeamRace = (TeamRace)teamRaceDao.getById(insertedTeamRaceId);
        assertEquals(teamRace, insertedTeamRace);

    }


    @Test
    void delete() {

        teamRaceDao = new GenericDao(TeamRace.class);
        teamRaceDao.delete(teamRaceDao.getById(2));
        assertNull(teamRaceDao.getById(2));
    }

    /**
     * Gets all.
     */

    @Test
    void getAll() {

        List<TeamRace> teamRaces = teamRaceDao.getAll();
        assertEquals(17, teamRaces.size());
    }

    /**
     * This test's purpose is to ensure that the property has the
     * matching value. In this case "Joe" is a first name in
     * the table
     */

    @Test
    void getByPropertyEqual() {

        List<TeamRace> teamRaces = teamRaceDao.findByPropertyEqual("cp", "17");
        assertEquals(14, teamRaces.size());
        assertEquals(1, teamRaces.get(0).getId());
    }
}
