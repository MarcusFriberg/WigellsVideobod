package org.edusystems.entities;

// Imports
import javax.persistence.*;
import java.sql.Timestamp;

/*
 * Class FilmCategory
 * Class that can be used to create filmCategory-objects from, with entites from Hibernate.
 * @author: Matilda Wintence
 * @author: matilda.wintence@edu.edugrade.se
 * @version: 1.0.
 */
@Entity
@Table(name = "film_category")
public class FilmCategory {
        @Id
        @Column(name = "film_id")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int filmId;

        @Column (name = "category_id")
        private int categoryId;

        @Column(name = "last_update")
        private Timestamp lastUpdate;

    // Empty constructor
    public FilmCategory() {
    }

    // Constructor
    public FilmCategory(int categoryId) {
        this.categoryId = categoryId;
    }

    // Getters and setters
    public int getFilmId() {
        return filmId;
    }

    public void setFilmId(int filmId) {
        this.filmId = filmId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}



