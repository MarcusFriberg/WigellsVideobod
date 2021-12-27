package org.edusystems.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

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
    private int length;

    @Column(name = "replacement_cost")
    private double replacementCost;

    //@Enumerated(EnumType.STRING)
    //private Rating rating;

    //public enum Rating { G, PG, PG-13, R, NC-17 }
    //@Column(name = "rating")
    //private enum rating{'G' = ("G"), };

    //@Column(name = "special_features")
    //Detta är ett set

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

    public void setLength(int length) {
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

    public int getLength() {
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

