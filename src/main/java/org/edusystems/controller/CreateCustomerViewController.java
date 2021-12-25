package org.edusystems.controller;

import javafx.scene.control.TextField;
import org.edusystems.entities.Customer;

public class CreateCustomerViewController {
    CustomerViewController customerViewController;


    public CreateCustomerViewController(CustomerViewController customerViewController) {
        this.customerViewController = customerViewController;
    }

    public Short getCountryID(TextField country) {
        Short countryID = customerViewController.searchCountryID(country);
        System.out.println(countryID);
        return countryID;
    }

    public void createCountry(TextField country) {
        customerViewController.createCountry(country);
    }


    public Short getCityID(TextField city) {
        Short cityID = customerViewController.searchCityID(city);
        return cityID;
    }

    public void createCity(TextField city1, int countryID) {
        customerViewController.createCity(city1, countryID);
    }


    public void addAddress(TextField textFieldAddress, TextField textFieldAddress2, TextField textFieldPostalCode, TextField textFieldDistrict, TextField textFieldPhone, int cityID) {
        customerViewController.addAddress(textFieldAddress, textFieldAddress2, textFieldPostalCode, textFieldDistrict, textFieldPhone, cityID);
    }

    public int getAddressID() {
        return customerViewController.searchAddressID();
    }

    public void addCustomer(TextField textFieldStoreID, TextField textFieldFirstName, TextField textFieldLastName, TextField textFieldEmail, int addressID) {
        customerViewController.addCustomer(textFieldStoreID, textFieldFirstName, textFieldLastName, textFieldEmail, addressID);
    }
}
