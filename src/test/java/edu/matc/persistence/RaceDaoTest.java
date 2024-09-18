package edu.matc.persistence;
import edu.matc.entity.Race;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.List;
import edu.matc.testUtil.Database;
import static org.junit.jupiter.api.Assertions.*;

/**
 * This class' purpose is to test the functionality of this application
 */
class RaceDaoTest {

    private GenericDao dao;

    /**
     * Triggers before everything else
     */
    @BeforeEach
    void setUp() {

        Database database = Database.getInstance();
        database.runSQL("clean_DB.sql");
        dao = new GenericDao(Race.class);
    }

    /**
     * Gets by id.
     */
    @Test
    void getById() {

        Race retrievedRace = (Race)dao.getById(1);
        assertNotNull(retrievedRace);
        assertEquals(dao.getById(1), retrievedRace);
    }

    /**
     * Update.
     */
    @Test
    void update() {

        LocalDate date = LocalDate.now();
        String length = "35";
        String name = "Example2";

        Race raceToUpdate = (Race)dao.getById(1);

        raceToUpdate.setName(name);
        raceToUpdate.setLength(length);
        raceToUpdate.setDate(date);

        dao.update(raceToUpdate);

        Race updatedRace = (Race)dao.getById(1);
        assertEquals(raceToUpdate, updatedRace);
    }

    /**
     * Insert.
     */
    @Test
    void insert() {
        Race race = new Race("Example3", "22", LocalDate.now());
        int insertedRaceId = dao.insert(race);
        assertNotEquals(0, insertedRaceId);
        Race insertedRace = (Race)dao.getById(insertedRaceId);
        assertEquals(race, insertedRace);
    }


    @Test
    void delete() {

        dao = new GenericDao(Race.class);
        dao.delete(dao.getById(1));
        assertNull(dao.getById(1));
    }

    /**
     * Gets all.
     */
    @Test
    void getAll() {

        List<Race> races = dao.getAll();
        assertEquals(2, races.size());
    }

    /**
     * This test's purpose is to ensure that the property has the
     * matching value.
     */
    @Test
    void getByPropertyEqual() {

        List<Race> races = dao.findByPropertyEqual("name", "The Runship");
        assertEquals(1, races.size());
        assertEquals(2, races.get(0).getId());
    }
}