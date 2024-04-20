package edu.matc.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;
    private String division;

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
     * Gets division.
     *
     * @return the division
     */
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
}
