package edu.matc.persistence;
import edu.matc.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 * This class' purpose is to test the functionality of this application
 */
class UserDaoTest {

    private GenericDao userDao;

    /**
     * Triggers before everything else
     */
    @BeforeEach
    void setUp() {

        Database database = Database.getInstance();
        database.runSQL("cleanDB.sql");
        userDao = new GenericDao(User.class);
    }


    /**
     * Gets by id.
     */
    @Test
    void getById() {

        User retrievedUser = (User)userDao.getById(1);
        assertNotNull(retrievedUser);
        assertEquals(userDao.getById(1), retrievedUser);
    }

    /**
     * Update.
     */
    @Test
    void update() {

        String firstName = "Jon";
        String lastName = "Janson";
        String userName = "Jon";

        User userToUpdate = (User)userDao.getById(1);

        userToUpdate.setUserName(userName);
        userToUpdate.setFirstName(firstName);
        userToUpdate.setLastName(lastName);

        userDao.update(userToUpdate);

        User updatedUser = (User)userDao.getById(1);
        assertEquals(userToUpdate, updatedUser);
    }

    /**
     * Insert.
     */
    @Test
    void insert() {
        User user = new User("Anton", "Larson", "Anton", "example@hotmail.com", "34324", LocalDate.now());
        int insertedUserId = userDao.insert(user);
        assertNotEquals(0, insertedUserId);
        User insertedUser = (User)userDao.getById(insertedUserId);
        assertEquals(user, insertedUser);
    }

    /**
     * Delete.
     */
    @Test
    void delete() {
        userDao = new GenericDao(User.class);
        userDao.delete(userDao.getById(2));
        assertNull(userDao.getById(2));

    }

    /**
     * Delete with races.
     */
    @Test
    void deleteWithRaces() {


    }

    /**
     * Gets all.
     */
    @Test
    void getAll() {

        List<User> users = userDao.getAll();
        assertEquals(6, users.size());
    }

    /**
     * This test's purpose is to ensure that the property has the
     * matching value. In this case "Joe" is a first name in
     * the table
     */
    @Test
    void getByPropertyEqual() {

        List<User> users = userDao.findByPropertyEqual("firstName", "Joe");
        assertEquals(1, users.size());
        assertEquals(1, users.get(0).getId());
    }
}