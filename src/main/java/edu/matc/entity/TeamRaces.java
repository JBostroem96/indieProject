package edu.matc.entity;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import java.util.Objects;

@Entity
@Table(name = "team_races")
public class TeamRaces {

    private int id;
    private int total_time;
    private Teams team;
    private Race race;
    private int division_place;
    private int cp;
    private int late_penalty;
    private int overall_place;

    /**
     * Instantiates a new Team races.
     */
    public TeamRaces() {
    }

    public TeamRaces(Teams team, Race race) {
        this.team = team;
        this.race = race;
    }

    public TeamRaces(Teams team, Race race, int cp, int late_penalty) {
        this.team = team;
        this.race = race;
        this.cp = cp;
        this.late_penalty = late_penalty;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "teams_race_PK")
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
     * Gets team.
     *
     * @return the team
     */

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "team_id")
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
        return race;
    }

    /**
     * Sets race.
     *
     * @param race the race
     */
    public void setRace(Race race) {
        this.race = race;
    }

    /**
     * Gets division place.
     *
     * @return the division place
     */
    @Column(name = "division_place")
    public int getDivision_place() {
        return division_place;
    }

    /**
     * Sets division place.
     *
     * @param divisionPlace the division place
     */
    public void setDivision_place(int divisionPlace) {
        this.division_place = divisionPlace;
    }

    @Column(name = "total_time")
    public int getTotal_time() {
        return total_time;
    }

    public void setTotal_time(int total_time) {
        this.total_time = total_time;
    }

    /**
     * Gets cp.
     *
     * @return the cp
     */
    @Column(name = "cp")
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
    @Column(name = "overall_place")
    public int getOverall_place() {
        return overall_place;
    }

    /**
     * Sets overall place.
     *
     * @param overallPlace the overall place
     */
    public void setOverall_place(int overallPlace) {
        this.overall_place = overallPlace;
    }

    /**
     * Gets late penalty.
     *
     * @return the late penalty
     */
    @Column(name = "late_penalty")
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
                "total_time=" + total_time +
                ", team=" + team +
                ", race=" + race +
                ", division_place=" + division_place +
                ", cp=" + cp +
                ", late_penalty=" + late_penalty +
                ", overall_place=" + overall_place +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeamRaces teamRaces = (TeamRaces) o;
        return id == teamRaces.id && division_place == teamRaces.division_place && cp == teamRaces.cp && late_penalty == teamRaces.late_penalty && overall_place == teamRaces.overall_place && Objects.equals(team, teamRaces.team) && Objects.equals(race, teamRaces.race);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, team, race, division_place, cp, late_penalty, overall_place);
    }
}
