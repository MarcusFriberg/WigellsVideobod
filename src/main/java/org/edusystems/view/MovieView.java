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

/*
 * Class MovieView
 * This class creates the graphics for the MovieView.
 * It uses a Tableview and lambda-functions that enables the user to search for users and create new, delete and update
 * existing inventory.
 * @author: Matilda Wintence
 * @author: matilda.wintence@edu.edugrade.se
 * @version: 1.0.
 */

public class MovieView {
    // Variables
    private MainView mainView;
    private MovieViewController movieViewController;
    private ObservableList<Inventory> inventoryObservableList;
    private FilteredList<Inventory> inventoryFilteredList;
    private SortedList<Inventory> sortedinventoryList;

    // Constructor
    public MovieView(MainView mainView) {
        this.mainView = mainView;
        movieViewController = new MovieViewController(this, mainView.getViewController());
        inventoryObservableList = movieViewController.getAllInventory();
        inventoryFilteredList = new FilteredList<>(inventoryObservableList, p -> true);
        sortedinventoryList = new SortedList<>(inventoryFilteredList);
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
        Image addMovieImage = new Image("/appGraphics/movie-add.png");
        Image addMovieMouseOverImage = new Image("/appGraphics/order-add-mouseover.png");
        ImageView addMovieImageView = new ImageView(addMovieImage);
        VBox addMovieVBox = new VBox();
        addMovieVBox.getChildren().add(addMovieLabel);
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
                if (inventory.getMovieName().toLowerCase().contains(textToFilterOn)) {
                    return true;
                } else if (inventory.getMovieTitle().toLowerCase().contains(textToFilterOn)) {
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
        // #2 storeId -
        TableColumn<Inventory, Integer> storeIdColumn = new TableColumn<>("Store Id");
        storeIdColumn.setMinWidth(80);
        storeIdColumn.setCellValueFactory(new PropertyValueFactory<>("storeId"));
        // #3 inventoryId - filmTitle is not a property of Rental entity but is reached by the custom getter.
        TableColumn<Inventory, String> filmTitleColumn = new TableColumn<>("Film");
        filmTitleColumn.setMinWidth(80);
        filmTitleColumn.setCellValueFactory(new PropertyValueFactory<>("filmTitle"));
        // Table to show all orders
        TableView<Inventory> inventoryTable = new TableView<Inventory>();
        inventoryTable.getColumns().addAll(InventoryIdColumn,storeIdColumn, filmTitleColumn);
        sortedinventoryList.comparatorProperty().bind(inventoryTable.comparatorProperty());
        inventoryTable.setItems(sortedinventoryList);
        // VBox to hold all the content on the Order page
        VBox content = new VBox();
        content.getChildren().addAll(pageTitle, topButtonRow, inventoryTable);
        return content;
    }
}

