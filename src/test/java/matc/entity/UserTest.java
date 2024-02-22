package matc.entity;

import edu.matc.entity.User;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void getAge() {
        // create the object that has the method I want to test
        User userTest = new User();
        //set birthdate for the user
        LocalDate birthdate = LocalDate.parse("1968-01-01");
        userTest.setDateOfBirth(birthdate);
        // create variable for the expected value
        int expectedAge = 56;
        //Call the method, and get the actual value
        int actualAge = userTest.getAge();
        //Compare the two, pass or fail
        assertEquals(expectedAge, actualAge);
    }
}