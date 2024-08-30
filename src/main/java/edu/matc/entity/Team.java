package edu.matc.entity;

import edu.matc.util.Validation;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * This class' purpose is to be the Javabean for race
 */
@Entity
@Table(name = "teams")
public class Team implements Validation {

    private String name;
    private int id;
    private String division;
    private Category category;
    private Set<TeamRace> teamRaces = new HashSet<TeamRace>();

    /**
     * Instantiates a new Team.
     */
    public Team() {
    }

    /**
     * Instantiates a new Team.
     */
    public Team(String name) {

        this.name = name;
    }

    /**
     * Instantiates a new Team.
     *
     * @param name     the name
     * @param category the category
     * @param division the division
     */
    public Team(String name, Category category, String division) {
        this.name = name;
        this.category = category;
        this.division = division;
    }

    /**
     * Instantiates a new Team.
     *
     * @param name     the name
     * @param division the division
     */
    public Team(String name, String division) {
        this.name = name;
        this.division = division;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "team_id")
    public int getId() {
        return id;
    }

    /**
     * Gets category.
     *
     * @return the category
     */
    //Needs to be looked at more
    @ManyToOne
    @JoinColumn(name = "category_id")
    public Category getCategory() {
        return category;
    }

    /**
     * Sets category.
     *
     * @param category the category
     */
    public void setCategory(Category category) {
        this.category = category;
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
     * Gets name.
     *
     * @return the name
     */
    @Override
    @Column(name = "name")
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
     * Gets division.
     *
     * @return the division
     */
    @Column(name = "division")
    public String getDivision() {
        return division;
    }

    /**
     * Sets division.
     *
     * @param division the division
     */
    public void setDivision(String division) {
        this.division = division;
    }

    /**
     * Gets team races.
     *
     * @return the team races
     */
    @OneToMany(mappedBy = "team")
    public Set<TeamRace> getTeamRaces() {
        return teamRaces;
    }

    /**
     * Sets team races.
     *
     * @param teamRaces the team races
     */
    public void setTeamRaces(Set<TeamRace> teamRaces) {
        this.teamRaces = teamRaces;
    }

    /**
     * Add team race.
     *
     * @param teamRace the team race
     */
    public void addTeamRace(TeamRace teamRace) {

        this.teamRaces.add(teamRace);
    }

    @Override
    public String toString() {
        return "Team{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", division='" + division + '\'' +
                ", category=" + category +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return id == team.id && Objects.equals(name, team.name) && Objects.equals(division, team.division) && Objects.equals(category, team.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id, division, category);
    }
}