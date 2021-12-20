package org.edusystems.entities;
// Imports
import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @Column(name = "customer_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customerId;

    @Column(name = "store_id")
    private int storeId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "address_id")
    private String addressId;

    @Column(name = "active")
    private int active;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "last_update")
    private Date lastUpdate;

    public Customer() {
    }

    public Customer(int storeId, String firstName, String lastName, String email, String addressId, int active) {
        this.storeId = storeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.addressId = addressId;
        this.active = active;
    }

    //Setters
    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public void setActive(int active) {
        this.active = active;
    }

    //Getters

    public int getCustomerId() {
        return customerId;
    }

    public int getStoreId() {
        return storeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getAddressId() {
        return addressId;
    }

    public int getActive() {
        return active;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }
}
