package org.edusystems.entities;

// Imports
import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "store")
public class Store {
    @Id
    @Column(name = "store_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int storeId;

    @Column(name = "manager_staff_id")
    private int managerStaffId;

    @Column(name = "address_id")
    private int addressId;

    @Column(name = "last_update")
    private Date lastUpdate;



    public Store(int managerStaffId, int addressId) {
        this.managerStaffId = managerStaffId;
        this.addressId = addressId;
    }

    public Store() {

    }

    // Setters
    public void setManagerStaffId(int managerStaffId) {
        this.managerStaffId = managerStaffId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    // Getters
    public int getStoreId() {
        return storeId;
    }

    public int getManagerStaffId() {
        return managerStaffId;
    }

    public int getAddressId() {
        return addressId;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }
}
