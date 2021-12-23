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
    private int cityId;

    @Column(name = "city")
    private String city;

    @Column(name = "country_id")
    private int country_id;


    @Column(name = "last_update")
    private Timestamp lastUpdate;

    public City() {
    }

    public City(String city, int country) {
        this.city = city;
        this.country_id = country;
    }

    //Setters
    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry_id(int country) {
        this.country_id = country_id;
    }

    //Getters
    public int getCityId() {
        return cityId;
    }

    public String getCity() {
        return city;
    }

    public int getCountry_id() {
        return country_id;
    }

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
