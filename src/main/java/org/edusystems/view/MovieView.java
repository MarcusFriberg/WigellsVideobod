package org.edusystems.view;
// Imports
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.edusystems.controller.MovieViewController;
import org.edusystems.entities.Inventory;

import java.sql.Date;

/*
 * Class MovieView
 * This class creates the graphics for the MovieView.
 * It uses a Tableview and lambda-functions that enables the user to search for users and create new, delete and update
 * existing inventory.
 * @author: Matilda Wintence
 * @author: Marcus Friberg
 * @author: matilda.wintence@edu.edugrade.se
 * @author: marcus.friberg@edu.edugrade.se
 * @version: 1.0.
 */

public class MovieView {
    // Variables
    private MainView mainView;
    private MovieViewController movieViewController;
    private ObservableList<Inventory> inventoryObservableList;
    private FilteredList<Inventory> inventoryFilteredList;
    private SortedList<Inventory> sortedInventoryList;

    // Constructor
    public MovieView(MainView mainView) {
        this.mainView = mainView;
        movieViewController = new MovieViewController(this, mainView.getViewController());
        inventoryObservableList = movieViewController.getAllInventory();
        inventoryFilteredList = new FilteredList<>(inventoryObservableList, p -> true);
        sortedInventoryList = new SortedList<>(inventoryFilteredList);
    }
    /*
     * Method getContent
     * A method to return content of the HomeView to the caller.
     * @author: Matilda Wintence
     * @author: Marcus Friberg
     * @author: matilda.wintence@edu.edugrade.se
     * @author: marcus.friberg@edu.edugrade.se
     * @version: 1.0
     */
    public VBox getContent() {
        // Add a title for the MovieView page
        Label pageTitle = new Label("Movie inventory");
        pageTitle.setStyle("-fx-text-fill: #FFFFFF; -fx-font-size: 18");

        // Add buttons to add a movie to the inventory
        Label addMovieLabel = new Label("Add movie");
        addMovieLabel.setPadding(new Insets(-5,0,0,0));
        addMovieLabel.setStyle("-fx-text-fill: #AAAAAA; -fx-font-size: 8;");
        //TODO: Make these images smaller to fit better, if I make it clickable
        Image addMovieImage = new Image("/appGraphics/movies-add.png");
        Image addMovieMouseOverImage = new Image("/appGraphics/movies-add-mouseover.png");
        ImageView addMovieImageView = new ImageView(addMovieImage);
        VBox addMovieVBox = new VBox();
        addMovieVBox.getChildren().addAll(addMovieImageView, addMovieLabel);
        addMovieVBox.setSpacing(10);

        // Button - Add Order Events
        addMovieVBox.setOnMouseEntered(event -> {
            addMovieImageView.setImage(addMovieMouseOverImage);
            addMovieLabel.setStyle("-fx-text-fill: #DC77FA; -fx-font-size: 8");
            addMovieVBox.setStyle("-fx-cursor: hand");
        });
        addMovieVBox.setOnMouseExited(event -> {
            addMovieImageView.setImage(addMovieImage);
            addMovieLabel.setStyle("-fx-text-fill: #AAAAAA; -fx-font-size: 8");
        });
        addMovieVBox.setOnMouseClicked(event -> {
            // Code to open window to add new order
        });
        // Search area
        Label filterLabel = new Label("Filter search results: ");
        filterLabel.setPrefSize(650,20);
        filterLabel.setStyle("-fx-text-fill: #AAAAAA; -fx-font-size: 10");
        filterLabel.setAlignment(Pos.BOTTOM_RIGHT);
        TextField filterTextField = new TextField();
        filterTextField.setPromptText("filter search results...");
        filterTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            inventoryFilteredList.setPredicate(inventory -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String textToFilterOn = newValue.toLowerCase();
                if (inventory.getFilmTitle().toLowerCase().contains(textToFilterOn)) {
                    return true;
                } else if (inventory.getDescription().toLowerCase().contains(textToFilterOn)) {
                    return true;
                }
                return false;
            });
        });

        // HBox to hold buttons above the table
        HBox topButtonRow = new HBox();
        topButtonRow.getChildren().addAll(addMovieVBox, filterLabel,filterTextField);
        topButtonRow.setPadding(new Insets(20, 0, 10, 0));

        // Table columns
        // #1 inventoryId
        TableColumn<Inventory, Integer> InventoryIdColumn = new TableColumn<>("Inventory Id");
        InventoryIdColumn.setMinWidth(80);
        InventoryIdColumn.setCellValueFactory(new PropertyValueFactory<>("inventoryId"));
        // #2 storeId - storeId is not a property of Inventory entity but is reached by the custom getter
        TableColumn<Inventory, Integer> storeIdColumn = new TableColumn<>("Store Id");
        storeIdColumn.setMinWidth(80);
        storeIdColumn.setCellValueFactory(new PropertyValueFactory<>("storeId"));
        // #3 filmId - filmTitle is not a property of Inventory entity but is reached by the custom getter.
        TableColumn<Inventory, String> filmTitleColumn = new TableColumn<>("Title");
        filmTitleColumn.setMinWidth(80);
        filmTitleColumn.setCellValueFactory(new PropertyValueFactory<>("filmTitle"));
        // #4 filmId - releaseYear is not a property of Inventory entity but is reached by the custom getter.
        TableColumn<Inventory, Date> releaseYearColumn = new TableColumn<>("Release year");
        releaseYearColumn.setMinWidth(80);
        releaseYearColumn.setCellValueFactory(new PropertyValueFactory<>("releaseYear"));
        // #4 filmId - length is not a property of Inventory entity but is reached by the custom getter.
        TableColumn<Inventory, Integer> lengthColumn = new TableColumn<>("Length in minutes");
        lengthColumn.setMinWidth(80);
        lengthColumn.setCellValueFactory(new PropertyValueFactory<>("length"));
        // #5 filmId - description is not a property of Inventory entity but is reached by the custom getter.
        TableColumn<Inventory, String> descriptionColumn = new TableColumn<>("Description");
        descriptionColumn.setMinWidth(80);
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        // Table to show all orders
        TableView<Inventory> inventoryTable = new TableView<Inventory>();
        inventoryTable.getColumns().addAll(InventoryIdColumn,storeIdColumn, filmTitleColumn, releaseYearColumn, lengthColumn, descriptionColumn);
        sortedInventoryList.comparatorProperty().bind(inventoryTable.comparatorProperty());
        inventoryTable.setItems(sortedInventoryList);
        // VBox to hold all the content on the Order page
        VBox content = new VBox();
        content.getChildren().addAll(pageTitle, topButtonRow, inventoryTable);
        return content;
    }
}

