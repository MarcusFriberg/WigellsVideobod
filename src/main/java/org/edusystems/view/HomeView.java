package org.edusystems.view;

// Imports
import com.apple.eawt.AppEvent;
import javafx.beans.InvalidationListener;
import javafx.beans.binding.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableStringValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/*
 * Class HomeView
 * A class to create a scene for the homeView of the application
 * where the user can get an overview of the current state of the system.
 * @param: Constructor receives the primaryStage as a parameter.
 * @author: Marcus Friberg
 * @author: marcus.friberg@edu.edugrad.se
 * @author: Matilda Wintence
 * @author: matilda.wintence@edu.edugrad.se
 * @author: Martin Andersson
 * @author: martin.andersson@edu.edugrad.se
 * @version: 1.0
 */
public class HomeView {
    // Variables
    private final Stage primaryStage;


    // Constructor
    public HomeView(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    /*
     * Method present
     * A method to present the startView.
     * Creates the layout of the first view that appears when game is started.
     * Presents the created scene to the primaryStage that was provided with
     * the call to the constructor.
     * @author: Marcus Friberg
     * @author: marcus.friberg@edu.edugrade.se
     * @version: 1.0
     */
    public void present() {
        // Top HBox containing logo
        Image topLogoImage = new Image("/appGraphics/edusystems.png");
        ImageView topLogoImageView = new ImageView(topLogoImage);
        HBox topHBox = new HBox();
        topHBox.getChildren().add(topLogoImageView);
        topHBox.setPrefHeight(100);
        topHBox.setStyle("-fx-background-color: #000000");

        Label orderLabel = new Label("Order");
        orderLabel.setPadding(new Insets(-10,0,0,0));
        orderLabel.setStyle("-fx-text-fill: #AAAAAA");
        Image orderImage = new Image("/appGraphics/order.png");
        Image orderMouseOverImage = new Image("/appGraphics/order-mouseover.png");
        Image orderSelectedImage = new Image("/appGraphics/order-selected.png");
        ImageView orderImageView = new ImageView(orderImage);
        orderImageView.setScaleX(0.5);
        orderImageView.setScaleY(0.5);
        VBox orderVBox = new VBox();
        orderVBox.setAlignment(Pos.CENTER);
        orderVBox.getChildren().addAll(orderImageView,orderLabel);
        orderVBox.setOnMouseEntered(event -> {
            orderImageView.setImage(orderMouseOverImage);
            orderLabel.setStyle("-fx-text-fill: #DC77FA");
            // implement code to change mouse cursor to finger
            orderVBox.setStyle("-fx-cursor: hand");
        });
        orderVBox.setOnMouseExited(event -> {
            orderImageView.setImage(orderImage);
            orderLabel.setStyle("-fx-text-fill: #AAAAAA");
        });
        orderVBox.setOnMouseClicked(event -> {
            orderImageView.setImage((orderSelectedImage));
        });
        Label filmLabel = new Label("Movies");
        filmLabel.setPadding(new Insets(-10,0,0,0));
        filmLabel.setStyle("-fx-text-fill: #AAAAAA");
        Image filmImage = new Image("/appGraphics/movies.png");
        Image filmMouseOverImage = new Image("/appGraphics/movies-mouseover.png");
        Image filmSelectedImage = new Image("/appGraphics/movies-selected.png");
        ImageView filmImageView = new ImageView(filmImage);
        filmImageView.setScaleX(0.5);
        filmImageView.setScaleY(0.5);
        VBox filmVBox = new VBox();
        filmVBox.setAlignment(Pos.CENTER);
        filmVBox.getChildren().addAll(filmImageView,filmLabel);
        filmVBox.setOnMouseEntered(event -> {
            filmImageView.setImage(filmMouseOverImage);
            filmLabel.setStyle("-fx-text-fill: #DC77FA");
            // implement code to change mouse cursor to finger
            filmVBox.setStyle("-fx-cursor: hand");
        });
        filmVBox.setOnMouseExited(event -> {
            filmImageView.setImage(filmImage);
            filmLabel.setStyle("-fx-text-fill: #AAAAAA");
        });
        filmVBox.setOnMouseClicked(event -> {
            filmImageView.setImage((filmSelectedImage));
        });
        Label customerLabel = new Label("Customer");
        customerLabel.setPadding(new Insets(-10,0,0,0));
        customerLabel.setStyle("-fx-text-fill: #AAAAAA");
        Image customerImage = new Image("/appGraphics/customer.png");
        Image customerMouseOverImage = new Image("/appGraphics/customer-mouseover.png");
        Image customerSelectedImage = new Image("/appGraphics/customer-selected.png");
        ImageView customerImageView = new ImageView(customerImage);
        customerImageView.setScaleX(0.5);
        customerImageView.setScaleY(0.5);
        VBox customerVBox = new VBox();
        customerVBox.setAlignment(Pos.CENTER);
        customerVBox.getChildren().addAll(customerImageView,customerLabel);
        customerVBox.setOnMouseEntered(event -> {
            customerImageView.setImage(customerMouseOverImage);
            customerLabel.setStyle("-fx-text-fill: #DC77FA");
            // implement code to change mouse cursor to finger
            customerVBox.setStyle("-fx-cursor: hand");

        });
        customerVBox.setOnMouseExited(event -> {
            customerImageView.setImage(customerImage);
            customerLabel.setStyle("-fx-text-fill: #AAAAAA");
        });
        customerVBox.setOnMouseClicked(event -> {
            customerImageView.setImage((customerSelectedImage));
        });
        Label staffLabel = new Label("Staff");
        staffLabel.setPadding(new Insets(-10,0,0,0));
        staffLabel.setStyle("-fx-text-fill: #AAAAAA");
        Image staffImage = new Image("/appGraphics/staff.png");
        Image staffMouseOverImage = new Image("/appGraphics/staff-mouseover.png");
        Image staffSelectedImage = new Image("/appGraphics/staff-selected.png");
        ImageView staffImageView = new ImageView(staffImage);
        staffImageView.setScaleX(0.5);
        staffImageView.setScaleY(0.5);
        VBox staffVBox = new VBox();
        staffVBox.setAlignment(Pos.CENTER);
        staffVBox.getChildren().addAll(staffImageView,staffLabel);
        staffVBox.setOnMouseEntered(event -> {
            staffImageView.setImage(staffMouseOverImage);
            staffLabel.setStyle("-fx-text-fill: #DC77FA");
            // implement code to change mouse cursor to finger
            staffVBox.setStyle("-fx-cursor: hand");
        });
        staffVBox.setOnMouseExited(event -> {
            staffImageView.setImage(staffImage);
            staffLabel.setStyle("-fx-text-fill: #AAAAAA");
        });
        staffVBox.setOnMouseClicked(event -> {
            staffImageView.setImage((staffSelectedImage));
        });
        VBox menuVBox = new VBox();
        menuVBox.getChildren().addAll(orderVBox, filmVBox, customerVBox, staffVBox);
        menuVBox.setStyle("-fx-background-color: #000000");
        menuVBox.setPrefHeight(668);
        /*
        * The Pane - contentPain is where we add the unique content of each section.
        * Add tables and buttons here to view and manipulate the data from the database.
         */
        Pane contentPane = new Pane();
        contentPane.setStyle("-fx-background-color: #333231");
        contentPane.setMinWidth(1180);
        contentPane.setMinHeight(668);
        HBox menuAndContentHBox = new HBox();
        menuAndContentHBox.getChildren().addAll(menuVBox, contentPane);
        VBox mainVBox = new VBox();
        mainVBox.getChildren().addAll(topHBox, menuAndContentHBox);
        // Make a new Scene containing the stackPane and set the size as the backgrounds size
        Scene scene = new Scene(mainVBox, 1280, 768);
        // Set this scene as the scene of primaryStage
        primaryStage.setScene(scene);
        // Set the heigt of the meny VBOX to the primaryStage height minus the topHbar height
        // Show the stage
        primaryStage.show();
    }
}