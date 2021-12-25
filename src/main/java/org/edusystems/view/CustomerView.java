package org.edusystems.view;
// Imports
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.edusystems.controller.CustomerViewController;
import org.edusystems.entities.TextFieldResult;

public class CustomerView {
    // Variables
    private CustomerViewController customerViewController;
    private MainView mainView;
    ObservableList<TextFieldResult> data = FXCollections.observableArrayList();
    TextField textFieldSearchField = new TextField();
    TableView tableViewResultArea = new TableView();
    TableColumn storeIDCol = new TableColumn("StoreID");
    TableColumn customerIDCol = new TableColumn("CustomerID");
    TableColumn firstNameCol = new TableColumn("First name");
    TableColumn lastNameCol = new TableColumn("Last name");
    TableColumn emailCol = new TableColumn("Email");
    TableColumn addressCol = new TableColumn("Address");
    TableColumn address2Col = new TableColumn("Address2");
    TableColumn postalCodeCol = new TableColumn("Postal code");
    TableColumn districtCol = new TableColumn("District");
    TableColumn cityCol = new TableColumn("City");
    TableColumn countryCol = new TableColumn("Country");
    TableColumn phoneCol = new TableColumn("Phone");
    Button bSearch = new Button("Search");
    Button bUpdate2 = new Button("Update");
    Button bDelete = new Button("Delete");
    Button bCreate = new Button("Create customer");

    Label labelInfoBannerCanNotDelete = new Label("This customer can't be deleted, make sure that the customer \n has returned " +
            "all the rentals and payed all the fees.");

    HBox hBoxInfoBannerCanNotDelete = new HBox();
    HBox hBoxSearchField = new HBox();
    HBox hBoxSearchSettings = new HBox();
    HBox hBoxResultsField = new HBox();
    HBox hBoxChange = new HBox();
    VBox vBoxSearch = new VBox();
    VBox content = new VBox();

    // Constructor
    public CustomerView(MainView mainView) {
        this.mainView = mainView;
        customerViewController = new CustomerViewController(this, mainView.getViewController());
    }

