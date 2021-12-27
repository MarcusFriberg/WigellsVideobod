package org.edusystems.entities;
// Imports
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
 * @author: Marcus Friberg
 * @author: marcus.friberg@edu.edugrade.se
 * @version: 1.1
 */
@Entity
@Table(name = "rental")
public class Rental {
    // Variables
    @Id
    @Column(name = "rental_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short rentalId;

    @Column(name = "rental_date")
    private Date rentalDate;

    @ManyToOne
    @JoinColumn(name = "inventory_id")
    private Inventory inventoryItem;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(name = "return_date")
    private Timestamp returnDate;

    @Column(name = "staff_id")
    private Short staffId;

    @Column(name = "last_update")
    private Timestamp lastUpdate;



    //Constructor
    public Rental(Date rental_date, Inventory inventoryItem, Customer customer, Timestamp return_date, Short staff_id) {
        this.rentalDate = rental_date;
        this.inventoryItem = inventoryItem;
        this.customer = customer;
        this.returnDate = return_date;
        this.staffId = staff_id;
    }

    public Rental() {
    }

    //Setters
    public void setRentalDate(Date rental_date) {
        this.rentalDate = rental_date;
    }
    public void setReturnDate(Timestamp return_date) {
        this.returnDate = return_date;
    }
    public void setStaffId(Short staff_id) {
        this.staffId = staff_id;
    }
    public void setInventoryItem(Inventory inventoryItem) {
        this.inventoryItem = inventoryItem;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    //Getters
    public Short getRentalId() {
        return rentalId;
    }
    public Date getRentalDate() {
        return rentalDate;
    }
    public Inventory getInventoryItem() {
        return inventoryItem;
    }
    public Customer getCustomer() {
        return customer;
    }
    public Timestamp getReturnDate() {
        return returnDate;
    }
    public Short getStaffId() {
        return staffId;
    }
    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public String getCustomerName() {
        return customer.getFirstName() + " " + customer.getLastName();
    }

    public String getFilmTitle() {
        return inventoryItem.getInventoryFilm().getTitle();
    }

}