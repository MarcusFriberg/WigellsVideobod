package org.edusystems.entities;
// Imports
import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "city")
public class City {
    @Id
    @Column(name = "city_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cityId;

    @Column(name = "city")
    private String city;

    @Column(name = "country")
    private String country;

    @Column(name = "last_update")
    private Date lastUpdate;

    public City() {
    }

    public City(String city, String country) {
        this.city = city;
        this.country = country;
    }

    //Setters
    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    //Getters
    public int getCityId() {
        return cityId;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }
}
