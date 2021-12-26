package org.edusystems.entities;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "payment")
public class Payment {
    @Id
    @Column(name = "payment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int paymentId;

    @Column(name = "customer_id")
    int customerId;

    @Column(name = "staff_id")
    int staffId;

    @Column(name = "rental_id")
    int rentalId;

    @Column(name = "amount")
    double amount;

    @Column(name = "payment_date")
    Timestamp paymentDate;

    @Column(name = "last_update")
    Timestamp lastUpdate;

    public Payment() {
    }

    public Payment(int customerId, int staffId, int rentalId, double amount, Timestamp paymentDate) {
        this.customerId = customerId;
        this.staffId = staffId;
        this.rentalId = rentalId;
        this.amount = amount;
        this.paymentDate = paymentDate;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public void setRentalId(int rentalId) {
        this.rentalId = rentalId;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public int getStaffId() {
        return staffId;
    }

    public int getRentalId() {
        return rentalId;
    }

    public double getAmount() {
        return amount;
    }

    public Timestamp getPaymentDate() {
        return paymentDate;
    }

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }
}

