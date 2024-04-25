package edu.matc.persistence;

import edu.matc.entity.Category;
import edu.matc.entity.TeamRaces;
import edu.matc.entity.Teams;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TeamDaoTest {

    private GenericDao teamDao;
    private GenericDao teamRaceDao;

    /**
     * Triggers before everything else
     */
    @BeforeEach
    void setUp() {

        Database database = Database.getInstance();
        database.runSQL("clean_DB.sql");
        teamDao = new GenericDao(Teams.class);
    }


    /**
     * Gets by id.
     */
    @Test
    void getById() {

        Teams retrievedTeam = (Teams)teamDao.getById(1);
        assertNotNull(retrievedTeam);
        assertEquals(teamDao.getById(1), retrievedTeam);
    }

    /**
     * Update.
     */
    @Test
    void update() {

        Teams team = new Teams("test", "test");

        Teams teamToUpdate = (Teams)teamDao.getById(1);

        teamToUpdate.setName(team.getName());
        teamToUpdate.setName(team.getDivision());

        teamDao.update(teamToUpdate);

        Teams updatedTeam = (Teams)teamDao.getById(1);
        assertEquals(teamToUpdate , updatedTeam);
    }

    /**
     * Insert.
     */
    @Test
    void insert() {
        Teams team = new Teams("test", "test");
        int insertedTeamId = teamDao.insert(team);
        assertNotEquals(0, insertedTeamId);
        Teams insertedTeam = (Teams)teamDao.getById(insertedTeamId);
        assertEquals(team, insertedTeam);
    }

    /**
     * Delete.
     */
    /*
    @Test
    void delete() {

        teamDao = new GenericDao(Teams.class);
        teamRaceDao = new GenericDao(Category.class);

        teamDao.delete(teamDao.getById(5));
        assertNull(teamDao.getById(5));

        teamRaceDao.delete(teamRaceDao.getById(3));
        assertNull(teamRaceDao.getById(3));

    }
*/
    /**
     * Gets all.
     */
    @Test
    void getAll() {

        List<Teams> races = teamDao.getAll();
        assertEquals(32, races.size());
    }

    /**
     * This test's purpose is to ensure that the property has the
     * matching value. In this case "Joe" is a first name in
     * the table
     */
    @Test
    void getByPropertyEqual() {

        List<Teams> teams = teamDao.findByPropertyEqual("name", "Bear");
        assertEquals(1, teams.size());
        assertEquals(5, teams.get(0).getId());
    }
}

