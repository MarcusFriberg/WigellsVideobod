package org.edusystems;
// Imports
import org.edusystems.controller.ViewController;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    // Main Method, launches the start-method.
    public static void main(String[] args) {
        launch(args);
    }

    /*
     * Start method
     * Starts the JavaFX application and presents the LoginView.
     * @param Takes a Stage as parameter.
     * @author: Marcus Friberg
     * @author: marcus.friberg@edu.edugrad.se
     * @version: 1.0
     */
    @Override
    public void start(Stage primaryStage) {
        // Set the title of the window and create a StackPane
        primaryStage.setTitle("Wigells videobod");
        primaryStage.setResizable(true);
        // Create a new startView and provide it with the primaryStage and let it present itself
        //LoginView loginView = new LoginView(primaryStage);
        //loginView.present();
        ViewController viewController = new ViewController(primaryStage);
    }
}
