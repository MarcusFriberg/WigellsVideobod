package org.edusystems.entities;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "film_actor")
public class FilmActor {
    @Column(name = "actor_id")
    private int actorId;

    @Id
    @Column (name = "film_id")
    private int filmId;

    @Column (name = "last_update")
    private Date lastUpdate;

    // Constructor
    public FilmActor() {
    }

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

    public Date getLastUpdate() {
        return lastUpdate;
    }
}

