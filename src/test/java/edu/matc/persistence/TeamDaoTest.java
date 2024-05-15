package edu.matc.persistence;


import edu.matc.entity.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import edu.matc.testUtil.Database;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TeamDaoTest {

    private GenericDao teamDao;

    /**
     * Triggers before everything else
     */
    @BeforeEach
    void setUp() {

        Database database = Database.getInstance();
        database.runSQL("clean_DB.sql");
        teamDao = new GenericDao(Team.class);
    }


    /**
     * Gets by id.
     */
    @Test
    void getById() {

        Team retrievedTeam = (Team)teamDao.getById(1);
        assertNotNull(retrievedTeam);
        assertEquals(teamDao.getById(1), retrievedTeam);
    }

    /**
     * Update.
     */
    @Test
    void update() {

        Team team = new Team("test", "test");

        Team teamToUpdate = (Team)teamDao.getById(1);

        teamToUpdate.setName(team.getName());
        teamToUpdate.setName(team.getDivision());

        teamDao.update(teamToUpdate);

        Team updatedTeam = (Team)teamDao.getById(1);
        assertEquals(teamToUpdate , updatedTeam);
    }

    /**
     * Insert.
     */
    @Test
    void insert() {
        Team team = new Team("test", "test");
        int insertedTeamId = teamDao.insert(team);
        assertNotEquals(0, insertedTeamId);
        Team insertedTeam = (Team)teamDao.getById(insertedTeamId);
        assertEquals(team, insertedTeam);
    }

    /**
     * Delete.
     */

    @Test
    void delete() {

        teamDao = new GenericDao(Team.class);
        teamDao.delete(teamDao.getById(10));
        assertNull(teamDao.getById(10));

    }

    /**
     * Gets all.
     */
    @Test
    void getAll() {

        List<Team> races = teamDao.getAll();
        assertEquals(32, races.size());
    }

    /**
     * This test's purpose is to ensure that the property has the
     * matching value.
     */
    @Test
    void getByPropertyEqual() {

        List<Team> teams = teamDao.findByPropertyEqual("name", "Bear");
        assertEquals(1, teams.size());
        assertEquals(5, teams.get(0).getId());
    }
}

