package org.edusystems.entities;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Timestamp;

/*
 * Class TextFieldResults
 * A class used for showing results in tableview. Takes information about an customer-object and the connecting
 * address-, city- and country-object.
 *
 * @author: Linda Djurström
 * @author: linda.djurstrom@edu.edugrade.se
 * @version: 1.0.
 */
public class TextFieldResult {
    //Variables, that is contained in Property.
    final ObservableList<TextFieldResult> data = FXCollections.observableArrayList();
    private final SimpleIntegerProperty customerID;
    private final SimpleStringProperty firstName;
    private final SimpleStringProperty lastName;
    private final SimpleStringProperty email;
    private final SimpleStringProperty address;
    private final SimpleStringProperty address2;
    private final SimpleStringProperty postalCode;
    private final SimpleStringProperty district;
    private final SimpleStringProperty city;
    private final SimpleStringProperty country;
    private final SimpleStringProperty phone;
    private final SimpleIntegerProperty storeID;
    private Timestamp create_date;
    private Timestamp last_update;

    //Constructor. Creates a new SimpleStringProperty with the inparameter-values.
    public TextFieldResult(int customerID, String firstName, String lastName, String email, String address, String address2,
                            String postalCode, String district, String city, String country, String phone, int storeID) {
        this.customerID = new SimpleIntegerProperty(customerID);
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.email = new SimpleStringProperty(email);
        this.address = new SimpleStringProperty(address);
        this.address2 = new SimpleStringProperty(address2);
        this.postalCode = new SimpleStringProperty(postalCode);
        this.district = new SimpleStringProperty(district);
        this.city = new SimpleStringProperty(city);
        this.country = new SimpleStringProperty(country);
        this.phone = new SimpleStringProperty(phone);
        this.storeID = new SimpleIntegerProperty(storeID);
        this.create_date = create_date;
        this.last_update = last_update;
    }

    //Getters
    public int getCustomerID() {
            return customerID.get();
        }
    public String getFirstName() {
        return firstName.get();
    }
    public String getLastName() {
        return lastName.get();
    }
    public String getEmail() {
        return email.get();
    }
    public String getAddress() {
        return address.get();
    }
    public String getAddress2() {
        return address2.get();
    }
    public String getPostalCode() {
        return postalCode.get();
    }
    public String getDistrict() {
        return district.get();
    }
    public String getCity() {
        return city.get();
    }
    public String getCountry() {
        return country.get();
    }
    public String getPhone() {
        return phone.get();
    }
    public int getStoreID() {
        return storeID.get();
    }

    //Setters
    public void setCustomerID(int customerID) {
        this.customerID.set(customerID);
    }
    public void setFirstName(String firstName) {this.firstName.set(firstName);}
    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }
    public void setEmail(String email) {
        this.email.set(email);
    }
    public void setAddress(String address) {
        this.address.set(address);
    }
    public void setAddress2(String address2) {
        this.address2.set(address2);
    }
    public void setPostalCode(String postalCode) {
        this.postalCode.set(postalCode);
    }
    public void setDistrict(String district) {
        this.district.set(district);
    }
    public void setCity(String city) {
        this.city.set(city);
    }
    public void setCountry(String country) {
        this.country.set(country);
    }
    public void setPhone(String phone) {
        this.phone.set(phone);
    }
    public void setStoreID(int storeID) {
        this.storeID.set(storeID);
    }



    /*
    public Timestamp getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Timestamp create_date) {
        this.create_date = create_date;
    }

    public Timestamp getLast_update() {
        return last_update;
    }

    public void setLast_update(Timestamp last_update) {
        this.last_update = last_update;
    } */
    }