    /*
     * Method getContent
     * A method to return content of the HomeView to the caller.
     * @author: Marcus Friberg
     * @author: marcus.friberg@edu.edugrade.se
     * @version: 1.0
     */
    public VBox getContent() {
        textFieldSearchField.setPromptText("Search...");
        textFieldSearchField.setMinWidth(600);
        textFieldSearchField.setMinHeight(50);
        textFieldSearchField.setStyle("-fx-border-color: #b42be9; -fx-border-width: 3");
        tableViewResultArea.setMinHeight(400);
        tableViewResultArea.setMinWidth(900);
        tableViewResultArea.setEditable(false);
        storeIDCol.setCellValueFactory(
                new PropertyValueFactory<TextFieldResult, Integer>("storeID")
        );
        customerIDCol.setCellValueFactory(
                new PropertyValueFactory<TextFieldResult, Integer>("customerID")
        );
        firstNameCol.setCellValueFactory(
                new PropertyValueFactory<TextFieldResult, String>("firstName")

        );
        lastNameCol.setCellValueFactory(
                new PropertyValueFactory<TextFieldResult, String>("lastName")
        );
        emailCol.setCellValueFactory(
                new PropertyValueFactory<TextFieldResult, String>("email")
        );

        addressCol.setCellValueFactory(
                new PropertyValueFactory<TextFieldResult, Integer>("address")
        );
        address2Col.setCellValueFactory(
                new PropertyValueFactory<TextFieldResult, String>("address2")

        );
        postalCodeCol.setCellValueFactory(
                new PropertyValueFactory<TextFieldResult, String>("postalCode")
        );
        districtCol.setCellValueFactory(
                new PropertyValueFactory<TextFieldResult, String>("district")
        );
        cityCol.setCellValueFactory(
                new PropertyValueFactory<TextFieldResult, String>("city")
        );
        countryCol.setCellValueFactory(
                new PropertyValueFactory<TextFieldResult, String>("country")
        );
        phoneCol.setCellValueFactory(
                new PropertyValueFactory<TextFieldResult, String>("phone")
        );

        labelInfoBannerCanNotDelete.setStyle("-fx-text-fill: #ff0000");
        bSearch.setMinHeight(44);
        tableViewResultArea.setStyle("-fx-border-color: #b42be9");

        storeIDCol.setStyle("-fx-border-color: #b42be9; ");
        customerIDCol.setStyle("-fx-border-color: #b42be9; ");
        firstNameCol.setStyle("-fx-border-color: #b42be9; ");
        lastNameCol.setStyle("-fx-border-color: #b42be9; ");
        emailCol.setStyle("-fx-border-color: #b42be9; ");
        addressCol.setStyle("-fx-border-color: #b42be9; ");
        address2Col.setStyle("-fx-border-color: #b42be9; ");
        postalCodeCol.setStyle("-fx-border-color: #b42be9; ");
        districtCol.setStyle("-fx-border-color: #b42be9; ");
        cityCol.setStyle("-fx-border-color: #b42be9; ");
        countryCol.setStyle("-fx-border-color: #b42be9; ");
        phoneCol.setStyle("-fx-border-color: #b42be9; ");

        hBoxSearchField.getChildren().addAll(textFieldSearchField, bSearch);
        hBoxSearchField.setAlignment(Pos.BASELINE_CENTER);
        hBoxSearchField.setPadding(new Insets(0, 0, 5, 50));
        hBoxSearchSettings.setAlignment(Pos.BASELINE_CENTER);
        hBoxSearchSettings.setPadding(new Insets(0, 0, 0, 50));
        hBoxResultsField.getChildren().addAll(tableViewResultArea);
        hBoxResultsField.setAlignment(Pos.BASELINE_CENTER);
        hBoxResultsField.setPadding(new Insets(5, 0, 5, 50));

        hBoxChange.getChildren().addAll(bCreate, bUpdate2, bDelete);
        hBoxChange.setAlignment(Pos.BASELINE_RIGHT);
        hBoxChange.setPadding(new Insets(5, 0, 5, 0));

        hBoxInfoBannerCanNotDelete.getChildren().addAll(labelInfoBannerCanNotDelete);
        hBoxInfoBannerCanNotDelete.setVisible(false);
        hBoxInfoBannerCanNotDelete.setPadding(new Insets(0, 0, 0, 50));

        tableViewResultArea.getColumns().addAll(storeIDCol, customerIDCol, firstNameCol, lastNameCol, emailCol, addressCol, address2Col,
                postalCodeCol, districtCol, cityCol, countryCol, phoneCol);

        vBoxSearch.getChildren().addAll(hBoxSearchField, hBoxSearchSettings, hBoxResultsField, hBoxChange, hBoxInfoBannerCanNotDelete);

       content.getChildren().add(vBoxSearch);

        bSearch.setOnAction(event -> {
            hBoxInfoBannerCanNotDelete.setVisible(false);
            data.clear();
            data = customerViewController.searchFromCustomer(textFieldSearchField);
            tableViewResultArea.setItems(data);
        });

        textFieldSearchField.setOnKeyTyped(event -> {
            data = customerViewController.searchFromCustomer(textFieldSearchField);
            tableViewResultArea.setItems(data);
        });

        bDelete.setOnAction(event -> {
            hBoxInfoBannerCanNotDelete.setVisible(false);
            int customerCopy = tableViewResultArea.getSelectionModel().getSelectedIndex();
            if(customerCopy < 0)
            {
                labelInfoBannerCanNotDelete.setText("Du måste välja en kund att radera först.");
                hBoxInfoBannerCanNotDelete.setVisible(true);
            }
            int customerID = data.get(customerCopy).getCustomerID();
            customerViewController.deleteFromCustomer(customerID, hBoxInfoBannerCanNotDelete, labelInfoBannerCanNotDelete);

            data.clear();
            data = customerViewController.searchFromCustomer(textFieldSearchField);
            tableViewResultArea.setItems(data);
        });

        bCreate.setOnAction(event -> {
            hBoxInfoBannerCanNotDelete.setVisible(false);
            CreateCustomerView customerCreateView = new CreateCustomerView();
            customerCreateView.createUpdateView("create");
        });
        showAllCustomers();
        return content;
    }

    public void showAllCustomers() {
        data = customerViewController.searchFromCustomer(textFieldSearchField);
        tableViewResultArea.setItems(data);
    }

}








