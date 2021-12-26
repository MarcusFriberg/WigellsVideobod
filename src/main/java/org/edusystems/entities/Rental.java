package org.edusystems.entities;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
/*
 * Class Rentals
 * Class that can be used to create rental-objects from. With entites from Hibernate.
 * @author: Linda Djurström
 * @author: linda.djurstrom@edu.edugrade.se
 * @version: 1.0.
 */
//Enteties
@Entity
@Table(name = "rental")
public class Rental {
    @Id
    @Column(name = "rental_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short rental_id;

    @Column(name = "rental_date")
    private Timestamp rental_date;

    @Column(name = "inventory_id")
    private Short inventory_id;

    @Column(name = "customer_id")
    private Short customer_id;

    @Column(name = "return_date")
    private Timestamp return_date;

    @Column(name = "staff_id")
    private Short staff_id;

    @Column(name = "last_update")
    private Timestamp last_update;

    //Constructor
    public Rental(Timestamp rental_date, Short inventory_id, Short customer_id, Timestamp return_date, Short staff_id) {
        this.rental_date = rental_date;
        this.inventory_id = inventory_id;
        this.customer_id = customer_id;
        this.return_date = return_date;
        this.staff_id = staff_id;
    }

    public Rental() {
    }

    //Setters
    public void setRental_date(Timestamp rental_date) {
        this.rental_date = rental_date;
    }
    public void setInventory_id(Short inventory_id) {
        this.inventory_id = inventory_id;
    }
    public void setCustomer_id(Short customer_id) {
        this.customer_id = customer_id;
    }
    public void setReturn_date(Timestamp return_date) {
        this.return_date = return_date;
    }
    public void setStaff_id(Short staff_id) {
        this.staff_id = staff_id;
    }

    //Getter
    public Timestamp getRental_date() {
        return rental_date;
    }
    public Short getInventory_id() {
        return inventory_id;
    }
    public Short getCustomer_id() {
        return customer_id;
    }
    public Timestamp getReturn_date() {
        return return_date;
    }
    public Short getStaff_id() {
        return staff_id;
    }
}