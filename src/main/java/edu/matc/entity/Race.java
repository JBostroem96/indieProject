package edu.matc.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Objects;
import java.time.LocalDate;

@Entity
@Table(name = "race")
public class Race {

    @Column(name = "name")
    private String name;
    @Column(name = "length")
    private String length;
    @Column(name = "date")
    private LocalDate date;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;

    /**
     * Instantiates a new Race.
     */
    public Race() {
    }

    public Race(String name, String length, LocalDate date) {
        this.name = name;
        this.length = length;
        this.date = date;
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

    @Override
    public String toString() {
        return "Race{" +
                "name='" + name + '\'' +
                ", length='" + length + '\'' +
                ", date=" + date +
                '}';
    }
}
