package edu.matc.entity;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import java.util.Objects;

/**
 * The type Team races.
 */
@Entity
@Table(name = "team_races")
public class TeamRaces {

    private int id;
    private double total_time;
    private Team team;
    private Race race;
    private int division_place;
    private int cp;
    private int late_penalty;
    private int overall_place;
    private int race_id;


    /**
     * Instantiates a new Team races.
     */
    public TeamRaces() {
    }

    /**
     * Instantiates a new Team races.
     *
     * @param team the team
     * @param race the race
     */
    public TeamRaces(Team team, Race race) {
        this.team = team;
        this.race = race;
    }

    /**
     * Instantiates a new Team races.
     *
     * @param team         the team
     * @param race         the race
     * @param cp           the cp
     * @param late_penalty the late penalty
     * @param total_time   the total time
     */
    public TeamRaces(Team team, Race race, int cp, int late_penalty, int total_time) {
        this.team = team;
        this.race = race;
        this.cp = cp;
        this.late_penalty = late_penalty;
        this.total_time = total_time;
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
     * Gets race id.
     *
     * @return the race id
     */
    @Column(name = "race_id", insertable = false, updatable = false)
    public int getRace_id() {
        return race_id;
    }

    /**
     * Sets race id.
     *
     * @param race_id the race id
     */
    public void setRace_id(int race_id) {
        this.race_id = race_id;
    }

    /**
     * Gets team.
     *
     * @return the team
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "team_id")
    public Team getTeam() {
        return team;
    }

    /**
     * Sets team.
     *
     * @param team the team
     */
    public void setTeam(Team team) {
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

    /**
     * Gets total time.
     *
     * @return the total time
     */
    @Column(name = "total_time")
    public double getTotal_time() {
        return total_time;
    }

    /**
     * Sets total time.
     *
     * @param total_time the total time
     */
    public void setTotal_time(double total_time) {
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
