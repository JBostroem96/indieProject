package matc.persistence;
import edu.matc.persistence.Database;
import edu.matc.persistence.UserDao;
import edu.matc.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class UserDaoTest {

    private UserDao userDao;

    @BeforeEach
    void setUp() {

        Database database = Database.getInstance();
        database.runSQL("cleanDB.sql");
        userDao = new UserDao();
    }
    @Test
    void getById() {

        User retrievedUser = userDao.getById(1);
        assertNotNull(retrievedUser);
        assertEquals(userDao.getById(1), retrievedUser);
    }

    @Test
    void update() {

        String firstName = "Jon";
        String lastName = "Janson";
        String userName = "Jon";

        User userToUpdate = userDao.getById(1);

        userToUpdate.setUserName(userName);
        userToUpdate.setFirstName(firstName);
        userToUpdate.setLastName(lastName);

        userDao.update(userToUpdate);

        User updatedUser = userDao.getById(1);
        assertEquals(userToUpdate, updatedUser);
    }

    @Test
    void insert() {
        User user = new User("Anton", "Larson", "Anton", "example@hotmail.com", LocalDate.now());
        int insertedUserId = userDao.insert(user);
        assertNotEquals(0, insertedUserId);
        User insertedUser = userDao.getById(insertedUserId);
        assertEquals(user, insertedUser);
    }

    @Test
    void delete() {
        userDao = new UserDao();
        userDao.delete(userDao.getById(2));
        assertNull(userDao.getById(2));

    }

    @Test
    void deleteWithRaces() {


    }

    @Test
    void getAll() {

        List<User> users = userDao.getAll();
        assertEquals(6, users.size());
    }

    @Test
    void getByPropertyEqual() {
    }

    @Test
    void getByPropertyLike() {
    }
}