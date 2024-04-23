package edu.matc.entity;
import edu.matc.persistence.GenericDao;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.time.LocalDate;

/**
 * This class' purpose is to be the Javabean for User
 */
@Entity
@Table(name = "user")
public class User {

    @Column(name = "name")
    private String name;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "email")
    private String email;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;

    /**
     * Instantiates a new User.
     */
    public User() {
    }

    /**
     * Instantiates a new User.
     *
     * @param userName the first name
     * @param name the name
     * @param email       the email
     */
    public User(String name, String userName, String email) {
        this.name = name;
        this.email = email;
        this.userName = userName;
    }

    /**
     * Add user.
     * This method's purpose is to add a user if they don't already exist
     * @param user the user
     * @return the user
     */
    public User addUser(User user) {

        GenericDao dao = new GenericDao(User.class);
        List<User> users = dao.getAll();

        String email = null;
        String userName = null;

        for (User existingUsers : users) {

            email = existingUsers.getEmail();
            userName = existingUsers.getUserName();
        }
        if (!email.equals(user.getEmail()) || !userName.equals(user.getUserName())) {
            dao.insert(user);
        }
        return user;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets username.
     *
     * @return the username
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets username.
     *
     * @param userName the username
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Overrides the toString method
     * @return the first name, last name, username, id, date of birth, and age
     */
    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", userName='" + userName + '\'' +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(name, user.name) && Objects.equals(userName, user.userName) && Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, userName, email, id);
    }
}