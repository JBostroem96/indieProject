package matc.persistence;

import edu.matc.entity.Order;
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
        User user = new User("Anton", "Larson", "Anton", LocalDate.now());
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
    void deleteWithOrders() {
        //Create the userDao
        userDao = new UserDao();

        //Get the user we want to delete that has 2 orders associated
        User userToBeDeleted = userDao.getById(3);
        List<Order> orders = userToBeDeleted.getOrders();

        //Get the associated order numbers
        int orderNumber1 = orders.get(0).getId();
        int orderNumber2 = orders.get(1).getId();

        //Delete the user
        userDao.delete(userToBeDeleted);
        //Verify the user was deleted
        assertNull(userDao.getById(3));
        //Verify the order was also deleted
        OrderDao orderDao = new OrderDao();
        assertNull(orderDao.getById(orderNumber1));
        assertNull(orderDao.getById(orderNumber2));

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