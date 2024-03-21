package edu.matc.entity;

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
public class Teams {

    @Column(name = "name")
    private String name;
    private long id;
    private Set<TeamRaces> teamRaces = new HashSet<TeamRaces>();

    /**
     * Instantiates a new Race.
     */
    public Teams() {
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    public long getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Instantiates a new Race.
     */
    public Teams(String name) {

        this.name = name;
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
     * Gets team races.
     *
     * @return the team races
     */
    @OneToMany(mappedBy = "teams")
    public Set<TeamRaces> getTeamRaces() {
        return teamRaces;
    }

    /**
     * Sets team races.
     *
     * @param teamRaces the team races
     */
    public void setTeamRaces(Set<TeamRaces> teamRaces) {
        this.teamRaces = teamRaces;
    }

    /**
     * Add team race.
     *
     * @param teamRace the team race
     */
    public void addTeamRace(TeamRaces teamRace) {

        this.teamRaces.add(teamRace);
    }

    @Override
    public String toString() {
        return "Teams{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teams teams = (Teams) o;
        return id == teams.id && Objects.equals(name, teams.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id);
    }
}