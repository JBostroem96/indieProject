package edu.matc.entity;

import edu.matc.util.Validation;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.time.LocalDate;
import java.util.Set;

/**
 * This class' purpose is to be the Javabean for race
 */
@Entity
@Table(name = "race")
public class Race implements Validation {

    @Column(name = "name")
    private String name;
    @Column(name = "length")
    private String length;
    @Column(name = "date")
    private LocalDate date;
    private int id;
    private Set<TeamRace> teamRaces = new HashSet<TeamRace>();

    /**
     * Instantiates a new Race.
     */
    public Race() {
    }

    /**
     * Instantiates a new Race.
     *
     * @param name   the name
     * @param length the length
     * @param date   the date
     */
    public Race(String name, String length, LocalDate date) {
        this.name = name;
        this.length = length;
        this.date = date;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "race_id")
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
     * Gets team races.
     *
     * @return the team races
     */
    @OneToMany(mappedBy = "race", fetch = FetchType.EAGER)
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

    /**
     * Gets name.
     *
     * @return the name
     */
    @Override
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
     * Gets length.
     *
     * @return the length
     */
    public String getLength() {
        return length;
    }

    /**
     * Sets length.
     *
     * @param length the length
     */
    public void setLength(String length) {
        this.length = length;
    }

    /**
     * Gets date.
     *
     * @return the date
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Sets date.
     *
     * @param date the date
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Add team.
     *
     * @param team the team
     */
    public void addTeam(Team team) {

        TeamRace teamRace = new TeamRace(team, this);
        this.teamRaces.add(teamRace);
        team.getTeamRaces().add(teamRace);
    }

    /**
     * Remove team.
     *
     * @param team the team
     */
    public void removeTeam(Team team) {

        for (Iterator<TeamRace> iterator = teamRaces.iterator();
             iterator.hasNext();) {

            TeamRace teamRace = iterator.next();

            if (teamRace.getTeam().equals(this) && teamRace.getRace().equals(team)) {

              iterator.remove();
              teamRace.getTeam().getTeamRaces().remove(teamRace);
              teamRace.setTeam(null);
              teamRace.setRace(null);

            }
        }
    }
    @Override
    public String toString() {
        return "Race{" +
                "name='" + name + '\'' +
                ", length='" + length + '\'' +
                ", date=" + date +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Race race = (Race) o;
        return id == race.id && Objects.equals(name, race.name) && Objects.equals(length, race.length) && Objects.equals(date, race.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, length, date, id);
    }
}
