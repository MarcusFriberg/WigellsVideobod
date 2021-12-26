package org.edusystems.entities;
// Imports
import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

@Entity
@Table(name = "city")
public class City {
    @Id
    @Column(name = "city_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short cityId;

    @Column(name = "city")
    private String city;

    @Column(name = "country_id")
    private Short country_id;

    @Column(name = "last_update")
    private Timestamp lastUpdate;

    public City() {
    }

    //Constructor
    public City(String city, Short country) {
        this.city = city;
        this.country_id = country;
    }

    //Setters
    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry_id(Short country_id) {
        this.country_id = country_id;
    }

    //Getters
    public Short getCityId() {
        return cityId;
    }

    public String getCity() {
        return city;
    }

    public Short getCountry_id() {
        return country_id;
    }

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
