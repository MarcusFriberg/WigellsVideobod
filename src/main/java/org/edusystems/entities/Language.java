package org.edusystems.entities;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "language")
public class Language {
    @Id
    @Column(name = "language_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private short languageId;

    @Column(name = "name")
    private String name;

    @Column(name = "last_update")
    private Timestamp lastUpdate;

    // Constructor
    public Language(String name) {
        this.name = name;
    }
    public Language() {
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    // Getters
    public int getLanguageId() {
        return languageId;
    }

    public String getName() {
        return name;
    }

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }
}

