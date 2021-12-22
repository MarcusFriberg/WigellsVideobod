package org.edusystems.entities;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.*;
import java.sql.Date;


@Entity
@Table(name = "rental")
public class Rental {
    @Id
    @Column(name = "rental_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rental_id;

    @Column(name = "rental_date")
    private Date rental_date;

    @Column(name = "inventory_id")
    private int inventory_id;

    @Column(name = "customer_id")
    private int customer_id;

    @Column(name = "return_date")
    private Date return_date;

    @Column(name = "staff_id")
    private int staff_id;

    @Column(name = "last_update")
    private Date last_update;

    //Constructor


    public Rental(Date rental_date, int inventory_id, int customer_id, Date return_date, int staff_id) {
        this.rental_date = rental_date;
        this.inventory_id = inventory_id;
        this.customer_id = customer_id;
        this.return_date = return_date;
        this.staff_id = staff_id;
    }

    public Rental() {
    }

    //Setters
    public void setRental_date(Date rental_date) {
        this.rental_date = rental_date;
    }
    public void setInventory_id(int inventory_id) {
        this.inventory_id = inventory_id;
    }
    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }
    public void setReturn_date(Date return_date) {
        this.return_date = return_date;
    }
    public void setStaff_id(int staff_id) {
        this.staff_id = staff_id;
    }

    //Getter

    public Date getRental_date() {
        return rental_date;
    }
    public int getInventory_id() {
        return inventory_id;
    }
    public int getCustomer_id() {
        return customer_id;
    }
    public Date getReturn_date() {
        return return_date;
    }
    public int getStaff_id() {
        return staff_id;
    }
}