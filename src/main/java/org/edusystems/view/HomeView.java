package org.edusystems.view;

// Imports
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import org.edusystems.controller.HomeViewController;

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
    private MainView mainView;
    private HomeViewController homeViewController;

    // Constructor
    public HomeView(MainView mainView) {
        this.mainView = mainView;
        homeViewController = new HomeViewController(this, mainView.getViewController());
    }

    /*
     * Method getContent
     * A method to return content of the HomeView to the caller.
     * @author: Marcus Friberg
     * @author: marcus.friberg@edu.edugrade.se
     * @version: 1.0
     */
    public VBox getContent() {
        VBox content = new VBox();
        Label label = new Label("Homeview is here :)");
        label.setStyle("-fx-text-fill: #FFFFFF; -fx-font-size: 18");
        content.getChildren().add(label);
        return content;
    }

}