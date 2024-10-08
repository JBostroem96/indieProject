package edu.matc.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.Objects;
import java.util.Set;


/**
 * The type Category.
 */
@Entity
@Table(name = "category")
public class Category {

    private int category_id;
    private Division division;
    private Set<Team> teams;

    /**
     * Instantiates a new Category.
     */
    public Category() {

    }

    /**
     * Instantiates a new Category.
     *
     * @param division the division
     */
    public Category(Division division) {
        this.division = division;
    }

    /**
     * Gets category id.
     *
     * @return the category id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    public int getCategory_id() {
        return category_id;
    }

    /**
     * Sets category id.
     *
     * @param id the id
     */
    public void setCategory_id(int id) {
        this.category_id = id;
    }

    /**
     * Gets teams.
     *
     * @return the teams
     */
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    public Set<Team> getTeams() {
        return teams;
    }

    /**
     * Sets teams.
     *
     * @param teams the teams
     */
    public void setTeams(Set<Team> teams) {
        this.teams = teams;
    }


    /**
     * Gets division.
     *
     * @return the division
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "division")
    public Division getDivision() {
        return division;
    }

    /**
     * Sets division.
     *
     * @param division the division
     */
    public void setDivision(Division division) {
        this.division = division;
    }

    /**
     * Add team.
     *
     * @param team the team
     */
    public void addTeam(Team team) {

        this.teams.add(team);
    }

    @Override
    public String toString() {
        return "Category{" +
                "category_id=" + category_id +
                ", division='" + division + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return category_id == category.category_id && Objects.equals(division, category.division);
    }

    @Override
    public int hashCode() {
        return Objects.hash(category_id, division);
    }
}
