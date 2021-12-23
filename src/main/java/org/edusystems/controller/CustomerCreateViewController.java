package org.edusystems.controller;

import javafx.scene.control.TextField;
import org.edusystems.entities.Customer;

import java.awt.*;

public class CustomerCreateViewController {
    Customer customer = new Customer();


    public CustomerCreateViewController() {
    }

    public Short getCountryID(TextField country) {
        Short countryID = customer.searchCountryID(country);
        System.out.println(countryID);
        return countryID;
    }

    public void createCountry(TextField country) {
        customer.createCountry(country);
    }


    public Short getCityID(TextField city) {
        Short cityID = customer.searchCityID(city);
        return cityID;
    }

    public void createCity(TextField city1, int countryID) {
        customer.createCity(city1, countryID);
    }


    public void addAddress(TextField textFieldAddress, TextField textFieldAddress2, TextField textFieldPostalCode, TextField textFieldDistrict, TextField textFieldPhone, int cityID) {
     customer.addAddress(textFieldAddress, textFieldAddress2, textFieldPostalCode, textFieldDistrict, textFieldPhone, cityID);
    }

    public int getAddressID() {
        return customer.searchAddressID();
    }

    public void addCustomer(TextField textFieldStoreID, TextField textFieldFirstName, TextField textFieldLastName, TextField textFieldEmail, int addressID) {
    customer.addCustomer(textFieldStoreID, textFieldFirstName, textFieldLastName, textFieldEmail, addressID);
    }
}
