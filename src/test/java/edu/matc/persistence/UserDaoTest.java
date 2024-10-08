package edu.matc.persistence;
import edu.matc.entity.Role;
import edu.matc.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import edu.matc.testUtil.Database;
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
        database.runSQL("clean_DB.sql");
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

        User user = new User("Test", "Example", "example@hotmail.com", Role.ADMIN);

        User userToUpdate = (User)userDao.getById(1);

        userToUpdate.setUserName(user.getUserName());
        userToUpdate.setName(user.getName());
        userToUpdate.setRole(user.getRole());
        userToUpdate.setEmail(user.getEmail());

        userDao.update(userToUpdate);

        User updatedUser = (User)userDao.getById(1);
        assertEquals(userToUpdate, updatedUser);
    }

    /**
     * Insert.
     */
    @Test
    void insert() {
        User user = new User("Anton", "Anton", "example@hotmail.com", Role.ADMIN);
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
        userDao.delete(userDao.getById(1));
        assertNull(userDao.getById(1));

    }


    /**
     * Gets all.
     */
    @Test
    void getAll() {

        List<User> users = userDao.getAll();
        assertEquals(2, users.size());
    }

    /**
     * This test's purpose is to ensure that the property has the
     * matching value.
     */
    @Test
    void getByPropertyEqual() {

        List<User> users = userDao.findByPropertyEqual("name", "jb");
        assertEquals(1, users.size());
        assertEquals(1, users.get(0).getId());
    }
}