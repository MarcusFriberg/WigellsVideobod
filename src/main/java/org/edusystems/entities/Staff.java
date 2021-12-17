package org.edusystems.entities;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "staff")
public class Staff {
    @Id
    @Column(name = "staff_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int staffId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "address_id")
    private int addressId;

    @Column(name = "picture")
    private byte[] picture;

    @Column(name = "email")
    private String eMail;

    @Column(name = "store_id")
    private int storeId;

    @Column(name = "active")
    private int active;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "last_update")
    private Date lastUpdate;

    public Staff(String firstName, String lastName, int addressId, byte[] picture, String eMail, int storeId, int active, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.addressId = addressId;
        this.picture = picture;
        this.eMail = eMail;
        this.storeId = storeId;
        this.active = active;
        this.username = username;
        this.password = password;
    }

    public Staff() {

    }


    // Setters
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Getters
    public int getStaffId() {
        return staffId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAddressId() {
        return addressId;
    }

    public byte[] getPicture() {
        return picture;
    }

    public String geteMail() {
        return eMail;
    }

    public int getStoreId() {
        return storeId;
    }

    public int getActive() {
        return active;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }
}

