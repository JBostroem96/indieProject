package edu.matc.persistence;
import com.mysql.cj.Session;
import edu.matc.entity.Race;
import edu.matc.entity.TeamRaces;
import edu.matc.entity.Teams;
import edu.matc.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 * This class' purpose is to test the functionality of this application
 */
class TeamRacesTest {

    private GenericDao teamDao;
    private GenericDao teamRaceDao;

    private GenericDao raceDao;
    /**
     * Triggers before everything else
     */
    @BeforeEach
    void setUp() {


    }


    /**
     * Gets by id.
     */
    @Test
    void getById() {


    }

    /**
     * Update.
     */
    @Test
    void update() {


    }

    /**
     * Insert.
     */
    @Test
    void insert() {

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


    }

    /**
     * This test's purpose is to ensure that the property has the
     * matching value. In this case "Joe" is a first name in
     * the table
     */
    @Test
    void getByPropertyEqual() {


    }
}
