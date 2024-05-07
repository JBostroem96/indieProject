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
public class TeamRace {

    private int id;
    private double totalTime;
    private Team team;
    private Race race;
    private int divisionPlace;
    private int cp;
    private int latePenalty;
    private int overallPlace;
    private int race_id;


    /**
     * Instantiates a new Team races.
     */
    public TeamRace() {
    }

    /**
     * Instantiates a new Team races.
     *
     * @param team the team
     * @param race the race
     */
    public TeamRace(Team team, Race race) {
        this.team = team;
        this.race = race;
    }

    /**
     * Instantiates a new Team races.
     *
     * @param team         the team
     * @param race         the race
     * @param cp           the cp
     * @param latePenalty the late penalty
     * @param totalTime   the total time
     */
    public TeamRace(Team team, Race race, int cp, int latePenalty, double totalTime) {
        this.team = team;
        this.race = race;
        this.cp = cp;
        this.latePenalty = latePenalty;
        this.totalTime = totalTime;
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
    @ManyToOne
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
    @ManyToOne
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
     * Gets total time.
     *
     * @return the total time
     */
    @Column(name = "total_time")
    public double getTotalTime() {
        return totalTime;
    }

    /**
     * Sets total time.
     *
     * @param totalTime the total time
     */
    public void setTotalTime(double totalTime) {
        this.totalTime = totalTime;
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
    @Column(name = "late_penalty")
    public int getLatePenalty() {
        return latePenalty;
    }

    /**
     * Sets late penalty.
     *
     * @param latePenalty the late penalty
     */
    public void setLatePenalty(int latePenalty) {
        this.latePenalty = latePenalty;
    }

    @Override
    public String toString() {
        return "TeamRace{" +
                "id=" + id +
                ", totalTime=" + totalTime +
                ", team=" + team +
                ", race=" + race +
                ", divisionPlace=" + divisionPlace +
                ", cp=" + cp +
                ", latePenalty=" + latePenalty +
                ", overallPlace=" + overallPlace +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeamRace teamRace = (TeamRace) o;
        return id == teamRace.id && Double.compare(totalTime, teamRace.totalTime) == 0 && divisionPlace == teamRace.divisionPlace && cp == teamRace.cp && latePenalty == teamRace.latePenalty && overallPlace == teamRace.overallPlace && Objects.equals(team, teamRace.team) && Objects.equals(race, teamRace.race);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, totalTime, team, race, divisionPlace, cp, latePenalty, overallPlace);
    }
}
