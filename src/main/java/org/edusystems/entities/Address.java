package org.edusystems.entities;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.*;
import java.sql.Timestamp;
/*
 * Class Address.
 * Class that can be used to create address-objects from. With entities from Hibernate.
 * @author: Linda Djurström
 * @author: linda.djurstrom@edu.edugrade.se
 * @version: 1.0.
 */
//Entities
@Entity
@Table(name = "address")
public class Address {
    @Id
    @Column(name = "address_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short address_id;

    @Column(name = "address")
    private String address;

    @Column(name = "address2")
    private String address2;

    @Column(name = "district")
    private String district;

    @Column(name = "city_id")
    private Short city_id;

    @Column(name = "postal_code")
    private String postal_code;

    @Column(name = "phone")
    private String phone;

    @Column(name = "last_update")
    private Timestamp last_update;

    //Constructor
    public Address() {}
    public Address(String address, String address2, String district, Short city_id, String postal_code, String phone, Timestamp last_update) {
        this.address = address;
        this.address2 = address2;
        this.district = district;
        this.city_id = city_id;
        this.postal_code = postal_code;
        this.phone = phone;
        this.last_update = last_update;
    }

    //Setters
    public void setAddress(String address) {
        this.address = address;
    }
    public void setAddress2(String address2) {
        this.address2 = address2;
    }
    public void setDistrict(String district) {
        this.district = district;
    }
    public void setCity_id(Short city_id) {
        this.city_id = city_id;
    }
    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    //Getters

    public Short getAddress_id() {
        return address_id;
    }
    public String getAddress() {
        return address;
    }
    public String getAddress2() {
        return address2;
    }
    public String getDistrict() {
        return district;
    }
    public Short getCity_id() {
        return city_id;
    }
    public String getPostal_code() {
        return postal_code;
    }
    public String getPhone() {
        return phone;
    }
    public Timestamp getLast_update() {
        return last_update;
    }
    public void setLast_update(Timestamp last_update) {
        this.last_update = last_update;
    }
}