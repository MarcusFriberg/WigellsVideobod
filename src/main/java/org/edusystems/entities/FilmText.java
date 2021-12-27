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
@Table(name = "film_text")
public class FilmText {
        @Id
        @Column(name = "film_id")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int filmId;

        @Column(name = "title")
        private int title;

        @Column(name = "last_update")
        private String description;
    // Empty constructor
    public FilmText() {
    }
    // Constructor
    public FilmText(int title, String description) {
        this.title = title;
        this.description = description;
    }
    // Setters
    public void setTitle(int title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    // Getters
    public int getFilmId() {
        return filmId;
    }

    public int getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
