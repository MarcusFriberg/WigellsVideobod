package org.edusystems.view;
// Imports
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.edusystems.controller.CreateOrderViewController;
import org.edusystems.controller.OrderViewController;
import org.edusystems.entities.Customer;
import org.edusystems.entities.Inventory;
import org.edusystems.entities.Rental;

import java.sql.Date;


public class CreateOrderView {
    // Variables
    private OrderView orderView;
    private OrderViewController orderViewController;
    private CreateOrderViewController createOrderViewController;
    private Stage createOrderStage;
    private ObservableList<Customer> customerObservableList;
    private SortedList<Customer> sortedCustomerList;
    private TableView<Customer> customerTable;
    private ObservableList<Inventory> inventoryObservableList;
    private SortedList<Inventory> sortedInventoryList;
    private TableView<Inventory> inventoryTable;

    public CreateOrderView(OrderView orderView, OrderViewController orderViewController) {
        this.orderView = orderView;
        this.orderViewController = orderViewController;
        createOrderViewController = new CreateOrderViewController(this);
        createOrderStage = new Stage();
        createOrderStage.setMinWidth(600);
        createOrderStage.setMinHeight(800);
        createOrderStage.setTitle("Create new order");
        createNewRentalWindow();
    }

    public void createNewRentalWindow() {
        // VBox containing nodes to search and lists customers
        VBox customerVBox = new VBox();
        customerVBox.setSpacing(10);
        customerVBox.setPadding(new Insets(10,10,10,10));
        HBox customerSearchHBox = new HBox();
        customerSearchHBox.setSpacing(10);
        customerSearchHBox.setAlignment(Pos.BASELINE_RIGHT);
        Label searchCustomerLabel = new Label("Search for customer ");
        TextField searchCustomerTextField = new TextField();
        Button customerSearchButton = new Button("Search Customer");
        customerSearchButton.setOnAction(event -> {
            customerObservableList = createOrderViewController.getMatchingCustomers(searchCustomerTextField.getText());
            sortedCustomerList = new SortedList<>(customerObservableList);
            sortedCustomerList.comparatorProperty().bind(customerTable.comparatorProperty());
            customerTable.setItems(sortedCustomerList);
        });
        customerSearchHBox.getChildren().addAll(searchCustomerLabel,searchCustomerTextField, customerSearchButton);

        // Table columns
        // #1 customerID
        TableColumn<Customer, Short> customerIdColumn = new TableColumn<>("Customer ID");
        customerIdColumn.setMinWidth(80);
        customerIdColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        // #2 firstName
        TableColumn<Customer, String> customerFirstNameColumn = new TableColumn<>("First name");
        customerFirstNameColumn.setMinWidth(120);
        customerFirstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        // #3 lastName
        TableColumn<Customer, String> customerLastNameColumn = new TableColumn<>("Last Name");
        customerLastNameColumn.setMinWidth(120);
        customerLastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        // #4 email
        TableColumn<Customer, String> customerEmailColumn = new TableColumn<>("E-mail");
        customerEmailColumn.setMinWidth(180);
        customerEmailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        // Search customer table
        customerTable = new TableView<Customer>();
        customerTable.getColumns().addAll(customerIdColumn,customerFirstNameColumn, customerLastNameColumn, customerEmailColumn);
        customerTable.setMaxHeight(240);
        customerVBox.getChildren().addAll(customerSearchHBox,customerTable);

        // VBox containing nodes to search and lists movies
        VBox inventoryVBox = new VBox();
        inventoryVBox.setSpacing(10);
        inventoryVBox.setPadding(new Insets(10,10,10,10));
        HBox inventorySearchHBox = new HBox();
        inventorySearchHBox.setSpacing(10);
        inventorySearchHBox.setAlignment(Pos.BASELINE_RIGHT);
        Label searchInventoryLabel = new Label("Search for movie ");
        TextField searchInventoryTextField = new TextField();
        Button inventorySearchButton = new Button("Search Movie");
        inventorySearchButton.setOnAction(event -> {
            inventoryObservableList = createOrderViewController.getMatchingInventories(searchInventoryTextField.getText());
            sortedInventoryList = new SortedList<>(inventoryObservableList);
            sortedInventoryList.comparatorProperty().bind(inventoryTable.comparatorProperty());
            inventoryTable.setItems(sortedInventoryList);
        });
        inventorySearchHBox.getChildren().addAll(searchInventoryLabel,searchInventoryTextField, inventorySearchButton);

        // Table columns
        // #1 inventoryId
        TableColumn<Inventory, Short> inventoryIdColumn = new TableColumn<>("Inventory Id");
        inventoryIdColumn.setMinWidth(80);
        inventoryIdColumn.setCellValueFactory(new PropertyValueFactory<>("inventoryId"));
        // #2 filmTitle
        TableColumn<Inventory, String> filmTitleColumn = new TableColumn<>("Movie Title");
        filmTitleColumn.setMinWidth(320);
        filmTitleColumn.setCellValueFactory(new PropertyValueFactory<>("inventoryFilmTitle"));
        // #3 releaseYear
        TableColumn<Inventory, String> releaseYearColumn = new TableColumn<>("Release Year");
        releaseYearColumn.setMinWidth(120);
        releaseYearColumn.setCellValueFactory(new PropertyValueFactory<>("inventoryFilmReleaseYear"));
        // Search customer table
        inventoryTable = new TableView<Inventory>();
        inventoryTable.getColumns().addAll(inventoryIdColumn, filmTitleColumn, releaseYearColumn);
        inventoryTable.setMaxHeight(240);
        inventoryVBox.getChildren().addAll(inventorySearchHBox,inventoryTable);

        HBox bottomRow = new HBox();
        bottomRow.setPadding(new Insets(10,10,10,10));
        bottomRow.setAlignment(Pos.BOTTOM_RIGHT);
        Button createOrderButton = new Button("Create Order");
        createOrderButton.setStyle("-fx-background-color: #33DD33");
        createOrderButton.setOnMouseEntered(event -> {
            createOrderButton.setStyle("-fx-background-color: #33FF33; -fx-cursor: hand");
        });
        createOrderButton.setOnMouseExited(event -> {
            createOrderButton.setStyle("-fx-background-color: #33DD33");
        });
        createOrderButton.setOnAction(event -> {
            // Put the selected customer-entity into selectedCustomer
            Customer selectedCustomer = customerTable.getSelectionModel().getSelectedItem();
            // Put the selected inventory-entity into selectedInventory
            Inventory selectedInventory = inventoryTable.getSelectionModel().getSelectedItem();
            // Make changes to the object in the database
            if(createOrderViewController.createRental(selectedCustomer.getCustomerId(), selectedInventory.getInventoryId())) {
                createOrderStage.close();
            }
        });
        bottomRow.getChildren().add(createOrderButton);

        VBox addOrderContent = new VBox();
        addOrderContent.getChildren().addAll(customerVBox, inventoryVBox, bottomRow);
        //addOrderContent.setAlignment(Pos.BASELINE_RIGHT);

        Scene addOrderScene = new Scene(addOrderContent, 600, 800);
        createOrderStage.setScene(addOrderScene);
        createOrderStage.show();
    }

    // Getters
    public OrderViewController getOrderViewController() {
        return orderViewController;
    }
    public OrderView getOrderView() {
        return orderView;
    }
}
