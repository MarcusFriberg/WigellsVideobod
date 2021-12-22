package org.edusystems.view;

// Imports

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

    // Constructor
    public HomeView() {

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
        content.getChildren().add(label);
        return content;
    }

}