package edu.matc.entity;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import java.util.Objects;

@Entity
@Table(name = "teams_race")
public class TeamRaces {

    private long id;
    private Teams team;
    private Race Race;
    private int divisionPlace;
    private int cp;
    private int late_penalty;
    private int overallPlace;

    /**
     * Instantiates a new Team races.
     */
    public TeamRaces() {
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
     * Gets team.
     *
     * @return the team
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "teams_id")
    public Teams getTeam() {
        return team;
    }

    /**
     * Sets team.
     *
     * @param team the team
     */
    public void setTeam(Teams team) {
        this.team = team;
    }

    /**
     * Gets race.
     *
     * @return the race
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "race_id")
    public Race getRace() {
        return Race;
    }

    /**
     * Sets race.
     *
     * @param race the race
     */
    public void setRace(Race race) {
        Race = race;
    }

    /**
     * Gets division place.
     *
     * @return the division place
     */
    public int getDivisionPlace() {
        return divisionPlace;
    }

    /**
     * Sets division place.
     *
     * @param divisionPlace the division place
     */
    public void setDivisionPlace(int divisionPlace) {
        this.divisionPlace = divisionPlace;
    }

    /**
     * Gets cp.
     *
     * @return the cp
     */
    public int getCp() {
        return cp;
    }

    /**
     * Sets cp.
     *
     * @param cp the cp
     */
    public void setCp(int cp) {
        this.cp = cp;
    }

    /**
     * Gets overall place.
     *
     * @return the overall place
     */
    public int getOverallPlace() {
        return overallPlace;
    }

    /**
     * Sets overall place.
     *
     * @param overallPlace the overall place
     */
    public void setOverallPlace(int overallPlace) {
        this.overallPlace = overallPlace;
    }

    /**
     * Gets late penalty.
     *
     * @return the late penalty
     */
    public int getLate_penalty() {
        return late_penalty;
    }

    /**
     * Sets late penalty.
     *
     * @param late_penalty the late penalty
     */
    public void setLate_penalty(int late_penalty) {
        this.late_penalty = late_penalty;
    }

    @Override
    public String toString() {
        return "TeamRaces{" +
                "id=" + id +
                ", team=" + team +
                ", Race=" + Race +
                ", divisionPlace=" + divisionPlace +
                ", cp=" + cp +
                ", late_penalty=" + late_penalty +
                ", overallPlace=" + overallPlace +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeamRaces teamRaces = (TeamRaces) o;
        return id == teamRaces.id && divisionPlace == teamRaces.divisionPlace && cp == teamRaces.cp && late_penalty == teamRaces.late_penalty && overallPlace == teamRaces.overallPlace && Objects.equals(team, teamRaces.team) && Objects.equals(Race, teamRaces.Race);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, team, Race, divisionPlace, cp, late_penalty, overallPlace);
    }
}
