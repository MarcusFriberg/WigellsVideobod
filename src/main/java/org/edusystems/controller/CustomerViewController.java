package org.edusystems.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import org.edusystems.entities.Customer;
import org.edusystems.entities.TextFieldResult;
import org.edusystems.view.CustomerView;

public class CustomerViewController {
    Customer customer = new Customer();

    public CustomerViewController(){}

    public static void deleteCustomer(int customerID, HBox hBoxInfoBannerCanNotDelete, Label labelInfoBannerCanNotDelete) {
    }

    public ObservableList<TextFieldResult> searchFromCustomer(TextField textFieldSearchField) {
        ObservableList<TextFieldResult> data = FXCollections.observableArrayList();
       data = customer.searchCustomer(textFieldSearchField);
       return data;
    }

    public void deleteFromCustomer(int customerID, HBox hBoxInfoBannerCanNotDelete, Label labelInfoBannerCanNotDelete) {
        customer.deleteCustomer(customerID, hBoxInfoBannerCanNotDelete, labelInfoBannerCanNotDelete);
    }



}


