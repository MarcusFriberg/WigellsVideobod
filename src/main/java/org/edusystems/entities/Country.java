package org.edusystems.entities;
// Imports
import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

@Entity
@Table(name = "country")
public class Country {
    //Enteties
    @Id
    @Column(name = "country_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short countryId;

    @Column(name = "country")
    private String country;

    @Column(name = "last_update")
    private Timestamp lastUpdate;

    //Constructors
    public Country() {
    }

    public Country(String country) {
        this.country = country;
    }

    //Setters
    public void setCountry(String country) {
        this.country = country;
    }

    //Getters
    public Short getCountryId() {
        return countryId;
    }

    public String getCountry() {
        return country;
    }

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}


