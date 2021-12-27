package org.edusystems.entities;

// Imports
import javax.persistence.*;
import java.sql.Timestamp;

/*
 * Class FilmActor
 * Class that can be used to create filmActor-objects from, with entites from Hibernate.
 * @author: Matilda Wintence
 * @author: matilda.wintence@edu.edugrade.se
 * @version: 1.0.
 */

// Entities
@Entity
@Table(name = "film_actor")
public class FilmActor {
    @Column(name = "actor_id")
    private int actorId;

    @Id
    @Column (name = "film_id")
    private int filmId;

    @Column (name = "last_update")
    private Timestamp lastUpdate;

    // empty constructor
    public FilmActor() {
    }
    // Constructor
    public FilmActor(int actorId, int filmId) {
        this.actorId = actorId;
        this.filmId = filmId;
    }

    // Setters
    public void setActorId(int actorId) {
        this.actorId = actorId;
    }

    public void setFilmId(int filmId) {
        this.filmId = filmId;
    }

    // Getters
    public int getActorId() {
        return actorId;
    }

    public int getFilmId() {
        return filmId;
    }

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }
}

