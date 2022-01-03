package org.edusystems.view;
// Imports
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.edusystems.controller.OrderViewController;
import org.edusystems.entities.Rental;
import java.sql.Date;
import java.sql.Timestamp;

public class OrderView {
    // Variables
    private MainView mainView;
    private OrderViewController orderViewController;
    private ObservableList<Rental> rentalsObservableList;
    private FilteredList<Rental> rentalsFilteredList;
    private SortedList<Rental> sortedRentalsList;

    // Constructor
    public OrderView(MainView mainView) {
        this.mainView = mainView;
        orderViewController = new OrderViewController(this, mainView.getViewController());
        rentalsObservableList = orderViewController.getAllRentals();
        rentalsFilteredList = new FilteredList<>(rentalsObservableList, p -> true);
        sortedRentalsList = new SortedList<>(rentalsFilteredList);
    }

    /*
     * Method getContent
     * A method to return content of the HomeView to the caller.
     * @author: Marcus Friberg
     * @author: marcus.friberg@edu.edugrade.se
     * @version: 1.0
     */
    public VBox getContent() {
        // Add a page title
        Label pageTitle = new Label("Order management");
        pageTitle.setStyle("-fx-text-fill: #FFFFFF; -fx-font-size: 18");

        // Button - Add Order
        Label addOrderLabel = new Label("Add Order");
        addOrderLabel.setPadding(new Insets(-5,0,0,0));
        addOrderLabel.setStyle("-fx-text-fill: #AAAAAA; -fx-font-size: 8;");
        Image addOrderImage = new Image("/appGraphics/order-add.png");
        Image addOrderMouseOverImage = new Image("/appGraphics/order-add-mouseover.png");
        ImageView addOrderImageView = new ImageView(addOrderImage);
        VBox addOrderVBox = new VBox();
        addOrderVBox.getChildren().addAll(addOrderImageView, addOrderLabel);
        addOrderVBox.setSpacing(10);
        // Button - Add Order Events
        addOrderVBox.setOnMouseEntered(event -> {
            addOrderImageView.setImage(addOrderMouseOverImage);
            addOrderLabel.setStyle("-fx-text-fill: #DC77FA; -fx-font-size: 8");
            addOrderVBox.setStyle("-fx-cursor: hand");
        });
        addOrderVBox.setOnMouseExited(event -> {
            addOrderImageView.setImage(addOrderImage);
            addOrderLabel.setStyle("-fx-text-fill: #AAAAAA; -fx-font-size: 8");
        });
        addOrderVBox.setOnMouseClicked(event -> {
            CreateOrderView createOrderView = new CreateOrderView(this, orderViewController);
        });
        // Search
        Label filterLabel = new Label("Filter search results: ");
        filterLabel.setPrefSize(800,20);
        filterLabel.setStyle("-fx-text-fill: #AAAAAA; -fx-font-size: 10");
        filterLabel.setAlignment(Pos.BOTTOM_RIGHT);
        TextField filterTextField = new TextField();
        filterTextField.setPromptText("filter search results...");
        filterTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            rentalsFilteredList.setPredicate(rental -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String textToFilterOn = newValue.toLowerCase();
                if (rental.getCustomerName().toLowerCase().contains(textToFilterOn)) {
                    return true;
                } else if (rental.getFilmTitle().toLowerCase().contains(textToFilterOn)) {
                    return true;
                }
                return false;
            });
        });
        // HBox to hold buttons above the table
        HBox topButtonRow = new HBox();
        topButtonRow.getChildren().addAll(addOrderVBox, filterLabel,filterTextField);
        topButtonRow.setPadding(new Insets(20, 0, 10, 0));

        // Table columns
        // #1 rentalID
        TableColumn<Rental, Short> rentalIdColumn = new TableColumn<>("Order ID");
        rentalIdColumn.setMinWidth(80);
        rentalIdColumn.setCellValueFactory(new PropertyValueFactory<>("rentalId"));
        // #2 rentalDate
        TableColumn<Rental, Date> rentalDateColumn = new TableColumn<>("Rented");
        rentalDateColumn.setMinWidth(80);
        rentalDateColumn.setCellValueFactory(new PropertyValueFactory<>("rentalDate"));
        // #3 filmTitle - filmTitle is not a property of Rental entity but is reached by the custom getter.
        TableColumn<Rental, String> filmTitleColumn = new TableColumn<>("Film");
        filmTitleColumn.setMinWidth(220);
        filmTitleColumn.setCellValueFactory(new PropertyValueFactory<>("filmTitle"));
        // #4 customerName - customerName is not a property of Rental entity but is reached by the custom getter.
        TableColumn<Rental, String> customerNameColumn = new TableColumn<>("Customer");
        customerNameColumn.setMinWidth(180);
        customerNameColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        // #5 returnDate
        TableColumn<Rental, Date> returnDateColumn = new TableColumn<>("Return Date");
        returnDateColumn.setMinWidth(80);
        returnDateColumn.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
        // #6 staffName
        TableColumn<Rental, Short> staffIdColumn = new TableColumn<>("Staff Name");
        staffIdColumn.setMinWidth(80);
        staffIdColumn.setCellValueFactory(new PropertyValueFactory<>("staffName"));
        // #7 lastUpdate
        TableColumn<Rental, Timestamp> lastUpdateColumn = new TableColumn<>("Last Update");
        lastUpdateColumn.setMinWidth(80);
        lastUpdateColumn.setCellValueFactory(new PropertyValueFactory<>("lastUpdate"));
        // Table to show all orders
        TableView<Rental> rentalsTable = new TableView<Rental>();
        rentalsTable.getColumns().addAll(rentalIdColumn,rentalDateColumn, filmTitleColumn, customerNameColumn, returnDateColumn, staffIdColumn, lastUpdateColumn);
        rentalsTable.setMinWidth(1000);
        sortedRentalsList.comparatorProperty().bind(rentalsTable.comparatorProperty());
        rentalsTable.setItems(sortedRentalsList);

        // HBox and Buttons below the table to manipulate the selected row

        // Button - deleteRental
        Button deleteRental = new Button("Delete selected rental");
        deleteRental.setStyle("-fx-background-color: #DD3333");
        deleteRental.setOnMouseEntered(event -> {
            deleteRental.setStyle("-fx-background-color: #FF3333; -fx-cursor: hand");
        });
        deleteRental.setOnMouseExited(event -> {
            deleteRental.setStyle("-fx-background-color: #DD3333");
        });
        deleteRental.setOnAction(event -> {
            // Put the selected rental-entity into selectedRentalToDelete
            Rental selectedRentalToDelete = rentalsTable.getSelectionModel().getSelectedItem();
            // Delete the selected object from database
            orderViewController.deleteRental(selectedRentalToDelete);
            // Delete the selected object from the observable list
            rentalsObservableList.remove(selectedRentalToDelete);
        });

        // Button - markAsReturnedRental
        Button markAsReturnedRental = new Button("Return selected rental");
        markAsReturnedRental.setStyle("-fx-background-color: #33DD33");
        markAsReturnedRental.setOnMouseEntered(event -> {
            markAsReturnedRental.setStyle("-fx-background-color: #33FF33; -fx-cursor: hand");
        });
        markAsReturnedRental.setOnMouseExited(event -> {
            markAsReturnedRental.setStyle("-fx-background-color: #33DD33");
        });
        markAsReturnedRental.setOnAction(event -> {
            // Put the selected rental-entity into selectedRentalToReturn
            Rental selectedRentalToReturn = rentalsTable.getSelectionModel().getSelectedItem();
            // Make changes to the object in the database
            orderViewController.returnRental(selectedRentalToReturn);
        });

        // HBox - bottomButtonRow
        HBox bottomButtonRow = new HBox();
        bottomButtonRow.setPrefSize(1000,40);
        bottomButtonRow.setAlignment(Pos.BASELINE_RIGHT);
        bottomButtonRow.getChildren().addAll(deleteRental, markAsReturnedRental);
        bottomButtonRow.setSpacing(10);
        bottomButtonRow.setPadding(new Insets(20, 0, 10, 0));

        // VBox to hold all the content on the Order page
        VBox content = new VBox();
        content.getChildren().addAll(pageTitle, topButtonRow, rentalsTable, bottomButtonRow);
        return content;
    }

    // Getters
    public ObservableList<Rental> getRentalsObservableList() {
        return rentalsObservableList;
    }
}
