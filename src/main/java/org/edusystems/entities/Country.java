package org.edusystems.entities;
// Imports
import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "country")
public class Country {
    @Id
    @Column(name = "country_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int countryId;

    @Column(name = "country")
    private String country;

    @Column(name = "last_update")
    private Date lastUpdate;

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
    public int getCountryId() {
        return countryId;
    }

    public String getCountry() {
        return country;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }
}
