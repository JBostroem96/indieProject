package edu.matc.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.*;


/**
 * This class' purpose is to be the Javabean for User
 */
@Entity
@Table(name = "user")
public class User {

    @Enumerated(EnumType.STRING)
    private Role role;
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

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TeamRace> teamRaces = new ArrayList<>();

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
    public User(String name, String userName, String email, Role role) {
        this.name = name;
        this.email = email;
        this.userName = userName;
        this.role = role;
    }

    /**
     * Gets role.
     *
     * @return the role
     */
    @Column(name = "role")
    public Role getRole() {
        return role;
    }

    /**
     * Sets role.
     *
     * @param role the role
     */
    public void setRole(Role role) {
        this.role = role;
    }


    /**
     * Gets team races.
     *
     * @return the team races
     */
    public List<TeamRace> getTeamRaces() {
        return teamRaces;
    }

    /**
     * Sets team races.
     *
     * @param teamRaces the team races
     */
    public void setTeamRaces(List<TeamRace> teamRaces) {
        this.teamRaces = teamRaces;
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

    @Override
    public String toString() {
        return "User{" +
                "role='" + role + '\'' +
                ", name='" + name + '\'' +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", id=" + id +
                '}';
    }

    /**
     * Overrides the toString method
     * @return the first name, last name, username, id, date of birth, and age
     */


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(role, user.role) && Objects.equals(name, user.name) && Objects.equals(userName, user.userName) && Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(role, name, userName, email, id);
    }
}