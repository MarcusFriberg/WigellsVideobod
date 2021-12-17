package org.edusystems.view;

// Imports
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
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
public class LoginView {
    // Variables
    private final Stage primaryStage;

    // Constructor
    public LoginView(Stage primaryStage) {
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
        // First row in View is the image
        Image loginViewImage = new Image("appGraphics/wigellsvideobodlogocats.png");
        ImageView loginViewImageView = new ImageView(loginViewImage);

        // Second row in View is userLabel & userTextfield presented in Hbox
        Label userLabel = new Label("username:");
        TextField userTextField = new TextField("username");
        VBox vBox = new VBox();
        vBox.getChildren().addAll(loginViewImageView, userTextField);

        /*
        // Make the button scale up/down as mouse enters/leaves the object
        buttonStartServer.setOnMouseEntered(event -> {
            buttonStartServer.setScaleX(0.55);
            buttonStartServer.setScaleY(0.55);
        });
        buttonStartServer.setOnMouseExited(event -> {
            buttonStartServer.setScaleX(0.5);
            buttonStartServer.setScaleY(0.5);
        });
        buttonStartServer.setOnAction(event -> new GameController(primaryStage, true));
        Button buttonStartClient = new Button("", new ImageView("clientstart.png"));
        buttonStartClient.setStyle("-fx-background-color:transparent");
        buttonStartClient.setScaleX(0.5);
        buttonStartClient.setScaleY(0.5);
        */
        // Make the button scale up/down as mouse enters/leaves the object
        // Create a StackPane to hold the VBox
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.getChildren().add(vBox);
        // Make a new Scene containing the stackPane and set the size as the backgrounds size
        Scene scene = new Scene(anchorPane, 1280, 768);
        // Set this scene as the scene of primaryStage
        primaryStage.setScene(scene);
        // Show the stage
        primaryStage.show();
    }
}


