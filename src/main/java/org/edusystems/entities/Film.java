package org.edusystems.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "film")
public class Film implements Serializable {
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

    @Column(name = "language_id")
    private int languageId;

    @Column(name = "original_language_id")
    private int originalLanguageId;

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
    private Date lastUpdate;

    // Constructor
    public Film() {
    }


    // Setters


    // Getters





}

