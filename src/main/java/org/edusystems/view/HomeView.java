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
 * Class LoginView
 * A class to create a scene for the loginView of the application
 * where the user can login.
 * @param: Constructor receives the primaryStage as a parameter.
 * @author: Marcus Friberg
 * @author: marcus.friberg@edu.edugrad.se
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
        topHBox.setStyle("-fx-background-color: #000000");

        // Left VBox containing Menu options
        /*
        button.graphicProperty().bind(
                Bindings.when(
                                button.hoverProperty()
                        )
                        .then(meatView)
                        .otherwise(lambView)
        );
        */
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
        });
        orderVBox.setOnMouseExited(event -> {
            orderImageView.setImage(orderImage);
            orderLabel.setStyle("-fx-text-fill: #AAAAAA");
            // implement code to change mouse cursor back to normal
        });

        /*Button orderButton = new Button(orderLabel, orderImageView);
        orderButton.setScaleX(0.5);
        orderButton.setScaleY(0.5);
        orderButton.setTextAlignment(TextAlignment.CENTER);
        orderButton.setStyle("-fx-font-size: 30");
        orderButton.setStyle("-fx-text-fill: #AAAAAA");
        orderButton.graphicProperty().bind(
                Bindings.when(orderButton.hoverProperty())
                        .then(orderMouseOverImageView)
                        .otherwise(orderImageView)
        );
        orderButton.setStyle("-fx-background-color: transparent");
        orderButton.setContentDisplay(ContentDisplay.TOP);*/
        VBox menuVBox = new VBox();
        menuVBox.getChildren().add(orderVBox);
        menuVBox.setStyle("-fx-background-color: #000000");
        Pane contentPane = new Pane();
        HBox menuAndContentHBox = new HBox();
        menuAndContentHBox.getChildren().addAll(menuVBox, contentPane);

        VBox mainVBox = new VBox();
        mainVBox.getChildren().addAll(topHBox, menuAndContentHBox);

        // Make a new Scene containing the stackPane and set the size as the backgrounds size
        Scene scene = new Scene(mainVBox, 1280, 768);
        // Set this scene as the scene of primaryStage
        primaryStage.setScene(scene);
        // Show the stage
        primaryStage.show();
    }
}



