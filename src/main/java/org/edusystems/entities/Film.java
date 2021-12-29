package org.edusystems.entities;

// Imports
import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

/*
 * Class Film
 * Class that can be used to create film-objects from, with entites from Hibernate.
 * @author: Matilda Wintence
 * @author: matilda.wintence@edu.edugrade.se
 * @version: 1.0.
 */

// Entities
@Entity
@Table(name = "film")
public class Film {
    @Id
    @Column(name = "film_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int filmId;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "release_year")
    private Date releaseYear;

    @ManyToOne
    @JoinColumn(name = "language_id")
    private Language filmLanguage;

    @ManyToOne
    @JoinColumn(name = "original_language_id")
    private Language originalFilmLanguage;

    @Column(name = "rental_duration")
    private int rentalDuration;

    @Column(name = "rental_rate")
    private double rentalRate;

    @Column(name = "length")
    private short length;

    @Column(name = "replacement_cost")
    private double replacementCost;

    @Column(name = "last_update")
    private Timestamp lastUpdate;

    // Constructor
    public Film() {
    }


    // Setters
    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setReleaseYear(Date releaseYear) {
        this.releaseYear = releaseYear;
    }

    public void setRentalDuration(int rentalDuration) {
        this.rentalDuration = rentalDuration;
    }

    public void setRentalRate(double rentalRate) {
        this.rentalRate = rentalRate;
    }

    public void setLength(short length) {
        this.length = length;
    }

    public void setReplacementCost(double replacementCost) {
        this.replacementCost = replacementCost;
    }

    public void setFilmLanguage(Language filmLanguage) {
        this.filmLanguage = filmLanguage;
    }

    public void setOriginalFilmLanguage(Language originalFilmLanguage) {
        this.originalFilmLanguage = originalFilmLanguage;
    }

    // Getters
    public int getFilmId() {
        return filmId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Date getReleaseYear() {
        return releaseYear;
    }

    public int getRentalDuration() {
        return rentalDuration;
    }

    public double getRentalRate() {
        return rentalRate;
    }

    public short getLength() {
        return length;
    }

    public double getReplacementCost() {
        return replacementCost;
    }

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public Language getFilmLanguage() {
        return filmLanguage;
    }

    public Language getOriginalFilmLanguage() {
        return originalFilmLanguage;
    }
}

