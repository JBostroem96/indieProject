package edu.matc.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;


import java.util.ArrayList;
import java.util.Objects;
import java.util.Set;


/**
 * The type Category.
 */
@Entity
@Table(name = "category")
public class Category {

    private int category_id;
    private String division;
    private Set<Teams> teams;

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
    public Category(String division) {
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
    public Set<Teams> getTeams() {
        return teams;
    }

    /**
     * Sets teams.
     *
     * @param teams the teams
     */
    public void setTeams(Set<Teams> teams) {
        this.teams = teams;
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
     * Add team.
     *
     * @param team the team
     */
    public void addTeam(Teams team) {

        this.teams.add(team);
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
