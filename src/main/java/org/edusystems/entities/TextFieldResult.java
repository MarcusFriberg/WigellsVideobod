package org.edusystems.entities;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Timestamp;

public class TextFieldResult {
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

        public int getCustomerID() {
            return customerID.get();
        }

        public SimpleIntegerProperty customerIDProperty() {
            return customerID;
        }

        public void setCustomerID(int customerID) {
            this.customerID.set(customerID);
        }

        public String getFirstName() {
            return firstName.get();
        }

        public SimpleStringProperty firstNameProperty() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName.set(firstName);
        }

        public String getLastName() {
            return lastName.get();
        }

        public SimpleStringProperty lastNameProperty() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName.set(lastName);
        }

        public String getEmail() {
            return email.get();
        }

        public SimpleStringProperty emailProperty() {
            return email;
        }

        public void setEmail(String email) {
            this.email.set(email);
        }

        public String getAddress() {
            return address.get();
        }

        public SimpleStringProperty addressProperty() {
            return address;
        }

        public void setAddress(String address) {
            this.address.set(address);
        }

        public String getAddress2() {
            return address2.get();
        }

        public SimpleStringProperty address2Property() {
            return address2;
        }

        public void setAddress2(String address2) {
            this.address2.set(address2);
        }

        public String getPostalCode() {
            return postalCode.get();
        }

        public SimpleStringProperty postalCodeProperty() {
            return postalCode;
        }

        public void setPostalCode(String postalCode) {
            this.postalCode.set(postalCode);
        }

        public String getDistrict() {
            return district.get();
        }

        public SimpleStringProperty districtProperty() {
            return district;
        }

        public void setDistrict(String district) {
            this.district.set(district);
        }

        public String getCity() {
            return city.get();
        }

        public SimpleStringProperty cityProperty() {
            return city;
        }

        public void setCity(String city) {
            this.city.set(city);
        }

        public String getCountry() {
            return country.get();
        }

        public SimpleStringProperty countryProperty() {
            return country;
        }

        public void setCountry(String country) {
            this.country.set(country);
        }

        public String getPhone() {
            return phone.get();
        }

        public SimpleStringProperty phoneProperty() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone.set(phone);
        }

        public int getStoreID() {
            return storeID.get();
        }

        public SimpleIntegerProperty storeIDProperty() {
            return storeID;
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



